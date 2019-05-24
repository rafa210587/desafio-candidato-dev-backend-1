package com.srmasset.thcepdetails.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Showing a simple customization of the Get /actuator/health
 * 
 * @author Rafael Meira
 * @author https://www.linkedin.com/in/rafael-meira-28876174/
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */

@Component
public class CustomHealthCheckService implements HealthIndicator {

	private final String thisService = "THCepDetails";

	@Override
	public Health health() {
		if (!isRunningServiceA()) {
			return Health.down().withDetail(thisService, "Not Available").build();
		}
		return Health.up().withDetail(thisService, "Available").build();
	}

	private Boolean isRunningServiceA() {
		Boolean isRunning = true;
		// Logic Skipped
		return isRunning;
	}
}