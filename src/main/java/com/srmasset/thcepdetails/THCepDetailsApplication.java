package com.srmasset.thcepdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class THCepDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(THCepDetailsApplication.class, args);
	}

}
