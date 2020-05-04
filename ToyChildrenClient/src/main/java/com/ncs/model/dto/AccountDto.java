package com.ncs.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AccountDto {
	private int id;

	private String username;

	private String email;

	private String password;

	private Date createTime;

	private Date lastAccess;

	private Byte active;

	private int role;
}
