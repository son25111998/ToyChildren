package com.ncs.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "coupon", schema = "dmdc", catalog = "")
@Data
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	@Column(name = "ID")
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private int id;

	@Column
	private String code;

	@Column
	private int sale;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

}
