package com.tutorial.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	// URI versioning
	@GetMapping(path = "/v1/person")
	public PersonV1 personV1 () {
		return new PersonV1("Pratheeban");
	}

	@GetMapping(path = "/v2/person")
	public personV2 personV2 () {
		return new personV2(new Name("Abiya", "Karolin"));
	}

	// Param versioning
	@GetMapping(path = "/person/param", params = "version=V1")
	public PersonV1 paramV1 () {
		return new PersonV1("Pratheeban");
	}

	@GetMapping(path = "/person/param", params = "version=V2")
	public personV2 paramV2 () {
		return new personV2(new Name("Abiya", "Karolin"));
	}

	// header versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1 () {
		return new PersonV1("Pratheeban");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public personV2 headerV2 () {
		return new personV2(new Name("Abiya", "Karolin"));
	}

	// media-type versioning
	@GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 produceV1 () {
		return new PersonV1("Pratheeban");
	}

	@GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public personV2 produceV2 () {
		return new personV2(new Name("Abiya", "Karolin"));
	}
}
