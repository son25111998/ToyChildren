package com.ncs.repositoryclient;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Category;
import com.ncs.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Page<Product> findByCategory(Category category, Pageable pageable);

	Page<Product> findByNameLike(Pageable pageable, String search);

	Page<Product> findByCreateTimeBetween(Date startDate, Date endDate, Pageable pageable);
	

	// search by name
	@Query(value = "SELECT * FROM product WHERE name_product LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<Product> findAllProduct(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM product WHERE name_product LIKE %:name%", nativeQuery = true)
    Page<Product> findNameProduct(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM product WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<Product> findStatusProduct(@Param("status") String status, Pageable pageable);

	Product findById(int id);

    Page<Product> findAll(Specification<Product> advanceFilter, Pageable pageable);
    List<Product> findByManufacturerId(int manufacturerId);
    List<Product> findByCategoryId(int categoryId);
	@Query(value = "SELECT * FROM product\n" + 
			"ORDER BY product.PRICE desc", nativeQuery = true)
    Page<Product> findAllOderByPriceDesc(Pageable pageable);
	@Query(value = "SELECT * FROM product\n" + 
			"ORDER BY product.PRICE ", nativeQuery = true)
	Page<Product> findAllOderByPriceAsc(Pageable pageable);
	@Query(value = "SELECT * FROM product\n" + 
			"where product.CATEGORY_ID = a and product.MANUFACTURER_ID=b ", nativeQuery = true)
	Page<Product> findAllProductCategoryandManufacturer(@Param("a") int a, @Param("b") int  b,Pageable pageable);


}
