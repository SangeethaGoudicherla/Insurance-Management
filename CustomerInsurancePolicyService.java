package com.insurance.service;


import com.insurance.dto.CustomerPolicyRequestDto;
import com.insurance.dto.CustomerPolicyResponseDto;
import com.insurance.exception.CustomerNotFoundException;

public interface CustomerInsurancePolicyService {

	CustomerPolicyResponseDto getAllPoliciesOptedByCustomer(Long customerId) throws CustomerNotFoundException;
	String registerPolicy(CustomerPolicyRequestDto customerPolicyRequestDto) throws CustomerNotFoundException;

}
