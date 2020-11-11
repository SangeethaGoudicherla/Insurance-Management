package com.insurance.service.impl;

import java.util.List;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.CustomerRequestDto;
import com.insurance.entity.Customer;
import com.insurance.exception.CustomerExistException;
import com.insurance.exception.ErrorMessage;
import com.insurance.repository.CustomerRepository;
import com.insurance.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;

	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

	/**
	 * Creates new customer.
	 * 
	 * @Param customerRequestDto information of customer along with policies
	 * @return Customer object upon registration.
	 * @exception CustomerExistException in case customer with same aadhar number exist
	 */
	@Override
	public void createCustomer(CustomerRequestDto customerRequestDto) {
		// Check if the customer already exist
		Predicate<Integer> greaterThan = i -> (i > 0);
		List<Customer> customers = customerRepository.findByaadharNumber(customerRequestDto.getAadharNumber());
		if (greaterThan.test(customers.size())) {
			throw new CustomerExistException(ErrorMessage.CUSTOMEREXIST.getMessage());
		}

		LOGGER.info("Creating customer started");

		Customer customer = new Customer();
		BeanUtils.copyProperties(customerRequestDto, customer);
		customerRepository.save(customer);

		LOGGER.info("Creating customer ended");
	}


}
