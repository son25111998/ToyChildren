package com.ncs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer>{

}
