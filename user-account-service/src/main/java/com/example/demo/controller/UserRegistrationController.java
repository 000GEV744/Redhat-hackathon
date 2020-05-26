package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.Response;
import com.example.demo.model.UpdatePasswordModel;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserRequest;
import com.example.demo.model.UserResponse;
import com.example.demo.service.RegistrationService;

@RestController
public class UserRegistrationController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private RegistrationService registrationServiceImpl;
	
	@Autowired
	private ModelMapper mapper; 

	//*************Register an Account****************
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
		
		logger.info("inside controller :: registerUser()");
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto=mapper.map(userRequest, UserDto.class);
		Object resp =  registrationServiceImpl.saveUser(userDto);
		return ResponseEntity.ok(resp);
	}
	
	//*************Delete Account****************
	@DeleteMapping("/user/{user_id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "user_id") String userId){
		
		logger.info("inside controller :: deleteUser()");
		Response resp = registrationServiceImpl.deleteUser(userId);
		return ResponseEntity.ok(resp);
	}
	
	//*************update phone number****************
	@PutMapping("/user/{user_id}/mobile-number/{phoneNumber}")
	public ResponseEntity<?> updatePhoneNumber(@PathVariable(name = "user_id") String userId, @PathVariable(name = "phoneNumber") String phoneNumber){
		
		logger.info("inside controller :: updatePhoneNumber()");
		UserDto uDto = registrationServiceImpl.updatePhoneNumber(userId, phoneNumber);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponse  response =  mapper.map(uDto, UserResponse.class);
		
		return ResponseEntity.ok(response);
	}
	
	//*************update email****************
	/*@PutMapping("/user/{user_id}/email/{email}")
	public ResponseEntity<?> updateEmail(@PathVariable(name = "user_id") String userId, @PathVariable(name = "email") String email){
		
		logger.info("inside controller :: updateEmail()");
		registrationServiceImpl.updateEmail(userId, email);
		return ResponseEntity.ok("updated..");
	}*/
	
	
	//***********update password******
	@PutMapping("/user/update-password")
	public ResponseEntity<?> updateEmail(@RequestBody UpdatePasswordModel passwordModel){
		
		logger.info("inside controller :: updateEmail()");
		UserDto uDto = registrationServiceImpl.updatePassword(passwordModel);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponse  response =  mapper.map(uDto, UserResponse.class);
		
		return ResponseEntity.ok(response);
	}
	
	//***********validate token for email validation******
	@GetMapping("/validateEmail/token/{token}")
	public ResponseEntity<Object> validateEmail(@PathVariable String token){
		
		logger.info("inside controller :: validateEmail() :: token :: {}", token);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto uDto = registrationServiceImpl.validateEmail(token);
		UserResponse  response =  mapper.map(uDto, UserResponse.class);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	//***********incase user didn't validate in time and token expired then again we can enter then user's email, it will get it's verification link******
	@GetMapping("/register/email/{email}")
	public ResponseEntity<?> registerWithEmail(@PathVariable String email){
		
		logger.info("inside controller :: registerWithEmail()");
		Object response = registrationServiceImpl.registerWithEmail(email);
		
		return ResponseEntity.ok(response);
	}

	
}
