package com.ncs.model.input;

import lombok.Data;

@Data
public class AccountInput {
	private int sex;
	private String firstName;
	private String middleName;
	private String lastName;
	private String birthday;
	private String username;
	private String password;
	private String email;
	private String phone;
}
