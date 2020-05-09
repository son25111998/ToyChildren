package com.ncs.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.ncs.common.constants.Constants;
import com.ncs.model.entity.Category;
import com.ncs.model.entity.Product;
import com.ncs.model.output.GetListProductOutput;
import com.ncs.model.output.Pagination;

@Repository
public class ProductDao {
	
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional
	public GetListProductOutput getListProduct(int page, int size, String search, Category category, Integer priceStart,
			Integer priceEnd) {
		StringBuffer sql = new StringBuffer();
		GetListProductOutput result = new GetListProductOutput();
		Pagination pagging = new Pagination();

		try {
			// create query DB
			sql.append("SELECT p FROM ");
			sql.append(Product.class.getName());
			sql.append(" p ");

			if (!StringUtils.isEmpty(search)) {
				sql.append(" WHERE p.name LIKE :productName ");
			}

			if (!ObjectUtils.isEmpty(category)) {
				if (!StringUtils.isEmpty(search)) {
					sql.append(" AND ");
				} else
					sql.append(" WHERE ");
				sql.append(" p.category = :category ");
			}

			if (priceStart != null && priceEnd != null) {
				if (!StringUtils.isEmpty(search) || !ObjectUtils.isEmpty(category)) {
					sql.append(" AND ");
				} else
					sql.append(" WHERE ");
				sql.append(" p.price BETWEEN ");
				sql.append(priceStart);
				sql.append(" AND ");
				sql.append(priceEnd);
			} else if (priceStart != null && priceEnd == null) {
				if (!StringUtils.isEmpty(search) || !ObjectUtils.isEmpty(category)) {
					sql.append(" AND ");
				} else
					sql.append(" WHERE ");
				sql.append(" p.price >= ");
				sql.append(priceStart);
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
			if (!StringUtils.isEmpty(search)) {
				query.setParameter("productName", "%" + search + "%");
			}
			if (!ObjectUtils.isEmpty(category)) {
				query.setParameter("category", category);
			}

			// set data output
			pagging.setPage(page);
			pagging.setSize(size);
			pagging.setTotalRecord(getTotalRecord(search, category, priceStart, priceEnd));

			result.setPagination(pagging);

			// execute query and return data
			result.setProducts(query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	private Long getTotalRecord(String search, Category category, Integer priceStart, Integer priceEnd) {
		StringBuffer sql = new StringBuffer();
		try {
			// create query DB
			sql.append("SELECT count(*) FROM ");
			sql.append(Product.class.getName());
			sql.append(" p ");

			if (!StringUtils.isEmpty(search)) {
				sql.append(" WHERE p.name LIKE :productName ");
			}

			if (!ObjectUtils.isEmpty(category)) {
				if (!StringUtils.isEmpty(search)) {
					sql.append(" AND ");
				} else
					sql.append(" WHERE ");
				sql.append(" p.category = :category ");
			}

			if (priceStart != null && priceEnd != null) {
				if (!StringUtils.isEmpty(search) || !ObjectUtils.isEmpty(category)) {
					sql.append(" AND ");
				} else
					sql.append(" WHERE ");
				sql.append(" p.price BETWEEN ");
				sql.append(priceStart);
				sql.append(" AND ");
				sql.append(priceEnd);
			} else if (priceStart != null && priceEnd == null) {
				if (!StringUtils.isEmpty(search) || !ObjectUtils.isEmpty(category)) {
					sql.append(" AND ");
				} else
					sql.append(" WHERE ");
				sql.append(" p.price >= ");
				sql.append(priceStart);
			}

			Query query = entityManager.createQuery(sql.toString());

			// set data to parameters of query
			if (!StringUtils.isEmpty(search)) {
				query.setParameter("productName", "%" + search + "%");
			}
			if (!ObjectUtils.isEmpty(category)) {
				query.setParameter("category", category);
			}

			// execute query and return data
			return (Long) query.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}

	@SuppressWarnings("unchecked")
	public GetListProductOutput getListProduct(int page, int size, String search) {
		StringBuffer sql = new StringBuffer();
		GetListProductOutput result = new GetListProductOutput();
		Pagination pagging = new Pagination();

		try {
			// create query DB
			sql.append("SELECT p FROM ");
			sql.append(Product.class.getName());
			sql.append(" p ");

			if (!StringUtils.isEmpty(search)) {
				sql.append(" WHERE p.name LIKE :productName ");
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
			if (!StringUtils.isEmpty(search)) {
				query.setParameter("productName", "%" + search + "%");
			}

			// set data output
			pagging.setPage(page);
			pagging.setSize(size);
			pagging.setTotalRecord(getTotalRecord(search));

			result.setPagination(pagging);

			// execute query and return data
			result.setProducts(query.getResultList());
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	private Long getTotalRecord(String search) {
		StringBuffer sql = new StringBuffer();
		try {
			// create query DB
			sql.append("SELECT count(*) FROM ");
			sql.append(Product.class.getName());
			sql.append(" p ");

			if (!StringUtils.isEmpty(search)) {
				sql.append(" WHERE p.name LIKE :productName ");
			}
			Query query = entityManager.createQuery(sql.toString());

			// set data to parameters of query
			if (!StringUtils.isEmpty(search)) {
				query.setParameter("productName", "%" + search + "%");
			}

			// execute query and return data
			return (Long) query.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}
}