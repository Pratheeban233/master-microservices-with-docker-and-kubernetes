package com.tutorial.userapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 3,message = "User name should have atleast 3 characters")
	private String name;
	@Past(message = "DOB should be past date")
	private LocalDate dob;

}
