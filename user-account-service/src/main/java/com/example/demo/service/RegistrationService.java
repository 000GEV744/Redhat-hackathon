package com.example.demo.service;



import com.example.demo.model.Response;
import com.example.demo.model.UpdatePasswordModel;
import com.example.demo.model.UserDto;

public interface RegistrationService {

	Object saveUser(UserDto userDto);
	//UserDto updateEmail(String userId, String email);
	UserDto updatePassword(UpdatePasswordModel passwordModel);
	UserDto updatePhoneNumber(String userId, String phoneNumber);
	Response deleteUser(String userId);
	UserDto validateEmail(String token);
	Object registerWithEmail(String email);
	
}
