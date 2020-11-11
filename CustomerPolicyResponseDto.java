package com.insurance.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerPolicyResponseDto {
	private String customerName;
	private List<InsurancePolicyResponseDto> policiesDto = new ArrayList<InsurancePolicyResponseDto>();

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<InsurancePolicyResponseDto> getPoliciesDto() {
		return policiesDto;
	}

	public void setPoliciesDto(List<InsurancePolicyResponseDto> policiesDto) {
		this.policiesDto = policiesDto;
	}

}
