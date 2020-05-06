package com.ncs.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.common.util.Result;
import com.ncs.entity.ShoppingCartDetailEntity;
import com.ncs.entity.ShoppingCartEntity;
import com.ncs.service.ShoppingCartService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/api/v1/categories")
public class ShoppingCartResource {
	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart/search/{name}/{status}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ShoppingCartDetailEntity>> searchAllShoppingCart(@PathVariable String name,
															  @PathVariable String status, Pageable pageable) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ShoppingCartDetailEntity>> searchAllShoppingCart(@PathVariable String name,
			@PathVariable String status, Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ShoppingCartDetailEntity>> response = new ResponseData<>();

		Page<ShoppingCartDetailEntity> shoppingCarts = null;

		try {
			shoppingCarts = shoppingCartService.searchAllShoppingCart(name, status, pageable);
			response.setData(shoppingCarts);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}

<<<<<<< HEAD

	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ShoppingCartDetailEntity>> searchNameShoppingCart(@PathVariable String name,
                                                               Pageable pageable) {
=======
	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ShoppingCartDetailEntity>> searchNameShoppingCart(@PathVariable String name,
			Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ShoppingCartDetailEntity>> response = new ResponseData<>();

		Page<ShoppingCartDetailEntity> shoppingCarts = null;

		try {
			shoppingCarts = shoppingCartService.searchNameShoppingCart(name, pageable);
			response.setData(shoppingCarts);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
<<<<<<< HEAD
    //testok
//	@GetMapping(value = "/shoppingCart-management/managed-shoppingCart/search-status/{status}", produces = {
//			MediaType.APPLICATION_JSON_UTF8_VALUE })
=======

	// testok
//	@GetMapping(value = "/shoppingCart-management/managed-shoppingCart/search-status/{status}", produces = {
//			MediaType.APPLICATION_JSON_VALUE })
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
//	public @ResponseBody
//	ResponseData<Page<ShoppingCartEntity>> searchStatusProduct(@PathVariable String status,
//                                                                 Pageable pageable) {
//		ResponseData<Page<ShoppingCartEntity>> response = new ResponseData<>();
//
//		Page<ShoppingCartEntity> products = null;
//
//		try {
//			products = shoppingCartService.searchStatusProduct(status, pageable);
//			response.setData(products);
//			response.setCode(Constants.SUCCESS_CODE);
//			response.setMessage(Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			// TODO: handle exception
//			response.setData(null);
//			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
//			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
//		}
//
//		return response;
//	}
<<<<<<< HEAD
	//testok
	//select page db in amphitheater
	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ShoppingCartDetailEntity>> getAllShoppingCart(Pageable pageable) {
=======
	// testok
	// select page db in amphitheater
	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ShoppingCartDetailEntity>> getAllShoppingCart(Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ShoppingCartDetailEntity>> response = new ResponseData<>();

		Page<ShoppingCartDetailEntity> shoppingCarts = null;

		try {
			shoppingCarts = shoppingCartService.findPaging(pageable);
			response.setData(shoppingCarts);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
<<<<<<< HEAD
    //testok
	//create new Amphitheater
	@PostMapping(value = "/shoppingcart-management/managed-shoppingcart", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> create(@RequestBody ShoppingCartDetailEntity shoppingCarts) {
=======

	// testok
	// create new Amphitheater
	@PostMapping(value = "/shoppingcart-management/managed-shoppingcart", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> create(@RequestBody ShoppingCartDetailEntity shoppingCarts) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		try {
			if (shoppingCartService.create(shoppingCarts) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			if (response.getData() == Constants.CAUTION_CODE_FIELD_EXISTED) {
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
<<<<<<< HEAD
				response.setMessage(
						Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
=======
				response.setMessage(Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			} else {
				response.setData(0);
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			}
		}

		return response;
	}
<<<<<<< HEAD
    //test ok
	// get all list Amphitheater
	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<List<ShoppingCartDetailEntity>> getListshoppingCart() {
=======

	// test ok
	// get all list Amphitheater
	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart/all", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<List<ShoppingCartDetailEntity>> getListshoppingCart() {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<List<ShoppingCartDetailEntity>> response = new ResponseData<>();

		List<ShoppingCartDetailEntity> shoppingCarts = null;

		try {
			shoppingCarts = shoppingCartService.findAll();
			response.setData(shoppingCarts);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/shoppingcart-management/managed-shoppingcart/find-id/{id}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<ShoppingCartDetailEntity> findOne(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<ShoppingCartDetailEntity> findOne(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<ShoppingCartDetailEntity> response = new ResponseData<>();

		ShoppingCartDetailEntity shoppingCarts = null;

		try {
			shoppingCarts = shoppingCartService.findOne(id);
			response.setData(shoppingCarts);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}

	/**
	 * update
	 */
	@PutMapping(value = "/shoppingcart-management/managed-shoppingcart", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> update(@RequestBody ShoppingCartDetailEntity shoppingCarts) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> update(@RequestBody ShoppingCartDetailEntity shoppingCarts) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		try {
			if (shoppingCartService.update(shoppingCarts) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			if (response.getData() == Constants.CAUTION_CODE_FIELD_EXISTED) {
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
<<<<<<< HEAD
				response.setMessage(
						Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
=======
				response.setMessage(Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			} else {
				response.setData(0);
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			}
		}

		return response;
	}

	/**
	 * delete by id
	 */
	@DeleteMapping(value = "/shoppingcart-management/managed-shoppingcart/delete/{id}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> delete(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> delete(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		int result = shoppingCartService.delete(id);
		if (result == 1) {
			response.setData(result);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} else {
			response.setData(0);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}

	/**
	 * delete multiple
	 */
	@DeleteMapping(value = "/shoppingcart-management/managed-shoppingcart/delete-multiple/{entityids}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		List<ShoppingCartDetailEntity> shoppingCarts = new ArrayList<ShoppingCartDetailEntity>();
		for (int i = 0; i < entityIds.length; i++) {
			shoppingCarts.add(shoppingCartService.findOne(entityIds[i]));
		}

		int result = shoppingCartService.deleteAllBatch(shoppingCarts);
		if (result == 1) {
			response.setData(result);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} else {
			response.setData(0);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}

<<<<<<< HEAD


	// tim kiem
	@PostMapping(value = "/shoppingcart-management/managed-shoppingcart/advance-search", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ShoppingCartDetailEntity>> search(@RequestBody ShoppingCartEntity product,
                                               Pageable pageable) {
		ResponseData<Page<ShoppingCartDetailEntity>> response = new ResponseData<>();

		Page<ShoppingCartDetailEntity> shoppingCarts = null;
=======
	// tim kiem
	@PostMapping(value = "/shoppingcart-management/managed-shoppingcart/advance-search", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ShoppingCartDetailEntity>> search(@RequestBody ShoppingCartEntity product,
			Pageable pageable) {
		ResponseData<Page<ShoppingCartDetailEntity>> response = new ResponseData<>();

//		Page<ShoppingCartDetailEntity> shoppingCarts = null;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

//		try {
//			shoppingCarts = shoppingCartService.search(product, pageable);
//			response.setData(shoppingCarts);
//			response.setCode(Constants.SUCCESS_CODE);
//			response.setMessage(Constants.SUCCESS_MSG);
//		} catch (Exception e) {
//			// TODO: handle exception
//			response.setData(null);
//			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
//			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
//		}

		return response;
	}
}
