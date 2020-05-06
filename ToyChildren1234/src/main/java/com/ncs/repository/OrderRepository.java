<<<<<<< HEAD
package com.ncs.repository;

import java.util.List;
=======
package com.ncs.repositoryclient;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

<<<<<<< HEAD
import com.ncs.entity.OrderDetailEntity;
import com.ncs.entity.OrderEntity;
import com.ncs.entity.ProductEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>,JpaSpecificationExecutor<OrderEntity>{
	@Query(value = "SELECT * FROM orderproduct WHERE name_oder LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<OrderEntity> findAllOrder(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM orderproduct WHERE name_oder  LIKE %:name%", nativeQuery = true)
    Page<OrderEntity> findNameOrder(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM orderproduct WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<OrderEntity> findStatusOrder(@Param("status") String status, Pageable pageable);

	OrderEntity findById(int id);

    Page<OrderEntity> findAll(Specification<OrderEntity> advanceFilter,Pageable pagerable);
    
	
=======
import com.ncs.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity> {
	@Query(value = "SELECT * FROM orderproduct WHERE name_oder LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
	Page<OrderEntity> findAllOrder(@Param("name") String name, @Param("status") String status, Pageable pageable);

	@Query(value = "SELECT * FROM orderproduct WHERE name_oder  LIKE %:name%", nativeQuery = true)
	Page<OrderEntity> findNameOrder(@Param("name") String name, Pageable pageable);

	@Query(value = "SELECT * FROM orderproduct WHERE statuss LIKE %:status%", nativeQuery = true)
	Page<OrderEntity> findStatusOrder(@Param("status") String status, Pageable pageable);

	OrderEntity findById(int id);

	Page<OrderEntity> findAll(Specification<OrderEntity> advanceFilter, Pageable pagerable);

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
}
