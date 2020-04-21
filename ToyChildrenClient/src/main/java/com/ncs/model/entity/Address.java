package com.ncs.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "address", schema = "dmdc", catalog = "")
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column
	private String section;

	@Column
	private String road;

	@Column
	private String town;

	@Column
	private String district;

	@Column
	private String city;
}
