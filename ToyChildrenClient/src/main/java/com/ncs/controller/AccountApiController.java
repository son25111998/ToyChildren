package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.common.util.Result;
import com.ncs.model.entity.Customer;
import com.ncs.model.input.AccountInput;
import com.ncs.service.AccountService;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class AccountApiController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public ResponseData<Customer> register(@RequestBody AccountInput account) {
		return accountService.register(account);
	}

	@GetMapping("/login")
	public ResponseData<Object> login() {
		return new ResponseData<Object>(Result.SUCCESS);
	}
}
