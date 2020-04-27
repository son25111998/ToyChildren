package com.ncs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.input.PayInput;
import com.ncs.service.PayService;

@RestController
@RequestMapping(value = "/customer/api/v1/pay", produces = MediaType.APPLICATION_JSON_VALUE)
public class PayRestController {
	@Autowired
	private PayService payService;

	@PostMapping
	public ResponseData<Object> pay(@RequestBody PayInput input, HttpServletRequest request) {
		return payService.pay(input, request);
	}
}
