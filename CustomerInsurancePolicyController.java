package com.insurance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.CustomerPolicyRequestDto;
import com.insurance.dto.CustomerPolicyResponseDto;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.service.CustomerInsurancePolicyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/customerinsurancepolicies")
@Api(value = "Customer Policy Controller")
public class CustomerInsurancePolicyController {

	@Autowired
	CustomerInsurancePolicyService customerInsuranceService;

	private static final String SUCCESS = "success";

	@PostMapping("")
	@ApiOperation(value = "Apply policy for a customer")
	@ApiResponses(value = { @ApiResponse(code = 505, message = "System is overloaded wait a while!!!") })
	public ResponseEntity<String> applyPolicy(@Valid @RequestBody CustomerPolicyRequestDto customerRequestDto)
			throws CustomerNotFoundException {
		String status = customerInsuranceService.registerPolicy(customerRequestDto);
		if (status.equalsIgnoreCase(SUCCESS)) {
			return new ResponseEntity<String>("Successfully registered policy ", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed to register policy", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("{customerId}")
	@ApiOperation(value = "View all policies opted by a customer")
	@ApiResponses(value = { @ApiResponse(code = 506, message = "Under maintenance") })
	public ResponseEntity<CustomerPolicyResponseDto> getAllPoliciesOptedByCustomer(@PathVariable Long customerId)
			throws CustomerNotFoundException {
		return new ResponseEntity<CustomerPolicyResponseDto>(
				customerInsuranceService.getAllPoliciesOptedByCustomer(customerId), HttpStatus.OK);
	}
}
