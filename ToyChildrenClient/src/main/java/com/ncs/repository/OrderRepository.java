package com.ncs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query(value = "SELECT * FROM orderproduct WHERE name_oder LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<Order> findAllOrder(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM orderproduct WHERE name_oder  LIKE %:name%", nativeQuery = true)
    Page<Order> findNameOrder(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM orderproduct WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<Order> findStatusOrder(@Param("status") String status, Pageable pageable);

	Order findById(int id);

    Page<Order> findAll(Specification<Order> advanceFilter,Pageable pagerable);
    
}
