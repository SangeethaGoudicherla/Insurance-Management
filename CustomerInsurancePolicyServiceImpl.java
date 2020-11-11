package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.CustomerPolicyRequestDto;
import com.insurance.dto.CustomerPolicyResponseDto;
import com.insurance.dto.FundTransferRequestDto;
import com.insurance.dto.InsurancePolicyRequestDto;
import com.insurance.dto.InsurancePolicyResponseDto;
import com.insurance.dto.ResponseDto;
import com.insurance.entity.Customer;
import com.insurance.entity.Insurance;
import com.insurance.entity.InsuranceCompany;
import com.insurance.entity.InsurancePolicy;
import com.insurance.exception.BankServiceException;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.exception.ErrorMessage;
import com.insurance.exception.InsuranceNotFoundException;
import com.insurance.feignClient.BankClient;
import com.insurance.repository.CustomerRepository;
import com.insurance.repository.InsuranceCompanyRepository;
import com.insurance.repository.InsuranceRepository;
import com.insurance.service.CustomerInsurancePolicyService;

/**
 * Creates new customer,register policies for new and existing customer and
 * fetches policies opted by customer.
 */
@Service
public class CustomerInsurancePolicyServiceImpl implements CustomerInsurancePolicyService {

	@Autowired
	InsuranceRepository insuranceRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankClient bankClient;

	@Autowired
	InsuranceCompanyRepository companyRepository;

	private static final Logger LOGGER = Logger.getLogger(CustomerInsurancePolicyServiceImpl.class);

	private static final String PENDING = "Pending";
	private static final String SUCCESS = "Success";
	String status = PENDING;

	ConcurrentHashMap<Long, Double> insurancePaymentHashMapResult = new ConcurrentHashMap<>();

	/**
	 * Register policies for new and existing customer.
	 * 
	 * @Param insurancePolicies information of policies.
	 * @Param accountNumber accountNumber of a customer.
	 * @exception CustomerNotFoundException
	 */
	@Override
	public String registerPolicy(CustomerPolicyRequestDto customerPolicyRequestDto) throws CustomerNotFoundException {

		Optional<Customer> optionalCustomer = customerRepository.findById(customerPolicyRequestDto.getCustomerId());

		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException(ErrorMessage.CUSTOMERNOTFOUND.getMessage());
		}

		Customer customer = optionalCustomer.get();
		ConcurrentHashMap<Long, Double> insurancePaymentHashMapResult = new ConcurrentHashMap<>();
		String status = "";

		// Map customer to insurance policy
		insurancePaymentHashMapResult = mapCustomerToInsurancePolicy(customer,
				customerPolicyRequestDto.getInsurancePolicies());

		// Transfer amount to insurance company
		status = transferPremiumAmountToInsuranceCompany(insurancePaymentHashMapResult,
				customerPolicyRequestDto.getAccountNumber());

