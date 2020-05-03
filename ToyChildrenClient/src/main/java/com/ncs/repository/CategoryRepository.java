package com.ncs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findAll();
	@Query(value = "SELECT * FROM category WHERE name_category LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<Category> findAllCategory(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM category WHERE name_category  LIKE %:name%", nativeQuery = true)
    Page<Category> findNameCategory(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM category WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<Category> findStatusCategory(@Param("status") String status, Pageable pageable);

	Category findById(int id);

    Page<Category> findAll(Specification<Category> advanceFilter,Pageable pagerable);
}
