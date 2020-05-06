package com.ncs.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ncs.entity.OrderDetailEntity;
<<<<<<< HEAD
import com.ncs.entity.OrderEntity;
import com.ncs.repository.OrderDetailRepository;
import com.ncs.service.OrderDetailService;
import com.ncs.service.OrderService;
import com.ncs.specifications.OrderDetailSpecifications;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	private Logger log = Logger.getLogger(OrderDetailServiceImpl.class);
=======
import com.ncs.repository.OrderDetailRepository;
import com.ncs.service.OrderDetailService;
import com.ncs.specifications.OrderDetailSpecifications;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	private Logger log = Logger.getLogger(OrderDetailServiceImpl.class);

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public Page<OrderDetailEntity> findPaging(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public int create(OrderDetailEntity order) {
		try {
			OrderDetailEntity orderEntityeExisting = orderDetailRepository.findById(order.getId());
			if (orderEntityeExisting != null) {
				return 0;
			} else {
				orderDetailRepository.save(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return 0;
		}
		return 1;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public List<OrderDetailEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public OrderDetailEntity findOne(int id) {
		OrderDetailEntity order = null;
		try {
			order = orderDetailRepository.findById(id);
		} catch (Exception e) {
<<<<<<< HEAD
		
			log.error(e.getMessage());
		}
		return order; 
	}
=======

			log.error(e.getMessage());
		}
		return order;
	}

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public int update(OrderDetailEntity order) {
		// TODO Auto-generated method stub
		return 0;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public int deleteAllBatch(Iterable<OrderDetailEntity> order) {
		// TODO Auto-generated method stub
		return 0;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public Page<OrderDetailEntity> searchAllOrder(String name, String status, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public Page<OrderDetailEntity> searchNameOrder(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public Page<OrderDetailEntity> search(OrderDetailEntity order, Pageable pageable) {
		Page<OrderDetailEntity> orders = null;
		try {
			orders = orderDetailRepository.findAll(OrderDetailSpecifications.advanceFilter(order), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return orders;
	}
<<<<<<< HEAD
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	@Override
	public List<OrderDetailEntity> findbyOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

}
