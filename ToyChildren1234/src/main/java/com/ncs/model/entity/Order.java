package com.ncs.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ncs.model.output.OrderDetailOutput;

import lombok.Data;

@Entity
@Table(name = "orderproduct", schema = "dmdc", catalog = "")
@Data
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "DATE_ORDER")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "QRCODE")
    private byte[] qrcode = new byte[0];
	
	@Column(name = "PAYMENT_TYPE")
	private int payment;

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
	
	@Transient
	private int sumMoney;

	@Transient
	private List<OrderDetailOutput> orderDetails;
}
