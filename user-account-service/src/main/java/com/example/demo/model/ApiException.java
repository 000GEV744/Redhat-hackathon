package com.example.demo.model;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {

	private ZonedDateTime timestamp;
	private int status;
	private HttpStatus error;
	private String message;
	
}
