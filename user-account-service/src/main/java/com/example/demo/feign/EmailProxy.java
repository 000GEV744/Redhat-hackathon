package com.example.demo.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.VerifyTokenRequest;

@FeignClient(name = "email-service", url = "http://localhost:8006/")
public interface EmailProxy{

	@PostMapping("/send-verification-token")
	public ResponseEntity<?> sendEmailforverificationToken(@RequestBody VerifyTokenRequest tokenRequest);
}
