package com.ncs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ncs.model.entity.Account;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Account account;

	public CustomUserDetails(Account account) {
		this.account = account; 
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().getCode());

		grantList.add(authority);

		return grantList;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Account getAccount() {
		return account;
	}
}
