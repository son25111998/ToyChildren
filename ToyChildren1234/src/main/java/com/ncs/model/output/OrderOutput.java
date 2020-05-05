package com.ncs.model.output;

import java.util.Date;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.ncs.model.entity.Coupon;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.entity.Shipping;
import com.ncs.model.entity.Tax;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderOutput {
	private int orderId;
	private String customerName;
	private Date createDate;
	private String phone;
	private List<OrderDetail> orderDetails;
	private int payment;
	private Shipping shipping;
	private Tax tax;
	private Coupon counpon;
	private int status;
	
	@SuppressWarnings("unused")
	private int money;
	

	public int getMoney() {
		int sum = 0;
		if (ObjectUtils.isEmpty(orderDetails))
			return 0;

		for (OrderDetail orderDetail : orderDetails) {
			sum += (orderDetail.getProduct().getPrice() - orderDetail.getProduct().getDiscount())
					* orderDetail.getQuantity();
		}

		return (sum - counpon.getSale() - shipping.getCost() - Math.round(sum * tax.getPercentage()/100));
	}

}
