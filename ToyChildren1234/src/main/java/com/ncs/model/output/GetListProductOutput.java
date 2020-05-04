package com.ncs.model.output;

import java.util.List;

import com.ncs.model.entity.Product;

import lombok.Data;

@Data
public class GetListProductOutput {
	private List<Product> products;
	private Pagination pagination;
}
