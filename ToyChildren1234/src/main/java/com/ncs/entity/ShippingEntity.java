package com.ncs.entity;

<<<<<<< HEAD
import javax.persistence.*;
import java.util.Objects;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

@Entity
@Table(name = "shipping", schema = "dmdc", catalog = "")
public class ShippingEntity {
<<<<<<< HEAD
    private int id;
    private Integer shippingType;
    private Double shippingCost;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SHIPPING_TYPE")
    public Integer getShippingType() {
        return shippingType;
    }

    public void setShippingType(Integer shippingType) {
        this.shippingType = shippingType;
    }

    @Basic
    @Column(name = "SHIPPING_COST")
    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingEntity that = (ShippingEntity) o;
        return id == that.id &&
                Objects.equals(shippingType, that.shippingType) &&
                Objects.equals(shippingCost, that.shippingCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shippingType, shippingCost);
    }
=======

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private int cost;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
