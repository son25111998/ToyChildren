package com.ncs.model.input;

import com.ncs.common.constants.Constants;

import lombok.Data;

@Data
public class PayInput {
	private int couponId = Constants.COUPON_ID_DEFAULT;
	private int taxId;
	private int shippingId;
	private String payment;
}
