package com.ncs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.model.dto.CustomUserDetails;
import com.ncs.model.entity.Account;
import com.ncs.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) {
		LOGGER.info(" >>>>>>>>>>>>>> loadUserByUsername Start >>>>>>>>>>>>>>>>");
		// get user by email
		Account account = accountRepository.findByUsername(username);

		// check user null or empty
		if (ObjectUtils.isEmpty(account)) {
			LOGGER.info("User {} was not found in the database", username);
			return null;
		}
		LOGGER.info(" >>>>>>>>>>>>>> loadUserByUsername End >>>>>>>>>>>>>>>>");
		return new CustomUserDetails(account);
	}

}
