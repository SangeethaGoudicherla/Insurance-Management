package com.insurance.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InsurancePolicyRequestDto {
	
	@NotNull
	@Min(1000)
	private Long premiumAmount;
	
	@NotNull
	private Long insurance_id;

	public Long getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Long premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Long getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(Long insurance_id) {
		this.insurance_id = insurance_id;
	}

}
