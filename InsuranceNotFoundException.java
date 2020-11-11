package com.insurance.exception;

public class InsuranceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsuranceNotFoundException(String message) {
		super(message);
	}
}
