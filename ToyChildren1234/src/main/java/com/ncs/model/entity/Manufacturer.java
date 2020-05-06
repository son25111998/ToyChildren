package com.ncs.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "manufacturer", schema = "dmdc", catalog = "")
@Data
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	@Column(name = "ID")
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private int id;

	@Column
	private String name;

	@Column
	private String image;

	@Column
	private String description;
<<<<<<< HEAD
=======
	
	@Column
	private int status;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
