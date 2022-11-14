package com.websiteshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteshop.dao.RoleDAO;
import com.websiteshop.entity.Role;
import com.websiteshop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO rdao;

	@Override
	public List<Role> findAll() {
		return rdao.findAll();
	}

}
