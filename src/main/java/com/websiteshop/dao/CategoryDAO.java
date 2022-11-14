package com.websiteshop.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.websiteshop.entity.Category;

@Service
public interface CategoryDAO extends JpaRepository<Category, Long> {

}
