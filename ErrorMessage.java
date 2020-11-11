package com.insurance.exception;

public enum ErrorMessage {
	
	CUSTOMERNOTFOUND("Customer not found in the DataBase"), 
	COMPANYNOTFOUND("Companies are not found in DataBase"),
	INSURANCENOTFOUND("Insurance not available in DataBase"),
	CUSTOMEREXIST("Customer already exist"),
	BANKSERVICEEXCEPTION("Exception occurred while interacting with Bank service"),
	INSURANCECOMPANYEXCEPTION("Exception occurred while preparing Insurance Company Response");
	
	private String message;

	public String getMessage() {
		return message;
	}

	private ErrorMessage(String message) {
		this.message = message;
	}
}