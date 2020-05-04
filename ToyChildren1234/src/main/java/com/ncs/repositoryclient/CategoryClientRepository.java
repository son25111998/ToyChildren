package com.ncs.repositoryclient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Category;

@Repository
public interface CategoryClientRepository extends JpaRepository<Category, Integer> {
	List<Category> findAll();

	Category findById(int id);

}
