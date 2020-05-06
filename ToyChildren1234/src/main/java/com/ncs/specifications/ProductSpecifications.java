package com.ncs.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ncs.common.CommonUtil;
import com.ncs.entity.ProductEntity;

<<<<<<< HEAD

=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
public class ProductSpecifications {
<<<<<<< HEAD
	public static Specification<ProductEntity> advanceFilter(ProductEntity product){
		return new Specification<ProductEntity>() {
			
=======
	public static Specification<ProductEntity> advanceFilter(ProductEntity product) {
		return new Specification<ProductEntity>() {
			private static final long serialVersionUID = 1L;

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			@Override
			public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
<<<<<<< HEAD
				
				if(product.getName() != null && !product.getName().equals("")) {
					obj = cb.like(cb.lower(root.get("name")), "%" + CommonUtil.standardized(product.getName().toLowerCase()) + "%");
					predicateList.add(obj);	
				}
				if (product.getStatus() != null && !product.getStatus().equals("")) {
					obj = cb.like(cb.lower(root.get("status")), "%" + product.getStatus()+ "%");
					predicateList.add(obj);
				}
				if(product.getDescription() != null && !product.getDescription().equals("")) {
					obj = cb.like(cb.lower(root.get("description")), "%" + CommonUtil.standardized(product.getDescription().toLowerCase()) + "%");
					predicateList.add(obj);	
				}
				if (product.getCategoryId() != 0 ) {
					obj = cb.equal(root.get("categoryId"), product.getCategoryId());
					predicateList.add(obj);
				}
				if (product.getManufacturerId() != 0 ) {
					obj = cb.equal(root.get("manufacturerId"), product.getManufacturerId());
					predicateList.add(obj);
				}
				
=======

				if (product.getName() != null && !product.getName().equals("")) {
					obj = cb.like(cb.lower(root.get("name")),
							"%" + CommonUtil.standardized(product.getName().toLowerCase()) + "%");
					predicateList.add(obj);
				}
				if (product.getStatus() != null && !product.getStatus().equals("")) {
					obj = cb.like(cb.lower(root.get("status")), "%" + product.getStatus() + "%");
					predicateList.add(obj);
				}
				if (product.getDescription() != null && !product.getDescription().equals("")) {
					obj = cb.like(cb.lower(root.get("description")),
							"%" + CommonUtil.standardized(product.getDescription().toLowerCase()) + "%");
					predicateList.add(obj);
				}
				if (product.getCategoryId() != 0) {
					obj = cb.equal(root.get("categoryId"), product.getCategoryId());
					predicateList.add(obj);
				}
				if (product.getManufacturerId() != 0) {
					obj = cb.equal(root.get("manufacturerId"), product.getManufacturerId());
					predicateList.add(obj);
				}

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				query.orderBy(cb.desc(root.get("id")));
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
