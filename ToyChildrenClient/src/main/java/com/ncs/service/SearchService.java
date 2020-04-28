package com.ncs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Product;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.model.output.Pagination;
import com.ncs.repository.ProductRepository;

@Service
public class SearchService {
	@Autowired
	private ProductRepository productRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public ResponseData<GetListProductOutput> findProductByName(int page, int size, String search) {
		ResponseData<GetListProductOutput> response = new ResponseData<>();
		try {
			GetListProductOutput productOutput = new GetListProductOutput();
			Pagination pagination = new Pagination();

			// set default page and size
			if (page < 0)
				page = 1;
			if (size < 0)
				size = Constants.SIZE_DEFAULT;

			Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());

			Page<Product> products = productRepository.findByNameLike(pageable, search);

			productOutput.setProducts(products.toList());

			// set value in pagination
			pagination.setPage(page);
			pagination.setSize(size);
			pagination.setTotalRecord(products.getTotalElements());
			productOutput.setPagination(pagination);

			response.setData(productOutput);
		} catch (Exception e) {
			LOGGER.error("Api search has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		return response;
	}
}
