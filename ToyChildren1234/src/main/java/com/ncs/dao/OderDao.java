package com.ncs.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Order;
import com.ncs.model.input.OderInput;

@Repository
public class OderDao {

	@Autowired
	private EntityManager entityManager;

	public ResponseData<Page<Order>> getListOrder(OderInput input, Pageable pageable) {
		StringBuffer sql = new StringBuffer();
		ResponseData<Page<Order>> response = new ResponseData<>();
		Page<Order> ordersPage;
		try {
			// create query DB
			sql.append(
					"select * from orderproduct a inner join order_detail b on b.ORDER_ID=a.id inner join product c on b.PRODUCT_ID=c.id ");
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

			// sort by createDate
			// sql.append(" ORDER BY orderproduct.DATE_ORDER desc");

			Query query = entityManager.createNativeQuery(sql.toString()).setFirstResult(1 * pageable.getPageSize())
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

			System.out.println("Data============ " + query.getResultList());
			ordersPage = (Page<Order>) query.getResultList();

			response.setData(ordersPage);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
