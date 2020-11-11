package com.insurance.dto;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCompanyResponseDto {
	
	private String company_name;
	private String company_headQuarters;
	private String company_phoneNumber;
	private String company_emailId;
	private String sector;

	private List<InsuranceResponseDto> insurances = new ArrayList<InsuranceResponseDto>();

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_headQuarters() {
		return company_headQuarters;
	}

	public void setCompany_headQuarters(String company_headQuarters) {
		this.company_headQuarters = company_headQuarters;
	}

	public String getCompany_phoneNumber() {
		return company_phoneNumber;
	}

	public void setCompany_phoneNumber(String company_phoneNumber) {
		this.company_phoneNumber = company_phoneNumber;
	}

	public String getCompany_emailId() {
		return company_emailId;
	}

	public void setCompany_emailId(String company_emailId) {
		this.company_emailId = company_emailId;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public List<InsuranceResponseDto> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<InsuranceResponseDto> insurances) {
		this.insurances = insurances;
	}

}
