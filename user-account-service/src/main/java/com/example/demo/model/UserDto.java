package com.example.demo.model;

import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String userId;
	
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
	
	@NotNull
	private Boolean userIsActive;

	public UserDto(@Size(min = 3) @NotNull String firstName, @Size(min = 3) @NotNull String lastName,
			@Email @NotNull String email, @NotNull String password, @NotNull String confirmPassword,
			@NotNull String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneNumber = phoneNumber;
	}

	

}
