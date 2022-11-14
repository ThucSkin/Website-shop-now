package com.websiteshop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import com.websiteshop.entity.Feedback;

public interface FeedbackService {

	<S extends Feedback> List<S> findAll(Example<S> example, Sort sort);

	<S extends Feedback> List<S> findAll(Example<S> example);

	void deleteAll();

	Feedback getReferenceById(Long id);

	void deleteAll(Iterable<? extends Feedback> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Feedback getById(Long id);

	void delete(Feedback entity);

	Feedback getOne(Long id);

	void deleteById(Long id);

	void deleteAllInBatch();

	long count();

	<S extends Feedback, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	<S extends Feedback> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<Feedback> entities);

	<S extends Feedback> long count(Example<S> example);

	boolean existsById(Long id);

	void deleteInBatch(Iterable<Feedback> entities);

	Optional<Feedback> findById(Long id);

	<S extends Feedback> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Feedback> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Feedback> S saveAndFlush(S entity);

	void flush();

	<S extends Feedback> List<S> saveAll(Iterable<S> entities);

	List<Feedback> findAllById(Iterable<Long> ids);

	List<Feedback> findAll(Sort sort);

	Page<Feedback> findAll(Pageable pageable);

	List<Feedback> findAll();

	<S extends Feedback> Optional<S> findOne(Example<S> example);

	<S extends Feedback> S save(S entity);
}
