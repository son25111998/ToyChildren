package com.ncs.repositoryclient;

import java.util.Date;
<<<<<<< HEAD
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Category;
import com.ncs.model.entity.Product;

@Repository
public interface ProductClientRepository extends JpaRepository<Product, Integer> {
	Page<Product> findByCategory(Category category, Pageable pageable);

	Page<Product> findByNameLike(Pageable pageable, String search);

	Page<Product> findByCreateTimeBetween(Date startDate, Date endDate, Pageable pageable);
<<<<<<< HEAD
	

	Product findById(int id);




=======

	Product findById(int id);

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
