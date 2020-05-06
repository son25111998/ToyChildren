package com.ncs.serviceclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Account;
import com.ncs.model.entity.Customer;
<<<<<<< HEAD:ToyChildrenClient/src/main/java/com/ncs/serviceclient/CustomerService.java
import com.ncs.repositoryclient.AccountRepository;
=======
import com.ncs.repositoryclient.AccountClientRepository;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1:ToyChildren1234/src/main/java/com/ncs/serviceclient/CustomerService.java
import com.ncs.repositoryclient.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private AccountClientRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public ResponseData<Customer> getCustomerLogged() {
		LOGGER.info(">>>>>>>>>>>getCustomerLogged Start >>>>>>>>>>>>");
		ResponseData<Customer> response = new ResponseData<Customer>();
		try {
			Account account = new Account();
			Customer customer = new Customer();
			String username;

			// get user name account logged 
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			
			// get account by user name
			account = accountRepository.findByUsername(username);
			
			// get customer by account
			customer = customerRepository.findByAccount(account);
			
			response.setData(customer);
		} catch (Exception e) {
			LOGGER.error("Api account getCustomerLogged has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getCustomerLogged End >>>>>>>>>>>>");
		return response;
	}
}
