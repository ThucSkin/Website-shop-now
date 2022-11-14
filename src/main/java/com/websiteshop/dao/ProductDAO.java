package com.websiteshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.websiteshop.entity.Product;

@Service
public interface ProductDAO extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.category.categoryId=?1")
	List<Product> findByCategoryId(Long cid);

	List<Product> findByNameContaining(String name);
}
