//package com.ncs.serviceclient;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//
//import com.ncs.model.dto.CustomUserDetails;
//import com.ncs.model.entity.Account;
//import com.ncs.repositoryclient.AccountClientRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//	@Autowired
//	private AccountClientRepository accountRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// get user by email
//		Account account = accountRepository.findByUsername(username);
//
//		// check user null or empty
//		if (ObjectUtils.isEmpty(account)) {
//			throw new UsernameNotFoundException("User " + username + " was not found in the database");
//		}
//
//		return new CustomUserDetails(account);
//	}
//}
