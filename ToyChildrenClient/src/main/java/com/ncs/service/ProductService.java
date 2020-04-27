package com.ncs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		LOGGER.info(">>>>>>>>>>>getListProducts Start >>>>>>>>>>>>");
		
		ResponseData<GetListProductOutput> response = new ResponseData<>();
		try {
			if (StringUtils.isEmpty(page)) {
				page = Constants.PAGE_DEFAULT;
			}

			if (StringUtils.isEmpty(page)) {
				size = Constants.SIZE_DEFAULT;
			}

			response.setData(productDao.getListProduct(page, size, search));
		} catch (Exception e) {
			LOGGER.error("Api get list product has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>getListProducts End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Product> getProductInfo(int productId) {
		LOGGER.info(">>>>>>>>>>>getProductInfo Start >>>>>>>>>>>>");
		ResponseData<Product> response = new ResponseData<>();
		try {
			response.setData(productRepository.findById(productId).get());
		} catch (Exception e) {
			LOGGER.error("Api get product detail has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>getProductInfo End >>>>>>>>>>>>");
		return response;
	}
}
