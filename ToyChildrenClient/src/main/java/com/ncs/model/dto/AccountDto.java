package com.ncs.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
