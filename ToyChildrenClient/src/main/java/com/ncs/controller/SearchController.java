package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.service.SearchService;

/**
 * @author: SonNc
 **/
@RestController
@RequestMapping(value = "/api/v1/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {
	@Autowired
	private SearchService searchService;

	@GetMapping
	public ResponseData<GetListProductOutput> findProductByName(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {
		return searchService.findProductByName(page, size,search);
	}
}
