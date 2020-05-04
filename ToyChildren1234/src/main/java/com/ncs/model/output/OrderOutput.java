package com.ncs.model.output;

import java.util.Date;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.ncs.model.entity.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderOutput {
	private int orderId;
	private int status;
	private int sale;
	private int shippingCost;
	@SuppressWarnings("unused")
	private int money;
	private Float taxPercentage;
	private int payment;
	private String shippingName;
	private String customerName;
	private String phone;
	private Date createDate;
	private List<OrderDetail> orderDetails;

	public OrderOutput(int orderId, int status, int sale, int shippingCost, Float taxPercentage, int payment,
			String shippingName, String customerName, String phone, Date createDate) {
		this.orderId = orderId;
		this.status = status;
		this.sale = sale;
		this.shippingCost = shippingCost;
		this.taxPercentage = taxPercentage;
		this.payment = payment;
		this.shippingName = shippingName;
		this.customerName = customerName;
		this.phone = phone;
		this.createDate = createDate;
	}

	public int getMoney() {
		int sum = 0;
		if (ObjectUtils.isEmpty(orderDetails))
			return 0;

		for (OrderDetail orderDetail : orderDetails) {
			sum += (orderDetail.getProduct().getPrice() - orderDetail.getProduct().getDiscount())
					* orderDetail.getQuantity();
		}

		return (sum - sale - shippingCost - Math.round(sum * taxPercentage/100));
	}

}
