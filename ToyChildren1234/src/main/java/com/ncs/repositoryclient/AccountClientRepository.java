package com.ncs.repositoryclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Account;

@Repository
public interface AccountClientRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);
}
