package com.ncs.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.ncs.model.entity.OrderDetail;
import com.ncs.model.output.OrderDetailOutput;

public class OrderDetailConverter {
	public static List<OrderDetailOutput> convertToListOrderDetailOutput(List<OrderDetail> orderDetails) {
		List<OrderDetailOutput> orderDetailOutputs = new ArrayList<>();
		if (orderDetails != null) {
			orderDetails.forEach(orderDetail -> {
				orderDetailOutputs.add(convertToOrderDetailOutput(orderDetail));
			});
		}
		return orderDetailOutputs;
	}

	public static OrderDetailOutput convertToOrderDetailOutput(OrderDetail orderDetail) {
		if (orderDetail != null) {
			OrderDetailOutput output = new OrderDetailOutput();

			output.setId(orderDetail.getId());
			output.setQuantity(orderDetail.getQuantity());
			output.setAttribute(orderDetail.getAttribute());
			output.setProduct(orderDetail.getProduct());
			return output;
		}
		return null;
	}

}
