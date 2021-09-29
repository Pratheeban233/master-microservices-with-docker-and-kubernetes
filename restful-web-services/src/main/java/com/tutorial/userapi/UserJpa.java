package com.tutorial.userapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userjpa")
public class UserJpa implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 3, message = "User name should have atleast 3 characters")
	private String name;
	@Past(message = "DOB should be past date")
	private LocalDate dob;

	@OneToMany(mappedBy = "userJpa", fetch = FetchType.LAZY)
	private List<Post> posts;
}
