package com.ncs.repositoryclient;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ncs.model.entity.Manufacturer;;

public interface ManufacturerClientRepository extends JpaRepository<Manufacturer, Integer>,JpaSpecificationExecutor<Manufacturer>{
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ncs.model.entity.Manufacturer;;

public interface ManufacturerClientRepository
		extends JpaRepository<Manufacturer, Integer>, JpaSpecificationExecutor<Manufacturer> {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

}
