package com.ncs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.dto.CartDto;
import com.ncs.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {
	@Autowired
	private CartService cartService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<List<CartDto>> view(HttpServletRequest request) {
		return cartService.getListCart(request);
	}

	@PostMapping("add")
	public ResponseData<Object> addProductToCart(@RequestBody CartDto cart, HttpServletRequest request) {
		return cartService.addProductToCart(cart, request);
	}

//	@DeleteMapping("/{cartId}")
//	public String deleteProductOutCart(@PathVariable("cartId") int cartId, HttpServletRequest request,
//			ModelMap modelMap) {
//		return cartService.deleteProductOutCart(cartId, request, modelMap);
//	}
}
