package com.ncs.repositoryclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.entity.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{

}
