package com.ncs.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Account;
import com.ncs.model.entity.Customer;
import com.ncs.model.entity.Role;
import com.ncs.model.input.AccountInput;
import com.ncs.repository.AccountRepository;
import com.ncs.repository.CustomerRepository;
import com.ncs.repository.RoleRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RoleRepository roleRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Transactional(rollbackOn = Exception.class)
	public ResponseData<Customer> register(@RequestBody AccountInput input) {
		LOGGER.info(">>>>>>>>>>>register Start >>>>>>>>>>>>");
		ResponseData<Customer> response = new ResponseData<Customer>();
		try {
			Account account = new Account();
			Customer customer = new Customer();
			Date date = new Date();

			String username = input.getUsername();
			Role role = roleRepository.findById(1).get();
			
			if(!ObjectUtils.isEmpty(accountRepository.findByUsername(username))){
				LOGGER.error("Tên đăng nhập đã tồn tại : {}", username);
				response.setData(null);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage("Tên đăng nhập đã tồn tại");
				return response;
			}
			
			// set date account
			account.setEmail(input.getEmail());
			account.setPassword(BCrypt.hashpw(input.getPassword(), BCrypt.gensalt(12)));
			account.setUsername(username);
			account.setActive((byte) 1);
			account.setCreateTime(date);
			account.setLastAccess(date);
			account.setRole(role);

			account = accountRepository.save(account);

			// set data customer
			customer.setAccount(account);
			customer.setBirthDay(date); // TODO convert string to date
			customer.setFirstName(input.getFirstName());
			customer.setMiddleName(input.getMiddleName());
			customer.setLastName(input.getLastName());
			customer.setGendr(input.getSex());
			customer.setPhone(input.getPhone());

			response.setData(customerRepository.save(customer));
		} catch (Exception e) {
			LOGGER.error("Api account register has exception : {}",e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>register End >>>>>>>>>>>>");
		return response;
	}
}
