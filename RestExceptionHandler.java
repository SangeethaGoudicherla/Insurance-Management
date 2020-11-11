package com.insurance.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.insurance.dto.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CUSTOMERCODE = "Customer-001";
	private static final String COMPANYCODE = "Company-001";
	private static final String INSURANCECODE = "Insurance-001";
	private static final String BANKSERVICECODE = "BankService-001";
	private static final String CUSTOMEREXISTCODE = "Customer-002";
	private static final String INSURANCECODE2 = "Insurance-002";
	private static final String INVALIDDATA = "InvalidData";

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException customerException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(CUSTOMERCODE);
		errorResponse.setStatusMessage(customerException.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CompanyNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCompanyNotFoundException(CompanyNotFoundException companyException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(COMPANYCODE);
		errorResponse.setStatusMessage(companyException.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InsuranceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleInsuranceNotFoundException(InsuranceNotFoundException insuranceException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(INSURANCECODE);
		errorResponse.setStatusMessage(insuranceException.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CustomerExistException.class)
	public ResponseEntity<ErrorResponse> handleCustomerExistException(CustomerExistException customerExistException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(CUSTOMEREXISTCODE);
		errorResponse.setStatusMessage(customerExistException.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = BankServiceException.class)
	public ResponseEntity<ErrorResponse> handleBankServiceException(BankServiceException bankServiceException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(BANKSERVICECODE);
		errorResponse.setStatusMessage(bankServiceException.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InsuranceCompanyException.class)
	public ResponseEntity<ErrorResponse> handleInsuranceCompanyException(InsuranceCompanyException insuranceCompanyException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(INSURANCECODE2);
		errorResponse.setStatusMessage(insuranceCompanyException.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException argInvalidException,
			HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(INVALIDDATA);
		String allFieldErrors = argInvalidException.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
		errorResponse.setStatusMessage(allFieldErrors);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
