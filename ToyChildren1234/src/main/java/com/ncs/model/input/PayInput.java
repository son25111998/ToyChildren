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
<<<<<<< HEAD
	private String payment;
=======
	private int payment;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private List<CartDto> carts;
}
