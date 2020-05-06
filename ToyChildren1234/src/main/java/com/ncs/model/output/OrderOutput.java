package com.ncs.model.output;

import java.util.Date;
import java.util.List;

import org.springframework.util.ObjectUtils;

<<<<<<< HEAD
import com.ncs.model.entity.OrderDetail;
=======
import com.ncs.model.entity.Coupon;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.entity.Shipping;
import com.ncs.model.entity.Tax;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderOutput {
	private int orderId;
<<<<<<< HEAD
	private int status;
	private int sale;
	private int shippingCost;
	@SuppressWarnings("unused")
	private int money;
	private Float taxPercentage;
	private String payment;
	private String shippingName;
	private String customerName;
	private String phone;
	private Date createDate;
	private List<OrderDetail> orderDetails;

	public OrderOutput(int orderId, int status, int sale, int shippingCost, Float taxPercentage, String payment,
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
=======
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
		int result;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		if (ObjectUtils.isEmpty(orderDetails))
			return 0;

		for (OrderDetail orderDetail : orderDetails) {
			sum += (orderDetail.getProduct().getPrice() - orderDetail.getProduct().getDiscount())
					* orderDetail.getQuantity();
		}

<<<<<<< HEAD
		return (sum - sale - shippingCost - Math.round(sum * taxPercentage/100));
=======
		result = (sum - counpon.getSale() - shipping.getCost() - Math.round(sum * tax.getPercentage() / 100));

		if (result < 0)
			result = 0;
		
		return result;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	}

}
