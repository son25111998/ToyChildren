package com.ncs.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "category", schema = "dmdc", catalog = "")
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private int status;
	
	@Column(name = "PARENT_CATEGORY_ID")
	private int parentCategoryId;
}
