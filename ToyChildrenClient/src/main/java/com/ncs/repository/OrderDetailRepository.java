package com.ncs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
	List<OrderDetail> findByOrderId(@Param("orderId") int orderId);
}