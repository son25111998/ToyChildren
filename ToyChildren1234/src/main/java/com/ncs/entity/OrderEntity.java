package com.ncs.entity;

import java.sql.Date;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import javax.persistence.Table;

@Entity
@Table(name = "orderproduct", schema = "dmdc", catalog = "")
public class OrderEntity {
<<<<<<< HEAD
    private int id;
    private Date dateOrder;
    private Integer paymentType;
    private Integer status;
    private Integer couponId;
    private Integer shippingId;
    private Integer customerId;
    private Integer taxId;
    private List<OrderDetailEntity> orderDetailEntity;

  

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DATE_ORDER")
    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Column(name = "PAYMENT_TYPE")
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name = "COUPON_ID")
    public Integer getCouponId() {
  		return couponId;
  	}

  	public void setCouponId(Integer couponId) {
  		this.couponId = couponId;
  	}
    @Column(name = "SHIPPING_ID")
  	public Integer getShippingId() {
  		return shippingId;
  	}

  	public void setShippingId(Integer shippingId) {
  		this.shippingId = shippingId;
  	}
    @Column(name = "CUSTOMER_ID")
  	public Integer getCustomerId() {
  		return customerId;
  	}

  	public void setCustomerId(Integer customerId) {
  		this.customerId = customerId;
  	}
    @Column(name = "TAX_ID")
  	public Integer getTaxId() {
  		return taxId;
  	}

  	public void setTaxId(Integer taxId) {
  		this.taxId = taxId;
  	}
=======
	private int id;
	private Date dateOrder;
	private Integer paymentType;
	private Integer status;
	private Integer couponId;
	private Integer shippingId;
	private Integer customerId;
	private Integer taxId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "DATE_ORDER")
	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	@Column(name = "PAYMENT_TYPE")
	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "COUPON_ID")
	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	@Column(name = "SHIPPING_ID")
	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "TAX_ID")
	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

//	@OneToMany(mappedBy="orderEntity")
//    public List<OrderDetailEntity> getOrderDetailEntity() {
//		return orderDetailEntity;
//	}
<<<<<<< HEAD

	public void setOrderDetailEntity(List<OrderDetailEntity> orderDetailEntity) {
		this.orderDetailEntity = orderDetailEntity;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                Objects.equals(dateOrder, that.dateOrder) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(status, that.status);
    }



	@Override
    public int hashCode() {
        return Objects.hash(id, dateOrder, paymentType, status);
    }
=======
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderEntity that = (OrderEntity) o;
		return id == that.id && Objects.equals(dateOrder, that.dateOrder)
				&& Objects.equals(paymentType, that.paymentType) && Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dateOrder, paymentType, status);
	}
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
