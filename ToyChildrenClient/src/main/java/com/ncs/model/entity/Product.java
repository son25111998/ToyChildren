package com.ncs.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String thumbai;

	@Column
	private double price;

	@Column
	private int amount;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column
	private Double lenght;

	@Column
	private Double width;

	@Column
	private Double height;

	@Column
	private Integer status;
	
	@Column(name = "manufacturer_id")
	private int manufacturerId;

	@Column(name = "CATEGORY_ID")
	private int categoryId;
}