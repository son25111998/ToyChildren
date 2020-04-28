package com.ncs.model.output;

import lombok.Data;

@Data
public class ListMenuOutput {
	private int id;
	private String name;
	private int parentId;
	
	public ListMenuOutput(int id, String name, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
}