		// Set status to customer
		customer.setStatus(status);
		customerRepository.save(customer);
		return SUCCESS;
	}

	/**
	 * Invokes BankService to pay premium to respective insurance company.
	 * 
	 * @Param insurancePaymentHashMapResult - AccountNumber and Premium amount.
	 * @Param accountNumber accountNumber of a customer.
	 * @exception BankServiceException
	 */
	private String transferPremiumAmountToInsuranceCompany(
			ConcurrentHashMap<Long, Double> insurancePaymentHashMapResult, Long insuranceAccountNumber)
			throws BankServiceException {

		List<FundTransferRequestDto> fundTransferRequestDtos = new ArrayList<>();

		insurancePaymentHashMapResult.forEach((k, v) -> {
			FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
			fundTransferRequestDto.setFrom_AccountNumber(insuranceAccountNumber);
			fundTransferRequestDto.setRemarks("Premium amount");
			fundTransferRequestDto.setTo_AccountNumber(k);
			fundTransferRequestDto.setAmount(v);
			fundTransferRequestDtos.add(fundTransferRequestDto);
		});

		ResponseDto responseDto = bankClient.fundTransfer(fundTransferRequestDtos);
		if (responseDto.getStatusMessage() != "Success") {
			throw new BankServiceException(responseDto.getStatusMessage());
		}
		System.out.println(responseDto.getStatusMessage());

		LOGGER.info("Payment done successfully");
		return status;
	}

	/**
	 * Invokes BankService to pay premium to respective insurance company.
	 * 
	 * @Param customer customer.
	 * @Param insurancePolicies accountNumber of a customer.
	 * @exception InsuranceNotFoundException
	 */

	private ConcurrentHashMap<Long, Double> mapCustomerToInsurancePolicy(Customer customer,
			List<InsurancePolicyRequestDto> insurancePolicies) {

		ConcurrentHashMap<Long, Double> insurancePaymentHashMap = new ConcurrentHashMap<>();
		AtomicLong atomicLong = new AtomicLong();

		insurancePolicies.stream().forEach(requestDto -> {

			Optional<Insurance> optionalInsurance = insuranceRepository.findById(requestDto.getInsurance_id());

			if (!optionalInsurance.isPresent()) {
				throw new InsuranceNotFoundException(ErrorMessage.INSURANCENOTFOUND.getMessage());
			}

			Insurance insurance = optionalInsurance.get();
			InsurancePolicy insurancePolicy = new InsurancePolicy();
			insurancePolicy.setInsurance_id(insurance.getInsurance_id());
			insurancePolicy.setPremiumAmount(requestDto.getPremiumAmount());
			insurancePolicy.setPolicyDate(Calendar.getInstance().getTime());

			insurancePolicy.setPolicyNumber(getPolicyNumber());

			customer.setTotalAmount(atomicLong.addAndGet(requestDto.getPremiumAmount()));

			customer.getInsurancePolicy().add(insurancePolicy);
			Optional<InsuranceCompany> company = companyRepository.findById(insurance.getInsurance_company_id());

			insurancePaymentHashMapResult = prepareInsurancePolicyPaymentsMap(insurancePaymentHashMap,
					company.get().getAccountNumber(), requestDto.getPremiumAmount());
		});

		// Save customer as total amount is calculated
		customerRepository.save(customer);

		return insurancePaymentHashMapResult;
	}

	private int getPolicyNumber() {

		Supplier<Integer> supplier = () -> {
			Random random = new Random();
			int policyNumber = random.nextInt(10000);
			return policyNumber;
		};
		return supplier.get();
	}

	private ConcurrentHashMap<Long, Double> prepareInsurancePolicyPaymentsMap(
			ConcurrentHashMap<Long, Double> insurancePaymentHashMap, Long accountNumber, double premiumAmount) {
		// Java8
		if (insurancePaymentHashMap.size() > 0) {
			Iterator<Long> iterator = insurancePaymentHashMap.keySet().iterator();

			while (iterator.hasNext()) {
				Long key = iterator.next();
				Double value = insurancePaymentHashMap.get(key);

				if (key.equals(accountNumber)) {
					insurancePaymentHashMap.replace(key, value + premiumAmount);
				} else {
					insurancePaymentHashMap.put(accountNumber, premiumAmount);
				}
			}
		} else {
			insurancePaymentHashMap.put(accountNumber, premiumAmount);
		}
		return insurancePaymentHashMap;

	}

	/**
	 * Retrieves all policies of a customer.
	 * 
	 * @Param customerId.
	 * @return Customer and policies details opted by customer.
	 * @exception CustomerNotFoundException
	 */
	@Override
	public CustomerPolicyResponseDto getAllPoliciesOptedByCustomer(Long customerId) throws CustomerNotFoundException {
		LOGGER.info("Fetching policies opted by customer :" + customerId);
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		List<InsurancePolicyResponseDto> insurancePolicyDtoList = new ArrayList<InsurancePolicyResponseDto>();
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException(ErrorMessage.CUSTOMERNOTFOUND.getMessage());
		}

		Customer customer = optionalCustomer.get();
		CustomerPolicyResponseDto responseDto = new CustomerPolicyResponseDto();
		responseDto.setCustomerName(customer.getCustomer_name());
		LOGGER.info("Number of policies opted are:" + customer.getInsurancePolicy().size());

		customer.getInsurancePolicy().stream().forEach(policy -> {
			InsurancePolicyResponseDto policyDto = new InsurancePolicyResponseDto();
			policyDto.setPolicyNumber(policy.getPolicyNumber());
			policyDto.setPremiumAmount(policy.getPremiumAmount());
			policyDto.setPolicyDate(policy.getPolicyDate());

			Optional<Insurance> optionalInsurance = insuranceRepository.findById(policy.getInsurance_id());
			Insurance insurance = optionalInsurance.get();

			policyDto.setInsuranceName(insurance.getInsurance_name());
			policyDto.setInsuranceType(insurance.getInsurance_type());
			policyDto.setSumInsured(insurance.getSumInsured());
			policyDto.setPeriod(insurance.getPeriod());

			insurancePolicyDtoList.add(policyDto);
		});

		responseDto.getPoliciesDto().addAll(insurancePolicyDtoList);
		return responseDto;
	}

}
