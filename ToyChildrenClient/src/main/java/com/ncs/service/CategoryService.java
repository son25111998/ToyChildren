package com.ncs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Category;
import com.ncs.model.output.ListMenuOutput;
import com.ncs.repository.CategoryRepository;

@Service
public class CategoryService {
	private static final int PARENT_ID_VALUE = 1;
	private static final int STATUS_ACTIVE = 1;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public ResponseData<List<ListMenuOutput>> getListCategory() {
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
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}

	private ListMenuOutput convertCategoryToListMenuOutput(Category category) {
		if (ObjectUtils.isEmpty(category))
			return null;

		return new ListMenuOutput(category.getId(), category.getName(), category.getParentCategoryId());
	}
}
