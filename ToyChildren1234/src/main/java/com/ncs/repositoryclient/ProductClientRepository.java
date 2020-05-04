package com.ncs.repositoryclient;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Category;
import com.ncs.model.entity.Product;

@Repository
public interface ProductClientRepository extends JpaRepository<Product, Integer> {
	Page<Product> findByCategory(Category category, Pageable pageable);

	Page<Product> findByNameLike(Pageable pageable, String search);

	Page<Product> findByCreateTimeBetween(Date startDate, Date endDate, Pageable pageable);

	Product findById(int id);

}
