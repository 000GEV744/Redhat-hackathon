package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyTokenRequest {

	private String fname;
	private String lname;
	private String to;
	private String subject;
	private String token;
}
