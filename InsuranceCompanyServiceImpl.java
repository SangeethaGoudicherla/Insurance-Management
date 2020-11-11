package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsuranceCompanyResponseDto;
import com.insurance.dto.InsuranceResponseDto;
import com.insurance.entity.Insurance;
import com.insurance.entity.InsuranceCompany;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.exception.ErrorMessage;
import com.insurance.exception.InsuranceCompanyException;
import com.insurance.repository.InsuranceCompanyRepository;
import com.insurance.repository.InsuranceRepository;
import com.insurance.service.InsuranceCompanyService;

@Service
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {

	@Autowired
	InsuranceCompanyRepository companyRepository;

	@Autowired
	InsuranceRepository insuranceRepository;
	
	private static final Logger LOGGER = Logger.getLogger(InsuranceCompanyServiceImpl.class);
	
	/**
	 * Retrieves all Insurance companies supported by application.
	 * @Param pageNumber - For Pagination.
	 * @param pageSize - For Pagination
	 * @return Insurance companies along with policies supported by each of them.
	 * @exception CustomerNotFoundException and InsuranceCompanyException
	 */
	@Override
	public List<InsuranceCompanyResponseDto> listOfInsuranceCompanies(int pageNumber, int pageSize) throws CustomerNotFoundException {
		LOGGER.info("Started fetching list of insurance companies");
		List<InsuranceCompanyResponseDto> companyResponseDtos = new ArrayList<InsuranceCompanyResponseDto>();
		List<InsuranceCompany> insuranceCompanies = new ArrayList<>();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		insuranceCompanies = companyRepository.findAll(pageable).getContent();
		LOGGER.info("Ended fetching list of insurance companies");
		if (insuranceCompanies.size() == 0) {
			throw new CustomerNotFoundException(ErrorMessage.COMPANYNOTFOUND.getMessage());
		}

		try {
			LOGGER.info("Total number of insurance companies supported are :"+insuranceCompanies.size());
			// Looping all insurance Companies
			insuranceCompanies.stream().forEach(insuranceCompany -> {

				List<InsuranceResponseDto> insuranceDtos = new ArrayList<>();
				InsuranceCompanyResponseDto companyDto = new InsuranceCompanyResponseDto();
				BeanUtils.copyProperties(insuranceCompany, companyDto);
				// Looping all insurances supported by each company

				List<Insurance> insurances = insuranceRepository
						.getInsuranceByinsurance_company_id(insuranceCompany.getCompany_id());

				insurances.stream().forEach(insurance -> {
					InsuranceResponseDto insuranceDto = new InsuranceResponseDto();
					BeanUtils.copyProperties(insurance, insuranceDto);
					insuranceDtos.add(insuranceDto);
				});

				companyDto.getInsurances().addAll(insuranceDtos);
				companyResponseDtos.add(companyDto);
			});
		} catch (Exception e) {
			throw new InsuranceCompanyException(ErrorMessage.INSURANCECOMPANYEXCEPTION.getMessage());
		}

		return companyResponseDtos;
	}

}
