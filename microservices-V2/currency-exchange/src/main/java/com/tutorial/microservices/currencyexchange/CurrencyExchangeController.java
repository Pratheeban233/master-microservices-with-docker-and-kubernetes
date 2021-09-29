package com.tutorial.microservices.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping(path = "{from}/to/{to}")
	public CurrencyExchange getCurrency(@PathVariable String from, @PathVariable String to){
		String port = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchange == null){
			throw new RuntimeException("Currency value not found for "+from+" to "+to);
		}
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
