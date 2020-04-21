package com.ncs.model.dto;

import java.io.Serializable;

import com.ncs.model.entity.Product;

import lombok.Data;

@Data
public class CartDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int cartId;
	private Product product;
	private int quantity;
}
