package com.ncs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Coupon;
import com.ncs.repository.CouponRepository;

@Service
public class CouponService {
	@Autowired
	private CouponRepository couponRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String COUPON_FIELD = "Mã giảm giá";
	
	public ResponseData<Coupon> findByCode(String code){
		LOGGER.info(">>>>>>>>>>>findByCode Start >>>>>>>>>>>>");
		
		ResponseData<Coupon> response = new ResponseData<Coupon>();
		try {
			Coupon coupon = couponRepository.findByCode(code);
			if(ObjectUtils.isEmpty(coupon)) {
				LOGGER.error("{} {}",COUPON_FIELD, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(COUPON_FIELD + " " + Constants.RECORD_DO_NOT_EXIST);
			}
			
			response.setData(coupon);
		} catch (Exception e) {
			LOGGER.error("Api get coupon by code has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>findByCode End >>>>>>>>>>>>");
		return response;
	}
}
