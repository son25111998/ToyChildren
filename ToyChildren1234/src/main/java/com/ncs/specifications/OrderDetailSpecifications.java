package com.ncs.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ncs.entity.OrderDetailEntity;

public class OrderDetailSpecifications {
<<<<<<< HEAD
	public static Specification<OrderDetailEntity> advanceFilter(OrderDetailEntity orderDetailEntity){
		return new Specification<OrderDetailEntity>() {
			
			@Override
			public Predicate toPredicate(Root<OrderDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				
=======
	public static Specification<OrderDetailEntity> advanceFilter(OrderDetailEntity orderDetailEntity) {
		return new Specification<OrderDetailEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<OrderDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
//				Predicate obj = null;

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
//				if(orderDetailEntity.get() != null && !orderDetailEntity.getUsername().equals("")) {
//					obj = cb.like(cb.lower(root.get("username")), "%" + CommonUtil.standardized(orderDetailEntity.getUsername().toLowerCase()) + "%");
//					predicateList.add(obj);	
//				}
//				if (product.getStatuss() != null && !product.getStatuss().equals("")) {
//					obj = cb.like(cb.lower(root.get("statuss")), "%" + product.getStatuss().toLowerCase() + "%");
//					predicateList.add(obj);
//				}
<<<<<<< HEAD
				
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				query.orderBy(cb.desc(root.get("id")));
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
