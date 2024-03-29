package com.ncs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.entity.Shipping;
import com.ncs.serviceclient.ShippingService;

@RestController
@RequestMapping(value = "/shipping", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ShippingRestController {
	@Autowired
	private ShippingService shippingService;
	
	@GetMapping
	public ResponseData<List<Shipping>> getListShipping(){
		return shippingService.getListShipping();
	}
	
	@GetMapping("{id}")
	public ResponseData<Shipping> getShippingById(@PathVariable int id){
		return shippingService.getShippingById(id);
	}
}
