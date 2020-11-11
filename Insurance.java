package com.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long insurance_id;

	private String insurance_name;
	private String insurance_type;
	private double sumInsured;
	private String period;
	private Long insurance_company_id;

	public Long getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(Long insurance_id) {
		this.insurance_id = insurance_id;
	}

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

	public Long getInsurance_company_id() {
		return insurance_company_id;
	}

	public void setInsurance_company_id(Long insurance_company_id) {
		this.insurance_company_id = insurance_company_id;
	}

/*	public InsuranceCompany getInsurance_company() {
		return insurance_company;
	}

	public void setInsurance_company(InsuranceCompany insurance_company) {
		this.insurance_company = insurance_company;
	}*/

}
