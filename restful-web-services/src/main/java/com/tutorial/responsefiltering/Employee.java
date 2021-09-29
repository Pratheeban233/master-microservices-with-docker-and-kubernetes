package com.tutorial.responsefiltering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter(value = "Employee-Filter")
public class Employee {
	private Integer id;
	private String empName;
	private String empAddress;

}
