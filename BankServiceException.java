package com.insurance.exception;

public class BankServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BankServiceException(String message) {
		super(message);
	}
}
