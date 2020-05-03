package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.entity.Customer;
import com.ncs.service.CustomerService;

@RestController
@RequestMapping(value = "/security/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CustomerApiController {
	@Autowired
	private CustomerService customerService;
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping
	public ResponseData<Customer> getCustomerLogged() {
		return customerService.getCustomerLogged();
	}
}
