package com.tutorial.microservices.limitservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "limit-service")
public class PropertyConfig {
	private Integer minimum;
	private Integer maximum;
}
