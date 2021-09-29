package com.tutorial.responsefiltering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeBeanResponseFilter {

	@GetMapping("/getEmployee")
	public MappingJacksonValue getEmployee () {
		Employee employee = new Employee(1, "Pratheeban", "DGL");
		MappingJacksonValue mappingEmployeeValue = new MappingJacksonValue(employee);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("empName", "empAddress");
		FilterProvider filters = new SimpleFilterProvider().addFilter("Employee-Filter", filter);
		mappingEmployeeValue.setFilters(filters);
		return mappingEmployeeValue;
	}

	@GetMapping("/getEmployeeList")
	public MappingJacksonValue getEmployeeList () {
		List<Employee> employeeList = Arrays.asList(new Employee(1, "Pratheeban", "DGL"),
				new Employee(2, "Abiya", "CHN"));
		MappingJacksonValue mappingEmployeeList = new MappingJacksonValue(employeeList);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "empName");
		FilterProvider employeeListFilter = new SimpleFilterProvider().addFilter("Employee-Filter", filter);
		mappingEmployeeList.setFilters(employeeListFilter);
		return mappingEmployeeList;
	}
}
