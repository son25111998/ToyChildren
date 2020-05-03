package com.ncs.repositoryclient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Order;

@Repository
public interface OrderClientRepository extends JpaRepository<Order, Integer>{
	Order findById(int id);


    
}
