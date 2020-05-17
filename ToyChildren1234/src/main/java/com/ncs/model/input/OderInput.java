package com.ncs.model.input;

import lombok.Data;

@Data
public class OderInput {
	Integer page;
	Integer size;
	Integer categoryId;
	Integer status;
	Integer manufacturerId;
	String productName;
	String date;
}
