package com.ncs.model.output;

import lombok.Data;

@Data
public class Pagination {
	private int page;
	private int size;
	@SuppressWarnings("unused")
	private int totalPage;
	private Long totalRecord;
	
	public int getTotalPages() {
		if(totalRecord%size == 0) {
			return (int) (totalRecord/size);
		}
		return (int) (totalRecord/size) + 1;
	}
}
