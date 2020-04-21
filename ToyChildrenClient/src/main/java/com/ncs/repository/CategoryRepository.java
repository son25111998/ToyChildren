package com.ncs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findAll();
}
