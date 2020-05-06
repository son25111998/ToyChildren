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
import com.ncs.entity.OrderDetailEntity;
import com.ncs.entity.OrderEntity;
<<<<<<< HEAD
import com.ncs.entity.ProductEntity;
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import com.ncs.service.OrderService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/api/v1/categories")
public class OrderResource {
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/order-management/managed-order/search/{name}/{status}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<OrderEntity>> searchAllOrder(@PathVariable String name,
															  @PathVariable String status, Pageable pageable) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<OrderEntity>> searchAllOrder(@PathVariable String name,
			@PathVariable String status, Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<OrderEntity>> response = new ResponseData<>();

		Page<OrderEntity> orders = null;

		try {
			orders = orderService.searchAllOrder(name, status, pageable);
			response.setData(orders);
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

	@GetMapping(value = "/order-management/managed-order/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<OrderEntity>> searchNameOrder(@PathVariable String name,
                                                               Pageable pageable) {
=======
	@GetMapping(value = "/order-management/managed-order/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<OrderEntity>> searchNameOrder(@PathVariable String name, Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<OrderEntity>> response = new ResponseData<>();

		Page<OrderEntity> orders = null;

		try {
			orders = orderService.searchNameOrder(name, pageable);
			response.setData(orders);
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
//	@GetMapping(value = "/order-management/managed-order/search-status/{status}", produces = {
//			MediaType.APPLICATION_JSON_UTF8_VALUE })
=======

	// testok
//	@GetMapping(value = "/order-management/managed-order/search-status/{status}", produces = {
//			MediaType.APPLICATION_JSON_VALUE })
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
//	public @ResponseBody
//	ResponseData<Page<OrderEntity>> searchStatusProduct(@PathVariable String status,
//                                                                 Pageable pageable) {
//		ResponseData<Page<OrderEntity>> response = new ResponseData<>();
//
//		Page<OrderEntity> products = null;
//
//		try {
//			products = orderService.searchStatusProduct(status, pageable);
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
	@GetMapping(value = "/order-management/managed-order", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<OrderEntity>> getAllOrder(Pageable pageable) {
=======
	// testok
	// select page db in amphitheater
	@GetMapping(value = "/order-management/managed-order", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<OrderEntity>> getAllOrder(Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<OrderEntity>> response = new ResponseData<>();

		Page<OrderEntity> orders = null;

		try {
			orders = orderService.findPaging(pageable);
			response.setData(orders);
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
	@PostMapping(value = "/order-management/managed-order", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> create(@RequestBody OrderEntity orders) {
=======

	// testok
	// create new Amphitheater
	@PostMapping(value = "/order-management/managed-order", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> create(@RequestBody OrderEntity orders) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		try {
			if (orderService.create(orders) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	@GetMapping(value = "/order-management/managed-order/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<List<OrderEntity>> getListOrder() {
=======

	// test ok
	// get all list Amphitheater
	@GetMapping(value = "/order-management/managed-order/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<List<OrderEntity>> getListOrder() {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<List<OrderEntity>> response = new ResponseData<>();

		List<OrderEntity> orders = null;

		try {
			orders = orderService.findAll();
			response.setData(orders);
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

	@GetMapping(value = "/order-management/managed-order/find-id/{id}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<OrderEntity> findOne(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<OrderEntity> findOne(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<OrderEntity> response = new ResponseData<>();

		OrderEntity orders = null;

		try {
			orders = orderService.findOne(id);
			response.setData(orders);
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
	@PutMapping(value = "/order-management/managed-order", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> update(@RequestBody OrderEntity orders) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> update(@RequestBody OrderEntity orders) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		try {
			if (orderService.update(orders) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	@DeleteMapping(value = "/order-management/managed-order/delete/{id}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> delete(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> delete(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		int result = orderService.delete(id);
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
	@DeleteMapping(value = "/order-management/managed-order/delete-multiple/{entityids}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		List<OrderEntity> orders = new ArrayList<OrderEntity>();
		for (int i = 0; i < entityIds.length; i++) {
			orders.add(orderService.findOne(entityIds[i]));
		}

		int result = orderService.deleteAllBatch(orders);
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
	@PostMapping(value = "/order-management/managed-order/advance-search", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<OrderEntity>> search(@RequestBody OrderEntity product,
                                               Pageable pageable) {
=======
	// tim kiem
	@PostMapping(value = "/order-management/managed-order/advance-search", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<OrderEntity>> search(@RequestBody OrderEntity product, Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<OrderEntity>> response = new ResponseData<>();

		Page<OrderEntity> orders = null;

		try {
			orders = orderService.search(product, pageable);
			response.setData(orders);
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
	@GetMapping(value = "/order-management/managed-order/find-idorder/{orderId}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
=======

	@GetMapping(value = "/order-management/managed-order/find-idorder/{orderId}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	public @ResponseBody ResponseData<List<OrderDetailEntity>> findByOrderEntity(@PathVariable("orderId") int orderId) {
		ResponseData<List<OrderDetailEntity>> response = new ResponseData<>();
		try {
			response.setData(orderService.getListOrderDetail(orderId));
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
}
