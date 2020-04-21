package com.ncs.model.output;

import java.util.List;

import lombok.Data;

@Data
public class ListMenuOutput {
	private int id;
	private String name;
	private int parentId;
	private List<ListMenuOutput> childrens;
	
	public ListMenuOutput(int id, String name, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
}
