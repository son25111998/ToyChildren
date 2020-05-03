package com.ncs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ncs.model.entity.Manufacturer;;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>,JpaSpecificationExecutor<Manufacturer>{
	@Query(value = "SELECT * FROM manufacturer WHERE name_manufacturer LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<Manufacturer> findAllManufacturer(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM manufacturer WHERE name_manufacturerLIKE %:name%", nativeQuery = true)
    Page<Manufacturer> findNameManufacturer(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM manufacturer WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<Manufacturer> findStatusManufacturer(@Param("status") String status, Pageable pageable);

	Manufacturer findById(int id);

    Page<Manufacturer> findAll(Specification<Manufacturer> advanceFilter,Pageable pagerable);
}
