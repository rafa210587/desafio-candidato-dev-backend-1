package com.srmasset.thcepdetails.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("thcepdetails")
@Data
public class THCepDetailsProperties {

	private EndpointProperties endpoint;
	private String thTestApi;
}
