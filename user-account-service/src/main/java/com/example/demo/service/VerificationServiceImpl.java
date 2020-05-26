package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.VerificationModel;
import com.example.demo.exception.TokenNotValidatedException;
import com.example.demo.feign.EmailProxy;
import com.example.demo.model.Response;
import com.example.demo.model.VerifyTokenRequest;
import com.example.demo.repository.VerificationRepository;

@Service
public class VerificationServiceImpl implements VerificationService {

	private Logger logger = LoggerFactory.getLogger(VerificationServiceImpl.class);
	
	@Autowired
	private VerificationRepository verifyRepo;
	
	@Autowired
	private EmailProxy emailService;
	
	public Object addVerificationDetails(User user) {
		
		logger.info("Verification Service :: inside addVerificationDetails()");
		VerificationModel verifyUser = new VerificationModel();
		verifyUser.setUser(user);
		verifyRepo.save(verifyUser);
		VerifyTokenRequest tokenRequest = new VerifyTokenRequest(user.getFirstName(), user.getLastName(), user.getEmail(), "Verify Your Account", verifyUser.getToken());
		Object message = (Object)emailService.sendEmailforverificationToken(tokenRequest).getBody();
		
		return message;
	}
	
	
	public User validateEmail(String token) {
		logger.info("Verification Service :: inside validateEmail()");
		 VerificationModel verificationInfo = verifyRepo.findByToken(token);
		
		 if(verificationInfo != null) {
			 
			logger.info("user is present with given token");
			
			if(verificationInfo.getStatus().equals(VerificationModel.STATUS_PENDING) && verificationInfo.getUser().getUserIsActive()== false) {
				
				logger.info("Verification Service :: checking whether token is expired or not");
				if(verificationInfo.getExpiredDateTime().isBefore(LocalDateTime.now())) {
					logger.info("Verification Service :: token is expired");
					
					throw new TokenNotValidatedException("Token is expired");
				}
				else {
					
					logger.info("Verification Service :: token is validated");
					verificationInfo.setConfirmedDateTime(LocalDateTime.now());
					verificationInfo.getUser().setUserIsActive(true);
					verificationInfo.setStatus(VerificationModel.STATUS_VERIFIED);
					verifyRepo.save(verificationInfo);
					
					return verificationInfo.getUser();
				}
			}
			
			throw new TokenNotValidatedException("User is already verified");
		 }
		 
		 throw new TokenNotValidatedException("Not a valid token, try again");
		
		}


	@Override
	public Object updateToken(VerificationModel verificationModel, String fName,String lName,String email) {
		// TODO Auto-generated method stub
		verificationModel.setToken(UUID.randomUUID().toString());
		verificationModel.setIssuedDateTime(LocalDateTime.now());
		verificationModel.setExpiredDateTime(LocalDateTime.now().plusSeconds(1*60*60*3));
		verifyRepo.save(verificationModel);
		
		VerifyTokenRequest tokenRequest = new VerifyTokenRequest(fName, lName, email, "Verify Your Account", verificationModel.getToken());
		Object message = (Object)emailService.sendEmailforverificationToken(tokenRequest).getBody();
		
		return message;
	}
		
}
