package com.ncs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.model.output.ListMenuOutput;
import com.ncs.service.CategoryService;

/**
 * @author: SonNc
 **/
@RestController
@RequestMapping(value = "/api/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseData<List<ListMenuOutput>> searchAllCategory() {
		return categoryService.getListCategory();
	}

	@GetMapping("{id}")
	public ResponseData<GetListProductOutput> findProductByCateogry(@PathVariable(name = "id") int categoryId,
			@RequestParam int page, @RequestParam int size) {
		return categoryService.findProductByCateogry(categoryId, page, size);
	}
}
