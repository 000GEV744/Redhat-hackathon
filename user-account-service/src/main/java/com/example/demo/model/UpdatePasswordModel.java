package com.example.demo.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordModel {

	private String userId;
	
	@NotNull
	private String password;
	
	@NotNull
	private String confirmPassword;
	
}
