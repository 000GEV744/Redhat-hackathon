package com.example.demo.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.model.Response;
import com.example.demo.model.VerifyTokenRequest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private Configuration config;
	private Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	
	public Response sendEmail(VerifyTokenRequest tokenRequest, Map<String, Object> model) {
		
		Response response = new Response();
		MimeMessage message = sender.createMimeMessage();
		try {
			//set Media Type 
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		Template t = config.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		helper.setTo(tokenRequest.getTo());
		helper.setText(html, true);
		helper.setSubject(tokenRequest.getSubject());
		sender.send(message);
		logger.info("Email service :: sending email..");
		response.setMessage("email sent");
		response.setStatus(true);
		
		return response;
		
		}catch (MessagingException | IOException | TemplateException e) {
			// TODO: handle exception
			response.setMessage("email not sent");
			response.setStatus(false);
			
			return response;
		}
	}
	

	public Map<String, Object> settingEmailTemplateModel(VerifyTokenRequest tokenRequest){
		
		logger.info("Email service :: setting up the template model..");
		Map<String, Object> model = new HashMap<>();
		model.put("fName", tokenRequest.getFname());
		model.put("lName", tokenRequest.getLname());
		model.put("location", "Banglore, India");
		model.put("token", tokenRequest.getToken());
		
		return model;
	}
	

}
