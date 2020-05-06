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
import com.ncs.common.constants.CommonConstants;
import com.ncs.common.constants.Constants;
import com.ncs.common.util.Result;
import com.ncs.entity.AccountEntity;
import com.ncs.entity.AmActionHistory;
<<<<<<< HEAD
import com.ncs.entity.ProductEntity;
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import com.ncs.service.AccountService;
import com.ncs.service.ActionHistoryService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/api/v1/categories")
public class AccountResource {
	@Autowired
	private ActionHistoryService actionHistoryService;
	@Autowired
	private AccountService accountService;

	@GetMapping(value = "/account-management/managed-account/search/{name}/{status}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<AccountEntity>> searchAllAccount(@PathVariable String name,
															  @PathVariable String status, Pageable pageable) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<AccountEntity>> searchAllAccount(@PathVariable String name,
			@PathVariable String status, Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<AccountEntity>> response = new ResponseData<>();

		Page<AccountEntity> account = null;

		try {
			account = accountService.searchAllAccount(name, status, pageable);
			response.setData(account);
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

	@GetMapping(value = "/account-management/managed-account/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<AccountEntity>> searchNameAccount(@PathVariable String name,
                                                               Pageable pageable) {
=======
	@GetMapping(value = "/account-management/managed-account/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<AccountEntity>> searchNameAccount(@PathVariable String name,
			Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<AccountEntity>> response = new ResponseData<>();

		Page<AccountEntity> accounts = null;

		try {
			accounts = accountService.searchNameAccount(name, pageable);
			response.setData(accounts);
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
//	@GetMapping(value = "/account-management/managed-account/search-status/{status}", produces = {
//			MediaType.APPLICATION_JSON_UTF8_VALUE })
=======

	// testok
//	@GetMapping(value = "/account-management/managed-account/search-status/{status}", produces = {
//			MediaType.APPLICATION_JSON_VALUE })
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
//	public @ResponseBody
//	ResponseData<Page<AccountEntity>> searchStatusProduct(@PathVariable String status,
//                                                                 Pageable pageable) {
//		ResponseData<Page<AccountEntity>> response = new ResponseData<>();
//
//		Page<AccountEntity> products = null;
//
//		try {
//			products = accountService.searchStatusProduct(status, pageable);
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
	@GetMapping(value = "/account-management/managed-account", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<AccountEntity>> getAllAccount(Pageable pageable) {
=======
	// testok
	// select page db in amphitheater
	@GetMapping(value = "/account-management/managed-account", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<AccountEntity>> getAllAccount(Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<AccountEntity>> response = new ResponseData<>();

		Page<AccountEntity> accounts = null;

		try {
			accounts = accountService.findPaging(pageable);
			response.setData(accounts);
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
	@PostMapping(value = "/account-management/managed-account", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> create(@RequestBody AccountEntity accounts) {
=======

	// testok
	// create new Amphitheater
	@PostMapping(value = "/account-management/managed-account", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> create(@RequestBody AccountEntity accounts) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		try {
			if (accountService.create(accounts) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
				// create log
				AmActionHistory actionHistory = new AmActionHistory();
				actionHistory.setAction(CommonConstants.ACTION_HISTORY_ACTION.CREATED);
				actionHistory.setModule(CommonConstants.ACTION_HISTORY_MODULE.CATEGORY.ACCOUNT);
				actionHistory.setRecordId(accounts.getId() + "");

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				actionHistory.setAccount(accounts.getUsername());
				actionHistory.setUserName(CommonConstants.DEFAULT_USER);
				actionHistory.setManipulation("Tạo mới tài khoản");
				actionHistory.setDetailAction(accounts.toString());
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
    //test ok
	// get all list Amphitheater
	@GetMapping(value = "/account-management/managed-account/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<List<AccountEntity>> getListAccount() {
=======

	// test ok
	// get all list Amphitheater
	@GetMapping(value = "/account-management/managed-account/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<List<AccountEntity>> getListAccount() {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<List<AccountEntity>> response = new ResponseData<>();

		List<AccountEntity> accounts = null;

		try {
			accounts = accountService.findAll();
			response.setData(accounts);
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

	@GetMapping(value = "/account-management/managed-account/find-id/{id}", produces = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<AccountEntity> findOne(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<AccountEntity> findOne(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<AccountEntity> response = new ResponseData<>();

		AccountEntity accounts = null;

		try {
			accounts = accountService.findOne(id);
			response.setData(accounts);
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
	@PutMapping(value = "/account-management/managed-account", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> update(@RequestBody AccountEntity accounts) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> update(@RequestBody AccountEntity accounts) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();
		AccountEntity oldData = accountService.findOne(accounts.getId());
		String dataSave = oldData.toString();
		try {
			if (accountService.update(accounts) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
				// create log
				AmActionHistory actionHistory = new AmActionHistory();
				actionHistory.setAction(CommonConstants.ACTION_HISTORY_ACTION.UPDATED);
				actionHistory.setModule(CommonConstants.ACTION_HISTORY_MODULE.CATEGORY.ACCOUNT);
				actionHistory.setRecordId(accounts.getId() + "");

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				actionHistory.setAccount(accounts.getUsername());
				actionHistory.setUserName(CommonConstants.DEFAULT_USER);
				actionHistory.setManipulation("Chỉnh sửa tài khoản");
				actionHistory.setDetailAction("Dữ liệu cũ: " + dataSave);
				actionHistory.setDetailAction1("Dữ liệu mới: " + accounts.toString());
				actionHistory.setDetailAction(accounts.toString());
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
	@DeleteMapping(value = "/account-management/managed-account/delete/{id}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> delete(@PathVariable("id") int id) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> delete(@PathVariable("id") int id) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		int result = accountService.delete(id);
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
	@DeleteMapping(value = "/account-management/managed-account/delete-multiple/{entityids}", consumes = {
<<<<<<< HEAD
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
=======
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Integer> response = new ResponseData<>();

		List<AccountEntity> accounts = new ArrayList<AccountEntity>();
		for (int i = 0; i < entityIds.length; i++) {
			accounts.add(accountService.findOne(entityIds[i]));
		}

		int result = accountService.deleteAllBatch(accounts);
		if (result == 1) {
			for (AccountEntity item : accounts) {
				AmActionHistory actionHistory = new AmActionHistory();
				actionHistory.setAction(CommonConstants.ACTION_HISTORY_ACTION.DELETED);
				actionHistory.setModule(CommonConstants.ACTION_HISTORY_MODULE.CATEGORY.ACCOUNT);
				actionHistory.setRecordId(item.getId() + "");
				actionHistory.setAccount(item.getUsername());
				actionHistory.setManipulation("Xóa Sản Phẩm");
				actionHistory.setDetailAction(item.toString());
				actionHistory.setUserName(CommonConstants.DEFAULT_USER);
				actionHistory.setStatus(CommonConstants.STATUS_HISTORY.DELETE);
				actionHistoryService.createActionHistoryItem(actionHistory);

			}
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
	@PostMapping(value = "/account-management/managed-account/advance-search", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ResponseData<Page<AccountEntity>> search(@RequestBody AccountEntity accountEntity,
                                               Pageable pageable) {
=======
	// tim kiem
	@PostMapping(value = "/account-management/managed-account/advance-search", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseData<Page<AccountEntity>> search(@RequestBody AccountEntity accountEntity,
			Pageable pageable) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		ResponseData<Page<AccountEntity>> response = new ResponseData<>();

		Page<AccountEntity> accounts = null;

		try {
			accounts = accountService.search(accountEntity, pageable);
			response.setData(accounts);
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
