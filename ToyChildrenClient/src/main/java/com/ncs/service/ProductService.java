package com.ncs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.dao.ProductDao;
import com.ncs.model.entity.Product;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductDao productDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	public ResponseData<GetListProductOutput> getListProducts(int page, int size, String search) {
		ResponseData<GetListProductOutput> response = new ResponseData<>();
		try {
			response.setData(productDao.getListProduct(page, size, search));
		} catch (Exception e) {
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}
	
	public ResponseData<Product> getProductInfo(int productId) {
		ResponseData<Product> response = new ResponseData<>();
		try {
			response.setData(productRepository.findById(productId).get());
		} catch (Exception e) {
			LOGGER.error("Api get product detail has exception : " + e.getMessage());
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}
}
