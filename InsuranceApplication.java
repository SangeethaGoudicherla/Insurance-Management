package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.insurance.configuration.RibbonConfiguration;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@RibbonClient(value = "svsBankService_LoadBalancer", configuration = RibbonConfiguration.class)
public class InsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}

}
