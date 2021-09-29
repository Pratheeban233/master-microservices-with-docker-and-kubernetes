package com.tutorial.microservices.limitservice.controller;

import com.tutorial.microservices.limitservice.configuration.PropertyConfig;
import com.tutorial.microservices.limitservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

	@Autowired
	private PropertyConfig propertyConfig;

	@GetMapping(path = "/limits")
	public Limits getLimits(){
		return new Limits(propertyConfig.getMinimum(),propertyConfig.getMaximum());
	}
}
