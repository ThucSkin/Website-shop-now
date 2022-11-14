package com.websiteshop.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteshop.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {
	
	
}
