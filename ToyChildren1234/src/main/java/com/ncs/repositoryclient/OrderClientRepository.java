package com.ncs.repositoryclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Order;

@Repository
public interface OrderClientRepository extends JpaRepository<Order, Integer> {
	Order findById(int id);

}
