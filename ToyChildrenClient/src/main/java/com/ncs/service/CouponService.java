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
		LOGGER.info(">>>>>>>>>>>findByCode Start >>>>>>>>>>>>");
		
		ResponseData<Coupon> response = new ResponseData<Coupon>();
		try {
			response.setData(couponRepository.findByCode(code));
		} catch (Exception e) {
			LOGGER.error("Api get coupon by code has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>findByCode End >>>>>>>>>>>>");
		return response;
	}
}
