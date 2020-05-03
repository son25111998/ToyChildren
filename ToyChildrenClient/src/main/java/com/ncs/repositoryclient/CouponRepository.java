package com.ncs.repositoryclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	Coupon findByCode(String code);
}
