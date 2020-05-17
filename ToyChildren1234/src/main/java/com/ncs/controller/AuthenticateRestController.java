package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.input.LoginInput;
import com.ncs.serviceclient.AccountService;

@RestController
@RequestMapping(value = "/auth/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticateRestController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseData<String> generateToken(@RequestBody LoginInput input) {
		return accountService.generateToken(input);
	}
}
