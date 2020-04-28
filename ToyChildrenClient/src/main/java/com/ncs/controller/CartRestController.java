package com.ncs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.dto.CartDto;
import com.ncs.model.input.CartInput;
import com.ncs.service.CartService;

@RestController
@RequestMapping(value = "/api/v1/cart", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class CartRestController {
	@Autowired
	private CartService cartService;

	@GetMapping
	public ResponseData<List<CartDto>> view(HttpServletRequest request) {
		return cartService.getListCart(request);
	}

	@PostMapping
	public ResponseData<CartDto> addProductToCart(@RequestBody CartInput input, HttpServletRequest request) {
		return cartService.addProductToCart(input, request);
	}

	@PutMapping("{cartId}")
	public ResponseData<CartDto> updateCart(@PathVariable("cartId") int cartId, HttpServletRequest request) {
		return cartService.updateCart(cartId, request);
	}

	@DeleteMapping("{cartId}")
	public ResponseData<Object> deleteProductOutCart(@PathVariable("cartId") int cartId, HttpServletRequest request) {
		return cartService.deleteProductOutCart(cartId, request);
	}
}
