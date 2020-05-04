package com.ncs.model.output;

import java.util.List;

import lombok.Data;

@Data
public class GetListOrderOutput {
	private List<OrderOutput> orders;
	private Pagination pagination;
}
