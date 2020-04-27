package com.ncs.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Order;
import com.ncs.model.output.GetListOrderOutput;
import com.ncs.model.output.OrderOutput;
import com.ncs.model.output.Pagination;

@Repository
public class OrderDao {
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GetListOrderOutput getListOrder(int page, int size, String date) {
		StringBuffer sql = new StringBuffer();
		GetListOrderOutput result = new GetListOrderOutput();
		Pagination pagging = new Pagination();

		try {
			// create query DB
			sql.append("SELECT new ");
			sql.append(OrderOutput.class.getName());
			sql.append(
					" (p.id, p.status, p.coupon.sale, p.shipping.cost, p.tax.percentage, p.payment, p.shipping.name, p.customer.lastName, p.customer.phone, p.createDate) ");
			sql.append(" FROM ");
			sql.append(Order.class.getName());
			sql.append(" p ");

			if (!StringUtils.isEmpty(date)) {
				sql.append(" AND ");
				sql.append(" p.create_date = STR_TO_DATE(:date,'%d/%m/%Y') ");
			}

			// sort by createDate
			sql.append(" ORDER BY p.id DESC NULLS LAST");

			// set default page = 1 & size = 0
			if (page < 1) {
				page = Constants.PAGE_DEFAULT;
			}
			if (size < 1) {
				size = Constants.SIZE_DEFAULT;
			}

			Query query = entityManager.createQuery(sql.toString()).setFirstResult((page - 1) * size)
					.setMaxResults(size);

			// set data to parameters of query
			if (!StringUtils.isEmpty(date)) {
				query.setParameter("date", date);
			}

			// set data output
			pagging.setPage(page);
			pagging.setSize(size);
			pagging.setTotalRecord(getTotalRecord(date));

			result.setPagination(pagging);

			// execute query and return data
			result.setOrders(query.getResultList());
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	private long getTotalRecord(String date) {
		StringBuffer sql = new StringBuffer();
		try {
			// create query DB
			sql.append("SELECT count(*) FROM ");
			sql.append(Order.class.getName());
			sql.append(" p ");

			if (!StringUtils.isEmpty(date)) {
				sql.append(" AND ");
				sql.append(" p.create_date = STR_TO_DATE(:date,'%d/%m/%Y') ");
			}

			Query query = entityManager.createQuery(sql.toString());

			// set data to parameters of query
			if (!StringUtils.isEmpty(date)) {
				query.setParameter("date", date);
			}

			return (long) query.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}
}
