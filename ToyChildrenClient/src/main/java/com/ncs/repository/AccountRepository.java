package com.ncs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);
	@Query(value = "SELECT * FROM account WHERE name_account LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
    Page<Account> findAllAccount(@Param("name") String name, @Param("status") String status, Pageable pageable);
	
	@Query(value = "SELECT * FROM account WHERE name_account LIKE %:name%", nativeQuery = true)
    Page<Account> findNameAccount(@Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT * FROM account WHERE statuss LIKE %:status%", nativeQuery = true)
    Page<Account> findStatusAccount(@Param("status") String status, Pageable pageable);

	Account findById(int id);

    Page<Account> findAll(Specification<Account> advanceFilter,Pageable pagerable);
}
