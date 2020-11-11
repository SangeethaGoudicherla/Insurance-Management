package com.insurance.exception;

public class CustomerExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CustomerExistException(String message) {
		super(message);
	}
}
