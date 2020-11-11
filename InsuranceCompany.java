package com.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InsuranceCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long company_id;

	private String company_name;
	private String company_headQuarters;
	private String company_phoneNumber;
	private String company_emailId;
	private String sector;
	private Long accountNumber;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

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

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	

}
