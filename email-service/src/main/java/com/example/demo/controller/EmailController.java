package com.example.demo.controller;

import java.util.Map;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Response;
import com.example.demo.model.VerifyTokenRequest;
import com.example.demo.service.EmailService;

@RestController
public class EmailController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailService eService;

	
	@PostMapping("/send-verification-token")
	public ResponseEntity<?> sendEmailforverificationToken(@RequestBody VerifyTokenRequest tokenRequest){
		
		logger.info("Email service :: inside email verification...");
		Map<String, Object> model = eService.settingEmailTemplateModel(tokenRequest);
		Response result = eService.sendEmail(tokenRequest, model);
		
		return ResponseEntity.ok(result); 
	}
}
