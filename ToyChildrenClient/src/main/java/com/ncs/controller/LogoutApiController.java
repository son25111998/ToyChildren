package com.ncs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.service.AccountService;

@RestController
@RequestMapping(value = "/member/logout", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class LogoutApiController {
	@Autowired
	private AccountService accountService;

	@GetMapping
	public ResponseData<Object> logout(HttpServletRequest request, HttpServletResponse response) {
		return accountService.logout(request, response);
	}
}
