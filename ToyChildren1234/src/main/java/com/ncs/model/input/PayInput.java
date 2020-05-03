package com.ncs.model.input;

import java.util.List;

import com.ncs.common.constants.Constants;
import com.ncs.model.dto.CartDto;

import lombok.Data;

@Data
public class PayInput {
	private int couponId = Constants.COUPON_ID_DEFAULT;
	private int taxId = Constants.TAX_ID_DEFAULT;
	private int shippingId;
	private String payment;
	private List<CartDto> carts;
}
