package com.ncs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.entity.Account;
import com.ncs.model.entity.Customer;
import com.ncs.model.input.AccountInput;
import com.ncs.service.AccountService;

@RestController
@RequestMapping(value = "/api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AccountRestController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public ResponseData<Customer> register(@RequestBody AccountInput account) {
		System.out.println("input account = " + account);
		return accountService.register(account);
	}

	@GetMapping("/login")
	public ResponseData<Account> login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		ResponseData<Account> responseData = accountService.login(email, password);
		if (responseData.getCode() == 200)
			session.setAttribute("account", responseData.getData());
		return responseData;
	}
}
