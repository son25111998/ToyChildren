package com.ncs.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Description;

import lombok.Data;

@Entity
@Table(name = "tax", schema = "dmdc", catalog = "")
@Data
@Description("Thuế")
public class Tax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	@Column(name = "ID")
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
	private int id;

	@Column
	private String type;

	@Column
	private Float percentage;
}
