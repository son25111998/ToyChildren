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
@Table(name = "role", schema = "dmdc", catalog = "")
@Data
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

<<<<<<< HEAD
	@Column(name = "CODE")
=======
	@Column(name = "ROLE")
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private String Code;
	
	@Column
	private String description;
}
