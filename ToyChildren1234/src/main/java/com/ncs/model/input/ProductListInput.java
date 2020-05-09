package com.ncs.model.input;

import lombok.Data;

@Data
public class ProductListInput {
	private int page;
	private int size;
	private Integer priceStart;
	private Integer priceEnd;
	private Integer categoryId;
	private String search;
}
