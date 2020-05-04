package com.ncs.repositoryclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ncs.model.entity.Manufacturer;;

public interface ManufacturerClientRepository
		extends JpaRepository<Manufacturer, Integer>, JpaSpecificationExecutor<Manufacturer> {

}
