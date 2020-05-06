package com.ncs.entity;

<<<<<<< HEAD
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "product_attribute", schema = "dmdc", catalog = "")
public class ProductAttributeEntity {
    private int id;
    //private int productId;
    private int attributeValueId;
    private Integer amount;
    private Double additionalPrice;
	private ProductEntity productEntity;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
=======
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_attribute", schema = "dmdc", catalog = "")
public class ProductAttributeEntity {
	private int id;
	// private int productId;
	private int attributeValueId;
	private Integer amount;
	private Double additionalPrice;
	private ProductEntity productEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

//    @Basic
//    @Column(name = "PRODUCT_ID")
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

<<<<<<< HEAD
    @Basic
    @Column(name = "ATTRIBUTE_VALUE_ID")
    public int getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(int attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    @Basic
    @Column(name = "AMOUNT")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "ADDITIONAL_PRICE")
    public Double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(Double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    @JsonIgnore
    public ProductEntity getProductEntity() {
=======
	@Basic
	@Column(name = "ATTRIBUTE_VALUE_ID")
	public int getAttributeValueId() {
		return attributeValueId;
	}

	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	@Basic
	@Column(name = "AMOUNT")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Basic
	@Column(name = "ADDITIONAL_PRICE")
	public Double getAdditionalPrice() {
		return additionalPrice;
	}

	public void setAdditionalPrice(Double additionalPrice) {
		this.additionalPrice = additionalPrice;
	}

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	@JsonIgnore
	public ProductEntity getProductEntity() {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
