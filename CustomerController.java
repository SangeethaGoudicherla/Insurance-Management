package com.insurance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.CustomerRequestDto;
import com.insurance.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/customer")
@Api(value = "Customer Controller")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("")
	@ApiOperation(value = "Creates a new customer")
	@ApiResponses(value = { @ApiResponse(code = 504, message = "System is overloaded"),
			@ApiResponse(code = 504, message = "We are on holiday") })
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
		customerService.createCustomer(customerRequestDto);
		return new ResponseEntity<String>("Customer created successfully", HttpStatus.CREATED);
	}
}
