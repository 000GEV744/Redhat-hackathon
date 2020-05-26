package com.example.demo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@Size(min = 3)
	@NotNull
	private String firstName;
	
	@Size(min = 3)
	@NotNull
	private String lastName;
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String confirmPassword;
	
	@NotNull
	private String phoneNumber;
}
