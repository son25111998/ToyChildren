package com.ncs.entity;

<<<<<<< HEAD
import java.util.Objects;

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
<<<<<<< HEAD
@Entity
@Table(name = "count_request", schema = "dmdc", catalog = "")
public class CountResquest {
		private int id;
	    private Integer requestCount;
	    private Integer idProduct;

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
	    @Column(name = "requestCount")
		public Integer getRequestCount() {
			return requestCount;
		}

		public void setRequestCount(Integer requestCount) {
			this.requestCount = requestCount;
		}
	    @Basic
	    @Column(name = "idProduct")
		public Integer getIdProduct() {
			return idProduct;
		}

		public void setIdProduct(Integer idProduct) {
			this.idProduct = idProduct;
		}
=======

@Entity
@Table(name = "count_request", schema = "dmdc", catalog = "")
public class CountResquest {
	private int id;
	private Integer requestCount;
	private Integer idProduct;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "requestCount")
	public Integer getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(Integer requestCount) {
		this.requestCount = requestCount;
	}

	@Basic
	@Column(name = "idProduct")
	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

}
