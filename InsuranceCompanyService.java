package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceCompanyResponseDto;
import com.insurance.exception.CustomerNotFoundException;

public interface InsuranceCompanyService {

	public List<InsuranceCompanyResponseDto> listOfInsuranceCompanies(int pageNumber, int pageSize) throws CustomerNotFoundException;

}
