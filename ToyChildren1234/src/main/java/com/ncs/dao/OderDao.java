package com.ncs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ncs.model.input.OderInput;

@Repository
public class OderDao {

	@Autowired
	private EntityManager entityManager;

	public List<Integer> getListOrder(OderInput input, Pageable pageable) {
		StringBuffer sql = new StringBuffer();
		List<Integer> output2 = new ArrayList<>(); 
		try {
			// create query DB
			sql.append(
					"select a.id from orderproduct a inner join order_detail b on b.ORDER_ID=a.id inner join product c on b.PRODUCT_ID=c.id ");
			if (!StringUtils.isEmpty(input.getProductName())) {
				sql.append(" WHERE c.name LIKE :productName ");
			}
			if (input.getCategoryId() != null) {
				if (!StringUtils.isEmpty(input.getProductName())) {
					sql.append(" AND ");
				}
				sql.append(" WHERE ");
				sql.append(" c.CATEGORY_ID = :categoryId ");
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
				sql.append(" c.MANUFACTURER_ID = :manufacturerId ");
			}
			if (!StringUtils.isEmpty(input.getDate())) {
				if (!StringUtils.isEmpty(input.getProductName()) || input.getCategoryId() != null
						|| input.getStatus() != null || !StringUtils.isEmpty(input.getManufacturerId())) {
					sql.append(" AND ");
				}
				sql.append(" WHERE ");
				sql.append(" a.DATE_ORDER = :date ");
			}

			Query query = entityManager.createNativeQuery(sql.toString()).setFirstResult((input.getPage() - 1) * input.getSize())
					.setMaxResults((int) pageable.getOffset());

			// set data to parameters of query
			if (!StringUtils.isEmpty(input.getProductName())) {
				query.setParameter("productName", "%" + input.getProductName() + "%");
			}
			if (input.getCategoryId() != null) {
				query.setParameter("categoryId", input.getCategoryId());
			}
			if (input.getStatus() != null) {
				query.setParameter("status", input.getStatus());
			}
			if (input.getManufacturerId() != null) {
				query.setParameter("manufacturerId", input.getManufacturerId());
			}
			if (!StringUtils.isEmpty(input.getDate())) {
				query.setParameter("date", input.getDate());
			}
			output2 = query. getResultList();
			System.out.println("Data============ " + output2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output2;
	}
}
