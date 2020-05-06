package com.ncs.repositoryclient;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Order;

@Repository
<<<<<<< HEAD
public interface OrderClientRepository extends JpaRepository<Order, Integer>{
	Order findById(int id);


    
=======
public interface OrderClientRepository extends JpaRepository<Order, Integer> {
	Order findById(int id);
	
	Page<Order> findAll(Pageable pageable);
	
	Page<Order> findByCreateDate(Pageable pageable, Date createDate);
	
	List<Order> findByCreateDate( Date createDate);
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
