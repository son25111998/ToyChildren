package com.ncs.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.entity.Order;
import com.ncs.model.output.GetListOrderOutput;
import com.ncs.serviceclient.OrderService;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<GetListOrderOutput> getListOrder(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String date) {
		return orderService.getListOrder(page, size, date);
	}

	@PutMapping(value = "{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Order> updateStatusOrder(@PathVariable int orderId) {
		return orderService.updateStatusOrder(orderId);
	}

<<<<<<< HEAD
	@GetMapping(value = "export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
=======
	@GetMapping(value = "export", produces = "application/vnd.ms-excel;charset=UTF-8")
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	public void exportFileExcel(HttpServletResponse response,
			@RequestParam(required = false) String date) {
		orderService.exportFileExcel(response, date);
	}
}
