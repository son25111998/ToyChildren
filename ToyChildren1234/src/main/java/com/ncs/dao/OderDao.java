package com.ncs.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.input.OderInput;
import com.ncs.model.output.OrderOutput2;

@Repository
public class OderDao {

	@Autowired
	private EntityManager entityManager;

	public ResponseData<OrderOutput2> getListOrder(OderInput input, Pageable pageable) {
		StringBuffer sql = new StringBuffer();
		ResponseData<OrderOutput2> response = new ResponseData<>();
		OrderOutput2 output2 = new OrderOutput2();
		try {
			// create query DB
			sql.append(
					"select a.* from orderproduct a inner join order_detail b on b.ORDER_ID=a.id inner join product c on b.PRODUCT_ID=c.id ");
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
			
			output2.setOrders(query.getResultList());
			output2.setPageable(pageable);
			System.out.println("Data============ " + output2.getOrders());
			response.setData(output2);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
