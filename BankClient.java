package com.insurance.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.dto.FundTransferRequestDto;
import com.insurance.dto.ResponseDto;



//@FeignClient(value = "Bank-service", url = "http://localhost:8082/svsbank")
@FeignClient(name="http://SVSBANK-SERVICE/svsbank")
public interface BankClient {


	@PostMapping("/bank/fundTransfer")
	public ResponseDto fundTransfer(@RequestBody List<FundTransferRequestDto> fundTransferRequestDto);
	
	@GetMapping("/port")
	public String portDetails();
}
