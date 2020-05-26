package com.example.demo.service;

import java.util.Calendar;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationModel;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UserOperationServerException;
import com.example.demo.model.Response;
import com.example.demo.model.UpdatePasswordModel;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationRepository;

@Service
public class RegistrationServiceImplementation implements RegistrationService {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());  
	@Autowired
	private ModelMapper mapper;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationService verificationService ;
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public UserDto updatePhoneNumber(String userId, String phoneNumber) {
		// TODO Auto-generated method stub
		logger.info("inside updatePhoneNumber() :: phone Number :: {} at time {}", phoneNumber, Calendar.getInstance().getTime());
		User user = userRepository.findByUserId(userId);
		if(user != null) {
			 user.setPhoneNumber(phoneNumber);
			 userRepository.save(user);
			 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			 UserDto uDto = mapper.map(user, UserDto.class);
			 return uDto; 
		}
		
		throw new NotFoundException("user not found");   
	}

	
	@Override
	public Object saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Inside saveUser() :: user Details that need to be saved :: {} at time {}",userDto, Calendar.getInstance().getTime()); 
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		//checking whether the password and confirm password matches or not 
		if(userDto.getPassword().equals(userDto.getConfirmPassword())) {
			logger.info("password and confirm password matched at time {}", Calendar.getInstance().getTime());
			String email = userDto.getEmail();
			User userAlreadyExist = userRepository.findByEmail(email); //checking if user already exists
			
			if(userAlreadyExist == null) {
				
				userDto.setUserId(UUID.randomUUID().toString());
				userDto.setUserIsActive(false);
				User user = mapper.map(userDto, User.class);
				String bcryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
				user.setPassword(bcryptPassword);
				userRepository.save(user);
				//*****************verification entity is creating<START>***************
				Object result = verificationService.addVerificationDetails(user);
				//*****************verification entity is creating<END>*****************
				return result;
			}
			else if(userAlreadyExist.getUserIsActive() == false && userAlreadyExist.getVerificationToken().getStatus().equals(VerificationModel.STATUS_PENDING)) {
				
				throw new BadRequestException("User is registered but not verified");
			}
			
			logger.info("user already exists....!");
			throw new BadRequestException("User already exists");
		}

		    logger.info("Password didn't match");
			throw new BadRequestException("Password didn't match");
	}


	/*@Override
	public UserDto updateEmail(String userId, String email) {
		// TODO Auto-generated method stub
		logger.info("inside updateEmail() :: UUID :: {} at time {}", userId, Calendar.getInstance().getTime());
		User user = userRepository.findByUserId(userId);
		if(user != null) {
			 user.setEmail(email);
			 userRepository.save(user);
			 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			 UserDto uDto = mapper.map(user, UserDto.class);
			 return uDto;
		}
		
			throw new NotFoundException("user not found");   
	}*/


	


	@Override
	public Response deleteUser(String userId) {
		// TODO Auto-generated method stub
		logger.info("inside deleteUser() :: UUID :: {} at time {}", userId, Calendar.getInstance().getTime());
		User user = userRepository.findByUserId(userId);
		if(user!=null) {
			Long id = user.getId();
			userRepository.deleteById(id);
			
			if(userRepository.findByUserId(userId)==null) {
				return new Response("User deleted", true);
			}
			
			throw  new UserOperationServerException("User is not deleted due to server issue");
		} 
		
		throw new NotFoundException("User not found");
	}


	@Override
	public UserDto updatePassword(UpdatePasswordModel passwordModel) {
		// TODO Auto-generated method stub
		logger.info("inside updatePassword() at time {}", Calendar.getInstance().getTime());
		
		if(passwordModel.getPassword().equals(passwordModel.getConfirmPassword())){
			
			logger.info("password matches..");
			String userId = passwordModel.getUserId();
			User user = userRepository.findByUserId(userId);
			if(user!=null) {
				
				String bcryptPassword = bCryptPasswordEncoder.encode(passwordModel.getPassword());
				user.setPassword(bcryptPassword);
				userRepository.save(user);
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				UserDto uDto = mapper.map(user, UserDto.class);
				
				return uDto;
			}
			
			throw new NotFoundException("user not found");
		}
		
		throw new BadRequestException("Password didn't match");
	}
	
	
	public UserDto validateEmail(String token) {
	
		User verifiedUser = verificationService.validateEmail(token);
		logger.info("Register service :: user is verifed ");
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto uDto = mapper.map(verifiedUser, UserDto.class);
	   
		return uDto;
	    
	}
	
	
	public Object registerWithEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		if(user!=null && user.getUserIsActive()== false && user.getVerificationToken().getStatus().equals(VerificationModel.STATUS_PENDING)) {
			Object response = verificationService.updateToken(user.getVerificationToken(),user.getFirstName(),user.getLastName(),user.getEmail());
			return response;
		}
		throw new NotFoundException("user is not registered");
	}
	
	
	

	
}
