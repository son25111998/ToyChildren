package com.ncs.model.output;

import java.util.List;

import com.ncs.model.entity.Order;

import lombok.Data;

@Data
public class OrderOutput2 {
	List<Order> ordersPage;
	Pagination pageable;
	@SuppressWarnings("unused")
	private int totalPages;
	private Long totalElements;
	
	public int getTotalPages() {
		if(totalElements%pageable.getPageSize() == 0) {
			return (int) (totalElements/pageable.getPageSize());
		}
		return (int) (totalElements/pageable.getPageSize()) + 1;
	}
}
