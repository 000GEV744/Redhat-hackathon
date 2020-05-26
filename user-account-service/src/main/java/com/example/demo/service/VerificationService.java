package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationModel;

public interface VerificationService {

	public Object addVerificationDetails(User user);
	public User validateEmail(String token);
	public Object updateToken(VerificationModel verificationModel, String fName, String lName, String email);
}
