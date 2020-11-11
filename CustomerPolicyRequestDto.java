package com.insurance.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


public class CustomerPolicyRequestDto {
	
	@NotNull
	private Long customerId;
	
	@Digits(fraction = 0, integer = 16)
	private Long accountNumber;

	private List<InsurancePolicyRequestDto> insurancePolicies = new ArrayList<InsurancePolicyRequestDto>();

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<InsurancePolicyRequestDto> getInsurancePolicies() {
		return insurancePolicies;
	}

	public void setInsurancePolicies(List<InsurancePolicyRequestDto> insurancePolicies) {
		this.insurancePolicies = insurancePolicies;
	}

}
