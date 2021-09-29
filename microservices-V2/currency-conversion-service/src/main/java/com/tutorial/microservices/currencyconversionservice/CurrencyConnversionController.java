package com.tutorial.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping(path = "currency-conversion")
public class CurrencyConnversionController {

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping(path = "/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversionValue (@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		ResponseEntity<CurrencyConversion> conversion =
				new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/" + from + "/to/" + to, CurrencyConversion.class);
		CurrencyConversion currencyConversion = conversion.getBody();

		return new CurrencyConversion(currencyConversion.getId(), from, to,
				currencyConversion.getConversionMultiple(), quantity,
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment() + " - RestTemplate");

	}

	@GetMapping(path = "/feign/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversionValueFeign (@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion currencyConversion = currencyExchangeProxy.getCurrencyConversionValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(), from, to,
				currencyConversion.getConversionMultiple(), quantity,
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment() + " - Feign");

	}
}
