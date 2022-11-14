package com.websiteshop.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteshop.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	
	
}
