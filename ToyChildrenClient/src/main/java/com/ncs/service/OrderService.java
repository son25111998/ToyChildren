package com.ncs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.dao.OrderDao;
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.output.GetListOrderOutput;
import com.ncs.model.output.OrderOutput;
import com.ncs.repository.OrderDetailRepository;
import com.ncs.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderDao orderDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String ORDER_FIELD = "Đơn hàng";

	public ResponseData<GetListOrderOutput> getListOrder(int page, int size, String date) {
		LOGGER.info(">>>>>>>>>>>getListOrder Start >>>>>>>>>>>>");
		ResponseData<GetListOrderOutput> response = new ResponseData<GetListOrderOutput>();
		try {
			// get data in db
			GetListOrderOutput output = orderDao.getListOrder(page, size, date);

			// set data order output
			List<OrderOutput> orderOutputs = output.getOrders();

			// set list order detail in order
			for (OrderOutput item : orderOutputs) {
				List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(item.getOrderId());
				item.setOrderDetails(orderDetails);
			}

			output.setOrders(orderOutputs);

			response.setData(output);
		} catch (Exception e) {
			LOGGER.error("Api get order has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListOrder End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Order> updateStatusOrder(int orderId) {
		LOGGER.info(">>>>>>>>>>>updateStatusOrder Start >>>>>>>>>>>>");
		ResponseData<Order> response = new ResponseData<Order>();
		try {
			// get data in db
			Order order = orderRepository.findById(orderId).orElse(null);

			// case order null or null empty
			if (ObjectUtils.isEmpty(order)) {
				LOGGER.error("{} {}", ORDER_FIELD, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(ORDER_FIELD + " " + Constants.RECORD_DO_NOT_EXIST);
			}

			// update status order
			if (order.getStatus() == Constants.STATUS_ACTIVE_VALUE) {
				order.setStatus(Constants.STATUS_INACTIVE_VALUE);
			} else {
				order.setStatus(Constants.STATUS_ACTIVE_VALUE);
			}

			orderRepository.save(order);

			response.setData(order);
		} catch (Exception e) {
			LOGGER.error("Api update status order has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>updateStatusOrder End >>>>>>>>>>>>");
		return response;
	}
}
