package com.ncs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Category;
import com.ncs.model.entity.Product;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.model.output.ListMenuOutput;
import com.ncs.model.output.Pagination;
import com.ncs.repository.CategoryRepository;
import com.ncs.repository.ProductRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	private static final String CATEGORY_FIELD = "Danh mục sản phẩm";
	private static final int PARENT_ID_VALUE = 0;
	private static final int STATUS_ACTIVE = 1;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public ResponseData<List<ListMenuOutput>> getListCategory() {
		LOGGER.info(">>>>>>>>>>>getListCategory Start >>>>>>>>>>>>");
		ResponseData<List<ListMenuOutput>> response = new ResponseData<>();
		try {
			List<ListMenuOutput> menuParents = new ArrayList<ListMenuOutput>();

			List<Category> categories = categoryRepository.findAll();

			categories.forEach(category -> {
				if (category.getParentCategoryId() == PARENT_ID_VALUE && category.getStatus() == STATUS_ACTIVE) {
					menuParents.add(convertCategoryToListMenuOutput(category));
				}
			});

			for (ListMenuOutput menu : menuParents) {
				List<ListMenuOutput> menuChildrens = new ArrayList<ListMenuOutput>();

				for (Category category : categories) {

					// check menu parentId = menuInfo menusId
					if (category.getParentCategoryId() == menu.getId()) {

						// add menu in child menu
						menuChildrens.add(convertCategoryToListMenuOutput(category));
					}
				}

				// add child menu in list menus
				menu.setChildrens(menuChildrens);
			}

			response.setData(menuParents);
		} catch (Exception e) {
			LOGGER.error("Api get list category has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListCategory End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<GetListProductOutput> findProductByCateogry(int categoryId, int page, int size) {
		LOGGER.info("findProductByCateogry Start");
		LOGGER.info("categoryId: {}, page : {}, size : {}", categoryId, page, size);
		ResponseData<GetListProductOutput> response = new ResponseData<>();
		try {
			GetListProductOutput productOutput = new GetListProductOutput();
			Pagination pagination = new Pagination();

			// get category by id
			Category category = categoryRepository.findById(categoryId);

			// case category null or empty
			if (ObjectUtils.isEmpty(category)) {
				LOGGER.error("{} {}", CATEGORY_FIELD, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(Constants.UNKNOWN_ERROR_MSG);
				return response;
			}

			// set default page and size
			if (page < 0)
				page = 1;
			if (size < 0)
				size = Constants.SIZE_DEFAULT;

			Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());

			Page<Product> products = productRepository.findByCategory(category, pageable);

			productOutput.setProducts(products.toList());

			// set value in pagination
			pagination.setPage(page);
			pagination.setSize(size);
			pagination.setTotalRecord(products.getTotalElements());
			productOutput.setPagination(pagination);

			response.setData(productOutput);
		} catch (Exception e) {
			LOGGER.error("Api get list product by category has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info("findProductByCateogry End");
		return response;
	}

	private ListMenuOutput convertCategoryToListMenuOutput(Category category) {
		if (ObjectUtils.isEmpty(category))
			return null;

		return new ListMenuOutput(category.getId(), category.getName(), category.getParentCategoryId());
	}
}
