package com.tutorial.microservices.limitservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Limits implements Serializable {
	private Integer minimum;
	private Integer maximum;
}
