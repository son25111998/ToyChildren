package com.ncs.serviceclient;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.dao.ProductDao;
import com.ncs.model.entity.Product;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.model.output.Pagination;
import com.ncs.repositoryclient.ProductRepository;

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

	public ResponseData<GetListProductOutput> getListProductsNew(int page, int size) {
		LOGGER.info(">>>>>>>>>>>getListProductsNew Start >>>>>>>>>>>>");

		ResponseData<GetListProductOutput> response = new ResponseData<>();
		try {
			GetListProductOutput productOutput = new GetListProductOutput();
			Pagination pagination = new Pagination();
			Calendar calendar = Calendar.getInstance();

			if (StringUtils.isEmpty(page)) {
				page = Constants.PAGE_DEFAULT;
			}

			if (StringUtils.isEmpty(page)) {
				size = Constants.SIZE_DEFAULT;
			}

			// date now - 1 month
			calendar.add(Calendar.MONTH, -1);

			Pageable pageable = PageRequest.of(page-1, size, Sort.by("createTime").descending());

			Page<Product> products = productRepository.findByCreateTimeBetween(calendar.getTime(),
					Calendar.getInstance().getTime(), pageable);

			pagination.setPage(page);
			pagination.setSize(size);
			pagination.setTotalRecord(products.getTotalElements());

			productOutput.setProducts(products.toList());
			productOutput.setPagination(pagination);

			response.setData(productOutput);
		} catch (Exception e) {
			LOGGER.error("Api get list product new has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>getListProductsNew End >>>>>>>>>>>>");
		return response;
	}
	
	public ResponseData<List<Product>> getListProductsHot() {
		LOGGER.info(">>>>>>>>>>>getListProductsHot Start >>>>>>>>>>>>");
		ResponseData<List<Product>> response = new ResponseData<>();
		try {
			//TODO:{
//			Pagination pagination = new Pagination();
		} catch (Exception e) {
			LOGGER.error("Api get list product hot has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>getListProductsHot End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Product> getProductInfo(int productId) {
		LOGGER.info(">>>>>>>>>>>getProductInfo Start >>>>>>>>>>>>");
		ResponseData<Product> response = new ResponseData<>();
		try {
			response.setData(productRepository.findById(productId));
		} catch (Exception e) {
			LOGGER.error("Api get product detail has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getProductInfo End >>>>>>>>>>>>");
		return response;
	}
}
