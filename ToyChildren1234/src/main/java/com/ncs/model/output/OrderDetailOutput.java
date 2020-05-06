package com.ncs.model.output;

import com.ncs.model.entity.Product;

import lombok.Data;

@Data
public class OrderDetailOutput {
	private int id;
	private int quantity;
	private String attribute;
	private Product product;
}
