package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.entity.Customer;
import com.ncs.model.input.AccountInput;
import com.ncs.service.AccountService;

@RestController
@RequestMapping(value = "/api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public ResponseData<Customer> getListProduct(@RequestBody AccountInput account) {
		return accountService.register(account);
	}
}
