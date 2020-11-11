package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.InsuranceCompanyResponseDto;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.service.InsuranceCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/insurancecompanies")
@Api(value = "insurance company controller")
public class InsuranceCompanyController {

	@Autowired
	InsuranceCompanyService insuranceCompanyService;

	@GetMapping("{pageNumber}/{pageSize}")
	@ApiOperation(value = "View all insurance companies")
	public ResponseEntity<List<InsuranceCompanyResponseDto>> listOfInsuranceCompanies(@PathVariable int pageNumber,
			@PathVariable int pageSize) throws CustomerNotFoundException {
		return new ResponseEntity<List<InsuranceCompanyResponseDto>>(
				insuranceCompanyService.listOfInsuranceCompanies(pageNumber, pageSize), HttpStatus.OK);
	}
}
