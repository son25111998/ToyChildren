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
@Table(name = "account", schema = "dmdc", catalog = "")
@Data
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String username;

	@Column
	private String email;

	@Column
	private String password;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "LAST_ACCESS")
	private Date lastAccess;

	@Column
	private int active;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;
}
