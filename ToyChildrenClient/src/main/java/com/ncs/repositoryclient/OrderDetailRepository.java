package com.ncs.repositoryclient;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
	List<OrderDetail> findByOrderId(@Param("orderId") int orderId);
	
	@Query(value = "SELECT * FROM order_detail WHERE name_oder LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<OrderDetail> findAllOrder(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM order_detail WHERE name_oder  LIKE %:name%", nativeQuery = true)
    Page<OrderDetail> findNameOrder(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM order_detail WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<OrderDetail> findStatusOrder(@Param("status") String status, Pageable pageable);

	OrderDetail findById(int id);
	// List<OrderDetail> findByOderId(int oderId);
    Page<OrderDetail> findAll(Specification<OrderDetail> advanceFilter,Pageable pagerable);
}
