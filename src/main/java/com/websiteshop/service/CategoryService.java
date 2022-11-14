package com.websiteshop.service;

import java.util.List;
import java.util.Optional;

import com.websiteshop.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Optional<Category> findById(Long id);

	void deleteAll();

	Category getReferenceById(Long id);

	void deleteAll(Iterable<? extends Category> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Category getById(Long id);

	void delete(Category entity);

	Category getOne(Long id);

	void deleteById(Long categoryId);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Category> entities);

	boolean existsById(Long id);

	void deleteInBatch(Iterable<Category> entities);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	void flush();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<Long> ids);

	<S extends Category> S save(S entity);

}
