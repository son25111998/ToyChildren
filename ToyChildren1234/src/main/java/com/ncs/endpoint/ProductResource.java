package com.ncs.endpoint;

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.CommonConstants;
import com.ncs.common.constants.Constants;
import com.ncs.common.util.Result;
import com.ncs.entity.AmActionHistory;
<<<<<<< HEAD
import com.ncs.entity.CategoryEntity;
import com.ncs.entity.CountResquest;
import com.ncs.entity.ProductEntity;
import com.ncs.service.ActionHistoryService;
import com.ncs.service.CountRequestService;
import com.ncs.service.ProductService;



=======
import com.ncs.entity.ProductEntity;
import com.ncs.service.ActionHistoryService;
import com.ncs.service.ProductService;

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
/**
 * @author: SonNc
 **/
@EnableAutoConfiguration
@Controller
@RequestMapping("/api/v1/categories")
public class ProductResource {
	@Autowired
	private ActionHistoryService actionHistoryService;

	@Autowired
	private ProductService productService;
<<<<<<< HEAD
	@Autowired
	private CountRequestService countRequestService;

	@GetMapping(value = "/product-management/managed-product/search/{name}/{status}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> searchAllProduct(@PathVariable String name,
															  @PathVariable String status, Pageable pageable) {
=======

	@GetMapping(value = "/product-management/managed-product/search/{name}/{status}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> searchAllProduct(@PathVariable String name,
			@PathVariable String status, Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> products = null;

		try {
			products = productService.searchAllProduct(name, status, pageable);
			response.setData(products);
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

	@GetMapping(value = "/product-management/managed-product/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> searchNameProduct(@PathVariable String name,
                                                               Pageable pageable) {
=======
	@GetMapping(value = "/product-management/managed-product/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> searchNameProduct(@PathVariable String name,
			Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> products = null;

		try {
			products = productService.searchNameProduct(name, pageable);
			response.setData(products);
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
	@GetMapping(value = "/product-management/managed-product/search-status/{status}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> searchStatusProduct(@PathVariable String status,
                                                                 Pageable pageable) {
=======

	// testok
	@GetMapping(value = "/product-management/managed-product/search-status/{status}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> searchStatusProduct(@PathVariable String status,
			Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> products = null;

		try {
			products = productService.searchStatusProduct(status, pageable);
			response.setData(products);
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
	//select page db in amphitheater
	@GetMapping(value = "/product-management/managed-product", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> getAllProduct(Pageable pageable) {
=======

	// testok
	// select page db in amphitheater
	@GetMapping(value = "/product-management/managed-product", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> getAllProduct(Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> products = null;

		try {
			products = productService.findPaging(pageable);
			response.setData(products);
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
	@PostMapping(value = "/product-management/managed-product", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> create(@RequestBody ProductEntity products) {
=======

	// testok
	// create new Amphitheater
	@PostMapping(value = "/product-management/managed-product", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> create(@RequestBody ProductEntity products) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		try {
			if (productService.create(products) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
				// create log
				AmActionHistory actionHistory = new AmActionHistory();
				actionHistory.setAction(CommonConstants.ACTION_HISTORY_ACTION.CREATED);
				actionHistory.setModule(CommonConstants.ACTION_HISTORY_MODULE.CATEGORY.PRODUCT);
				actionHistory.setRecordId(products.getId() + "");

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				actionHistory.setProduct(products.getName());
				actionHistory.setUserName(CommonConstants.DEFAULT_USER);
				actionHistory.setManipulation("Tạo mới sản phẩm");
				actionHistory.setDetailAction(products.toString());
				actionHistory.setStatus(CommonConstants.STATUS_HISTORY.CREATED);
				actionHistoryService.createActionHistoryItem(actionHistory);
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
	@RequestMapping(value = "/product-management/managed-product/upload", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> uploadImage(@RequestParam(name="file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		System.out.println("=========================================================================");
		
		//String path=context.getRealPath("/resources/templates");
		File files  = new File("F:\\ThucTapTotNghiep\\Toy-client\\src\\assets\\layouts\\layout\\img",  File.separator +fileName);
		//File files  = new File(path,  File.separator +fileName);
	    try {
=======

	@RequestMapping(value = "/product-management/managed-product/upload", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> uploadImage(@RequestParam(name = "file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		System.out.println("=========================================================================");

		// String path=context.getRealPath("/resources/templates");
		File files = new File("F:\\ThucTapTotNghiep\\Toy-client\\src\\assets\\layouts\\layout\\img",
				File.separator + fileName);
		// File files = new File(path, File.separator +fileName);
		try {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			file.transferTo(files);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
	    return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(), HttpStatus.CREATED);

	}
    //test ok
	// get all list Amphitheater
	@GetMapping(value = "/product-management/managed-product/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<List<ProductEntity>> getListProduct() {
=======
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(), HttpStatus.CREATED);

	}

	// test ok
	// get all list Amphitheater
	@GetMapping(value = "/product-management/managed-product/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<List<ProductEntity>> getListProduct() {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<List<ProductEntity>> response = new ResponseData<>();

		List<ProductEntity> product = null;

		try {
			product = productService.findAll();
			response.setData(product);
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

	@GetMapping(value = "/product-management/managed-product/find-id/{id}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<ProductEntity> findOne(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<ProductEntity> findOne(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<ProductEntity> response = new ResponseData<>();

		ProductEntity product = null;

		try {
			product = productService.findOne(id);
			response.setData(product);
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
	@PutMapping(value = "/product-management/managed-product", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> update(@RequestBody ProductEntity product) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> update(@RequestBody ProductEntity product) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		ProductEntity oldData = productService.findOne(product.getId());
		String dataSave = oldData.toString();
		try {
			if (productService.update(product) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
				// create log
				AmActionHistory actionHistory = new AmActionHistory();
				actionHistory.setAction(CommonConstants.ACTION_HISTORY_ACTION.UPDATED);
				actionHistory.setModule(CommonConstants.ACTION_HISTORY_MODULE.CATEGORY.PRODUCT);
				actionHistory.setRecordId(product.getId() + "");

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				actionHistory.setProduct(product.getName());
				actionHistory.setUserName(CommonConstants.DEFAULT_USER);
				actionHistory.setManipulation("Chỉnh sửa sản phẩm");
				actionHistory.setDetailAction("Dữ liệu cũ: " + dataSave);
				actionHistory.setDetailAction1("Dữ liệu mới: " + product.toString());
				actionHistory.setStatus(CommonConstants.STATUS_HISTORY.UPDATE);
				actionHistoryService.createActionHistoryItem(actionHistory);
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
	@DeleteMapping(value = "/product-management/managed-product/delete/{id}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> delete(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> delete(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		int result = productService.delete(id);
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
	@DeleteMapping(value = "/product-management/managed-product/delete-multiple/{entityids}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		List<ProductEntity> products = new ArrayList<ProductEntity>();
		for (int i = 0; i < entityIds.length; i++) {
			products.add(productService.findOne(entityIds[i]));
		}

		int result = productService.deleteAllBatch(products);
		if (result == 1) {
			for (ProductEntity item : products) {
				AmActionHistory actionHistory = new AmActionHistory();
				actionHistory.setAction(CommonConstants.ACTION_HISTORY_ACTION.DELETED);
				actionHistory.setModule(CommonConstants.ACTION_HISTORY_MODULE.CATEGORY.PRODUCT);
				actionHistory.setRecordId(item.getId() + "");
				actionHistory.setProduct(item.getName());
				actionHistory.setManipulation("Xóa Sản Phẩm");
				actionHistory.setDetailAction(item.toString());
				actionHistory.setUserName(CommonConstants.DEFAULT_USER);
				actionHistory.setStatus(CommonConstants.STATUS_HISTORY.DELETE);
				actionHistoryService.createActionHistoryItem(actionHistory);

			}
			response.setData(result);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
<<<<<<< HEAD
			
			
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		} else {
			response.setData(0);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}

<<<<<<< HEAD


	// tim kiem
	@PostMapping(value = "/product-management/managed-product/advance-search", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> search(@RequestBody ProductEntity product,
                                               Pageable pageable) {
=======
	// tim kiem
	@PostMapping(value = "/product-management/managed-product/advance-search", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> search(@RequestBody ProductEntity product,
			Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> products = null;

		try {
			products = productService.search(product, pageable);
//			CountResquest countRequest=new CountResquest();
//			countRequest.setIdProduct(product.getId());
//			countRequest.setRequestCount(1);
//			ProductEntity productExsits=new ProductEntity();
//			productExsits=productService.findOne(countRequest.getIdProduct());
//			if(productExsits!=null) {
//				countRequest.setRequestCount(countRequest.getRequestCount()+1);
//			}
//			countRequestService.create(countRequest);
			response.setData(products);
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
	@GetMapping(value = "/product-management/managed-product/find-idCategory/{idCategory}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
=======

	@GetMapping(value = "/product-management/managed-product/find-idCategory/{idCategory}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	public @ResponseBody ResponseData<List<ProductEntity>> findByCategory(@PathVariable("idCategory") int idCategory) {
		ResponseData<List<ProductEntity>> response = new ResponseData<>();

		try {
			response.setData(productService.getListCategory(idCategory));
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
	@GetMapping(value = "/product-management/managed-product/find-idManufacturer/{idManufacturer}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseData<List<ProductEntity>> findByManufacturer(@PathVariable("idManufacturer") int idManufacturer) {
=======

	@GetMapping(value = "/product-management/managed-product/find-idManufacturer/{idManufacturer}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<List<ProductEntity>> findByManufacturer(
			@PathVariable("idManufacturer") int idManufacturer) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<List<ProductEntity>> response = new ResponseData<>();
		try {
			response.setData(productService.getListManufacturer(idManufacturer));
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
	@GetMapping(value = "/product-management/managed-product/sort-price", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> getListProductByPrice( Pageable pageable) {
=======

	@GetMapping(value = "/product-management/managed-product/sort-price", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> getListProductByPrice(Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> product = null;

		try {
			product = productService.findByPriceDesc(pageable);
			response.setData(product);
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
	@GetMapping(value = "/product-management/managed-product/sort-price/asc", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<ProductEntity>> getListProductByPriceAsc( Pageable pageable) {
=======

	@GetMapping(value = "/product-management/managed-product/sort-price/asc", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<ProductEntity>> getListProductByPriceAsc(Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<ProductEntity>> response = new ResponseData<>();

		Page<ProductEntity> product = null;

		try {
			product = productService.findByPriceAsc(pageable);
			response.setData(product);
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
