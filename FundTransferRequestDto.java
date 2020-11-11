package com.insurance.dto;

public class FundTransferRequestDto {
	
	private Long from_AccountNumber;
	private Long to_AccountNumber;
	
	private Double amount;
	private String remarks;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getFrom_AccountNumber() {
		return from_AccountNumber;
	}

	public void setFrom_AccountNumber(Long from_AccountNumber) {
		this.from_AccountNumber = from_AccountNumber;
	}

	public Long getTo_AccountNumber() {
		return to_AccountNumber;
	}

	public void setTo_AccountNumber(Long to_AccountNumber) {
		this.to_AccountNumber = to_AccountNumber;
	}

}
