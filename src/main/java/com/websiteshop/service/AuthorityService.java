package com.websiteshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.websiteshop.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAll();

	public List<Authority> findAuthoritiesOfAdministrators();

	public Authority create(Authority auth);

	public void delete(Integer id);
}
