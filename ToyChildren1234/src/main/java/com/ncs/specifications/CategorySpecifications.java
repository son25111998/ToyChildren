package com.ncs.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ncs.common.CommonUtil;
import com.ncs.entity.CategoryEntity;

<<<<<<< HEAD

public class CategorySpecifications {
	public static Specification<CategoryEntity> advanceFilter(CategoryEntity category){
		return new Specification<CategoryEntity>() {
			
=======
public class CategorySpecifications {
	public static Specification<CategoryEntity> advanceFilter(CategoryEntity category) {
		return new Specification<CategoryEntity>() {
			private static final long serialVersionUID = 1L;

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			@Override
			public Predicate toPredicate(Root<CategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
<<<<<<< HEAD
				
				if(category.getName() != null && !category.getName().equals("")) {
					obj = cb.like(cb.lower(root.get("name")), "%" + CommonUtil.standardized(category.getName().toLowerCase()) + "%");
					predicateList.add(obj);	
=======

				if (category.getName() != null && !category.getName().equals("")) {
					obj = cb.like(cb.lower(root.get("name")),
							"%" + CommonUtil.standardized(category.getName().toLowerCase()) + "%");
					predicateList.add(obj);
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
				}
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
