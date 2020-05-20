package com.ncs.repositoryclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Account;
import com.ncs.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByAccount(Account account);
	
	@Query("Select c from Customer c Where c.account.username =:username")
	Customer findByUserName(@Param("username") String username);
}
