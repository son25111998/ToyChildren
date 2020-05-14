package com.ncs.model.output;

import lombok.Data;

@Data
public class MomoQRResponse {
	private Integer status;
    private String signature;
    private Long amount;
    private String partnerRefId;
    private String momoTransId;
    private String message;
}
