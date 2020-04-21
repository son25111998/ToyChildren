package com.ncs.model.entity;

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
@Table(name = "order", schema = "dmdc", catalog = "")
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_ORDER")
	private Date dateOrder;

	@Column(name = "PAYMENT_TYPE")
	private int paymentType;

	@Column
	private int status;

	@ManyToOne
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
	private Tax tax;
}
