package com.ncs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ncs.entity.OrderDetailEntity;
<<<<<<< HEAD
import com.ncs.entity.OrderEntity;
import com.ncs.entity.ProductEntity;;
=======
import com.ncs.entity.OrderEntity;;

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
@Service
public interface OrderService {
	Page<OrderEntity> findPaging(Pageable pageable);

	int create(OrderEntity order);

	List<OrderEntity> findAll();

	OrderEntity findOne(int id);

	int update(OrderEntity order);

	int delete(int id);

	int deleteAllBatch(Iterable<OrderEntity> order);

	Page<OrderEntity> searchAllOrder(String name, String status, Pageable pageable);

	Page<OrderEntity> searchNameOrder(String name, Pageable pageable);

<<<<<<< HEAD
	//Page<CategoryEntity> searchStatusProduct(String status, Pageable pageable);

	Page<OrderEntity> search(OrderEntity order, Pageable pageable);
=======
	// Page<CategoryEntity> searchStatusProduct(String status, Pageable pageable);

	Page<OrderEntity> search(OrderEntity order, Pageable pageable);

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	List<OrderDetailEntity> getListOrderDetail(int orderId);
}
