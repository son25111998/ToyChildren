package com.ncs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ncs.model.entity.Order;
import com.ncs.model.input.OderInput;
import com.ncs.model.output.OrderOutput2;
import com.ncs.model.output.Pagination;

@Repository
public class OderDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public OrderOutput2 getListOrder(OderInput input) {
		StringBuffer sql = new StringBuffer();
		OrderOutput2 result = new OrderOutput2();
		Pagination pageable = new Pagination();
		List<Order> orders = new ArrayList<>();

		try {
			// create query DB
			sql.append(
					"select DISTINCT a from Order a inner join OrderDetail b on b.order.id = a.id inner join Product c on b.product.id = c.id ");
			if (!StringUtils.isEmpty(input.getProductName())) {
				sql.append(" WHERE c.name LIKE :productName ");
			}
			if (input.getCategoryId() != null) {
				if (!StringUtils.isEmpty(input.getProductName())) {
					sql.append(" AND ");
				}
				sql.append(" WHERE ");
				sql.append(" c.category.id = :categoryId ");
			}
			if (input.getStatus() != null) {
				if (!StringUtils.isEmpty(input.getProductName()) || input.getCategoryId() != null) {
					sql.append(" AND ");
				}
				sql.append(" WHERE ");
				sql.append(" c.status = :status ");
			}
			if (!StringUtils.isEmpty(input.getManufacturerId())) {
				if (!StringUtils.isEmpty(input.getProductName()) || input.getCategoryId() != null
						|| input.getStatus() != null) {
					sql.append(" AND ");
				}
				sql.append(" WHERE ");
				sql.append(" c.manufacturer.id = :manufacturerId ");
			}

			Query query = entityManager.createQuery(sql.toString()).setFirstResult((input.getPage()) * input.getSize())
					.setMaxResults(input.getSize());

			Query query2 = entityManager.createQuery(sql.toString());

			// set data to parameters of query
			if (!StringUtils.isEmpty(input.getProductName())) {
				query.setParameter("productName", "%" + input.getProductName() + "%");
				query2.setParameter("productName", "%" + input.getProductName() + "%");
			}
			if (input.getCategoryId() != null) {
				query.setParameter("categoryId", input.getCategoryId());
				query2.setParameter("categoryId", input.getCategoryId());
			}
			if (input.getStatus() != null) {
				query.setParameter("status", input.getStatus());
				query2.setParameter("status", input.getStatus());
			}
			if (input.getManufacturerId() != null) {
				query.setParameter("manufacturerId", input.getManufacturerId());
				query2.setParameter("manufacturerId", input.getManufacturerId());
			}

			orders = query.getResultList();

			pageable.setPageNumber(input.getPage());
			pageable.setPageSize(input.getSize());
			result.setTotalElements(Long.valueOf(query2.getResultList().size()));

			result.setOrdersPage(orders);
			result.setPageable(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
