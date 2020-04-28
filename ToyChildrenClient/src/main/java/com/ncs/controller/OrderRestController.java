package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.entity.Order;
import com.ncs.model.output.GetListOrderOutput;
import com.ncs.service.OrderService;

@RestController
@RequestMapping(value = "/customer/api/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseData<GetListOrderOutput> getListOrder(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String date) {
		return orderService.getListOrder(page, size, date);
	}
	
	@PutMapping("{orderId}")
	public ResponseData<Order> updateStatusOrder(@PathVariable int orderId){
		return orderService.updateStatusOrder(orderId);
	}
}