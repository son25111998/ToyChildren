package com.ncs.repositoryclient;

import java.util.List;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.ncs.model.entity.OrderDetail;

@Repository
public interface OrderDetailClientRepository extends JpaRepository<OrderDetail, Integer>{
	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
	List<OrderDetail> findByOrderId(@Param("orderId") int orderId);
	
=======
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;

@Repository
public interface OrderDetailClientRepository extends JpaRepository<OrderDetail, Integer> {

	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
	List<OrderDetail> findByOrderId(@Param("orderId") int orderId);
	
	List<OrderDetail> findByOrder(Order order);
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

}
