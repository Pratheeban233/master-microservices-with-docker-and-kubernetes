package com.tutorial.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange", url = "http://localhost:8000")
public interface CurrencyExchangeProxy {
	@GetMapping(path = "/currency-exchange/{from}/to/{to}")
	public CurrencyConversion getCurrencyConversionValue(@PathVariable String from, @PathVariable String to);
}
