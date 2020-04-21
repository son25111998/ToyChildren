package com.ncs.model.entity;

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
public class Tax {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column
	private String type;

	@Column
	private Double percentage;
}
