package com.srmasset.thcepdetails.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.srmasset.thcepdetails.pojos.AddressDTO;

import lombok.AllArgsConstructor;

/**
 * Bringing the information that is displayed by the healthcheck of actuator to
 * the GET /info actually hosted in /actuator/info also checking the
 * availability of the rest service CEP that this service requires.
 * 
 * @author Rafael Meira
 * @author https://www.linkedin.com/in/rafael-meira-28876174/
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */

@Component
@DependsOn("THCepDetailsProperties")
@AllArgsConstructor
public class CustomInfoCheckService implements InfoContributor {

	private RestTemplate restTemplate;

	private THCepDetailsProperties restProperties;

	@Override
	public void contribute(Info.Builder builder) {
		final String thisService = "THCepDetails";
		final String otheService = "restCepApi";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(thisService, isRunning());
		map.put(otheService, isRunningRest());
		builder.withDetail("Info", map);
	}

	private String isRunningRest() {
		ResponseEntity<AddressDTO> response = restTemplate.exchange(
				restProperties.getEndpoint().getThApi() + restProperties.getThTestApi(), HttpMethod.GET,
				new HttpEntity<>(""), AddressDTO.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return "UP";
		}
		return "DOWN";
	}

	private Health isRunning() {

		return Health.up().build();
	}

}