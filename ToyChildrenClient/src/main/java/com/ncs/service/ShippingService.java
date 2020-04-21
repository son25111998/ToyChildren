package com.ncs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Shipping;
import com.ncs.repository.ShippingRepository;

@Service
public class ShippingService {
	@Autowired
	private ShippingRepository shippingRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	public ResponseData<List<Shipping>> getListShipping(){
		LOGGER.info(">>>>>>>>>>>getListShipping Start >>>>>>>>>>>>");
		
		ResponseData<List<Shipping>> response = new ResponseData<List<Shipping>>();
		try {
			response.setData(shippingRepository.findAll());
		} catch (Exception e) {
			LOGGER.error("Api get list shipping has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>getListShipping Start >>>>>>>>>>>>");
		return response;
	}
	
	
	public ResponseData<Shipping> getShippingById(int id){
		LOGGER.info(">>>>>>>>>>>getShippingById Start >>>>>>>>>>>>");
		
		ResponseData<Shipping> response = new ResponseData<Shipping>();
		try {
			response.setData(shippingRepository.findById(id).get());
		} catch (Exception e) {
			LOGGER.error("Api get list shipping has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>getListShipping Start >>>>>>>>>>>>");
		return response;
	}
}
