package com.ncs.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shipping", schema = "dmdc", catalog = "")
@Data
public class Shipping implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private int cost;
<<<<<<< HEAD
=======
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
