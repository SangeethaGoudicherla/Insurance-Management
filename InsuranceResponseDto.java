package com.insurance.dto;

public class InsuranceResponseDto {

	private String insurance_name;
	private String insurance_type;
	private double sumInsured;
	private String period;

	public String getInsurance_name() {
		return insurance_name;
	}

	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}

	public String getInsurance_type() {
		return insurance_type;
	}

	public void setInsurance_type(String insurance_type) {
		this.insurance_type = insurance_type;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
