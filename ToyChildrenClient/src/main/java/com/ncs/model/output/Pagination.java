package com.ncs.model.output;

import lombok.Data;

@Data
public class Pagination {
	private int page;
	private int size;
	private int totalPage;
	private Long totalRecord;
	
	public int getTotalPage() {
		if(totalRecord%size == 0) {
			return (int) (totalRecord/size);
		}
		return (int) (totalRecord/size) + 1;
	}
}
