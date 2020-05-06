package com.ncs.model.entity;

import java.io.Serializable;

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
@Table(name = "order_detail", schema = "dmdc", catalog = "")
@Data
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int quantity;

<<<<<<< HEAD
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
=======
	@Column
	private String attribute;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private Product product;
}
