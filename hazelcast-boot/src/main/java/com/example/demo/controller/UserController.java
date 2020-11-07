package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private Map<String, UserAccount> accountMap;
	
	@GetMapping(path = { "/get/{accountNumber}" })
	public UserAccount getUser(@PathVariable("accountNumber") String accountNumber) {
		
		//first check if accountMap has the userAccount details, if yes then return it. Else fetch it from database.
		UserAccount userAccount = (accountMap.get(accountNumber) != null) ? accountMap.get(accountNumber): userRepo.findByAccountNumber(accountNumber);;
		return userAccount;
	}

	@PostMapping("/add")
	public void createUser(@RequestBody UserAccount user) {
		//save user account in cache
		accountMap.put(user.getAccountNumber(), user);
		userRepo.save(user);
	}

	@DeleteMapping("/{accountNumber}")
	public Long deleteUser(@PathVariable("accountNumber") String accountNumber) {
		//remove from both cache and database
		accountMap.remove(accountNumber);
		return userRepo.deleteByAccountNumber(accountNumber);
	}

	

}
