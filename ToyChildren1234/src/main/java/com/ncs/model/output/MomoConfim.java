package com.ncs.model.output;

import lombok.Data;

@Data
public class MomoConfim {
	private String partnerCode;
	private String partnerRefId;
	private String requestType;
	private String requestId;
	private String momoTransId;
	private String signature;
	private String customerNumber;
	private String description;
}
