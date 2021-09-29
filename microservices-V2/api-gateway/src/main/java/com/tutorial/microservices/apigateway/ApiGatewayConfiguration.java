package com.tutorial.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator getGatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				//only for test
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("myHeader", "customHeader")
								.addRequestParameter("myParam", "customParam"))
						.uri("http://httpbin.org:80"))

				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))

				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))

				.route(p -> p.path("/currency-conversion-test/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-test/(?<segment>.*)",
								"/currency-conversion/${segment}"))
						.uri("lb://currency-conversion"))

				.build();
	}
}
