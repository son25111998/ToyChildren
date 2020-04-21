package com.ncs.model.dto;

import com.ncs.model.entity.Product;

import lombok.Data;

@Data
public class CartDto {
	private int cartId;
	private Product product;
	private int quantity;
}
