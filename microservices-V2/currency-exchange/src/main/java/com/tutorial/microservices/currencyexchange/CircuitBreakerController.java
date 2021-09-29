package com.tutorial.microservices.currencyexchange;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	// TODO Refer : https://resilience4j.readme.io/docs for more info.
	/*
	all the configuration has 'default' as default configuration.
	We can modify as we want using properties.
	 */

	@GetMapping(path = "/test-circuitBreaker")
	@Retry(name = "sample-api-breaker", fallbackMethod = "fallBackTestCircuitBreaker")
	@CircuitBreaker(name = "default", fallbackMethod = "fallBackTestCircuitBreaker")
	@RateLimiter(name = "sample1")
	@Bulkhead(name = "default")
	public String testCircuitBreaker () {
		logger.info("main method called");
//		return new RestTemplate().getForObject("http://localhost:8080/get", String.class);
		return "ratelimit test";
	}

	public String fallBackTestCircuitBreaker (Throwable throwable) {
		logger.info("Fall back method called");
		return "<h1> FallBack Response </h1>";
	}
}
