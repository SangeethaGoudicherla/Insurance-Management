package com.insurance.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerRequestDto {
	
	@NotNull
	@Size(min = 2, max = 30, message = "Customer Name size must be between 2 and 30")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String customer_name;

	@NotBlank(message = "Date Of Birth is mandatory")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;

	@NotBlank(message = "Gender is mandatory cannot be left blank")
	private String gender;

	@NotBlank(message = "Phone Number is mandatory and cannot be left blank")
	@Digits(fraction = 0, integer = 12)
	private String phoneNumber;

	@NotBlank(message = "Address is mandatory and cannot be blank")
	private String address;

	
	@NotBlank(message = "Aadhar number is mandatory and cannot be blank")
	@Digits(fraction = 0, integer = 16)
	private String aadharNumber;


	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
}
