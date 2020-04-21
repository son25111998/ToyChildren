package com.ncs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Coupon;
import com.ncs.repository.CouponRepository;

@Service
public class CouponService {
	@Autowired
	private CouponRepository couponRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	public ResponseData<Coupon> findByCode(String code){
		ResponseData<Coupon> response = new ResponseData<Coupon>();
		try {
			response.setData(couponRepository.findByCode(code));
		} catch (Exception e) {
			LOGGER.error("Api get coupon by code has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage("Phiếu giảm giá không hợp lệ");
		}
		return response;
	}
}
