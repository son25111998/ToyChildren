package com.ncs.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer", schema = "dmdc", catalog = "")
@Data
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column
	private int gendr;

	@Column(name = "BIRTH_DAY")
	private Date birthDay;

	@Column
	private String phone;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
}
