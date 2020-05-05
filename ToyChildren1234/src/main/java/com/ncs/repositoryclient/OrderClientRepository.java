package com.ncs.repositoryclient;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Order;

@Repository
public interface OrderClientRepository extends JpaRepository<Order, Integer> {
	Order findById(int id);
	
	Page<Order> findAll(Pageable pageable);
	
	Page<Order> findByCreateDate(Pageable pageable, Date createDate);
}
