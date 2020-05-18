package com.ncs.model.output;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ncs.model.entity.Order;

import lombok.Data;

@Data
public class OrderOutput2 {

	List<Order> orders;
	Pageable pageable;
}
