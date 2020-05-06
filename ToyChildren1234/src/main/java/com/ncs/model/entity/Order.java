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
<<<<<<< HEAD
@Table(name = "orders", schema = "dmdc", catalog = "")
=======
@Table(name = "orderproduct", schema = "dmdc", catalog = "")
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
@Data
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

<<<<<<< HEAD
	@Column(name = "create_date")
	private Date createDate;

	@Column
	private String payment;
=======
	@Column(name = "DATE_ORDER")
	private Date createDate;

	@Column(name = "PAYMENT_TYPE")
	private int payment;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

	@Column
	private int status;

	@ManyToOne
<<<<<<< HEAD
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;

	@ManyToOne
	@JoinColumn(name = "shipping_id")
	private Shipping shipping;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "tax_id")
=======
	@JoinColumn(name = "COUPON_ID")
	private Coupon coupon;

	@ManyToOne
	@JoinColumn(name = "SHIPPING_ID")
	private Shipping shipping;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "TAX_ID")
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private Tax tax;
}
