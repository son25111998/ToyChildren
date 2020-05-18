package com.ncs.controller;

import java.util.List;

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
import com.ncs.model.input.OderInput;
import com.ncs.model.output.OrderOutput2;
import com.ncs.serviceclient.OrderService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderRestController {

	@Autowired
	private OrderService orderService;

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseData<Page<Order>> getListOrder(@RequestParam int page, @RequestParam int size,
//			@RequestParam(required = false) String date) {
//		return orderService.getListOrder(page, size, date);
//	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<OrderOutput2> getListOrder(@ApiParam OderInput input) {
		return orderService.getListOrder(input);
	}

	@GetMapping("statistic/money")
	public ResponseData<List<Long>> getListMoneyByMonth() {
		return orderService.getListMoneyByMonth();
	}

	@GetMapping("{id}")
	public ResponseData<Order> getOrderById(@PathVariable(name = "id") int id) {
		return orderService.getOrderById(id);
	}

	@PutMapping(value = "{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Order> updateStatusOrder(@PathVariable int id, @PathVariable int status) {
		return orderService.updateStatusOrder(id, status);
	}

	@GetMapping(value = "export", produces = "application/vnd.ms-excel;charset=UTF-8")
	public void exportFileExcel(HttpServletResponse response, @RequestParam(required = false) String date) {
		orderService.exportFileExcel(response, date);
	}
}
