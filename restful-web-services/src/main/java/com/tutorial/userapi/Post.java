package com.tutorial.userapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3,message = "Desc should be more than 3 characters")
	private String desc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private UserJpa userJpa;
}
