package com.websiteshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.websiteshop.dao.CategoryDAO;
import com.websiteshop.entity.Category;
import com.websiteshop.entity.Product;
import com.websiteshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO cdao;
	
	public CategoryServiceImpl(CategoryDAO categoryDAO) {
		this.cdao = categoryDAO;
	}
	

	@Override
	public <S extends Category> S save(S entity) {
		return cdao.save(entity);
	}


	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}

	@Override
	public List<Category> findAllById(Iterable<Long> ids) {
		return cdao.findAllById(ids);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return cdao.saveAll(entities);
	}

	@Override
	public void flush() {
		cdao.flush();
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return cdao.saveAndFlush(entity);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cdao.saveAllAndFlush(entities);
	}

	

	@Override
	public Optional<Category> findById(Long id) {
		return cdao.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Category> entities) {
		cdao.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return cdao.existsById(id);
	}


	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		cdao.deleteAllInBatch(entities);
	}

	

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		cdao.deleteAllByIdInBatch(ids);
	}

	
	@Override
	public long count() {
		return cdao.count();
	}

	@Override
	public void deleteAllInBatch() {
		cdao.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		cdao.deleteById(id);
	}

	@Override
	public Category getOne(Long id) {
		return cdao.getOne(id);
	}

	@Override
	public void delete(Category entity) {
		cdao.delete(entity);
	}

	@Override
	public Category getById(Long id) {
		return cdao.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		cdao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		cdao.deleteAll(entities);
	}

	@Override
	public Category getReferenceById(Long id) {
		return cdao.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		cdao.deleteAll();
	}


}
