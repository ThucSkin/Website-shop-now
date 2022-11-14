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

import com.websiteshop.dao.FeedbackDAO;
import com.websiteshop.dao.OrderDAO;
import com.websiteshop.entity.Feedback;
import com.websiteshop.entity.Order;
import com.websiteshop.service.FeedbackService;
import com.websiteshop.service.OrderService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDAO fdao;

	@Override
	public <S extends Feedback> S save(S entity) {
		return fdao.save(entity);
	}

	@Override
	public <S extends Feedback> Optional<S> findOne(Example<S> example) {
		return fdao.findOne(example);
	}

	@Override
	public List<Feedback> findAll() {
		return fdao.findAll();
	}

	@Override
	public Page<Feedback> findAll(Pageable pageable) {
		return fdao.findAll(pageable);
	}

	@Override
	public List<Feedback> findAll(Sort sort) {
		return fdao.findAll(sort);
	}

	@Override
	public List<Feedback> findAllById(Iterable<Long> ids) {
		return fdao.findAllById(ids);
	}

	@Override
	public <S extends Feedback> List<S> saveAll(Iterable<S> entities) {
		return fdao.saveAll(entities);
	}

	@Override
	public void flush() {
		fdao.flush();
	}

	@Override
	public <S extends Feedback> S saveAndFlush(S entity) {
		return fdao.saveAndFlush(entity);
	}

	@Override
	public <S extends Feedback> List<S> saveAllAndFlush(Iterable<S> entities) {
		return fdao.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Feedback> Page<S> findAll(Example<S> example, Pageable pageable) {
		return fdao.findAll(example, pageable);
	}

	@Override
	public Optional<Feedback> findById(Long id) {
		return fdao.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Feedback> entities) {
		fdao.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return fdao.existsById(id);
	}

	@Override
	public <S extends Feedback> long count(Example<S> example) {
		return fdao.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Feedback> entities) {
		fdao.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Feedback> boolean exists(Example<S> example) {
		return fdao.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		fdao.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Feedback, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return fdao.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return fdao.count();
	}

	@Override
	public void deleteAllInBatch() {
		fdao.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		fdao.deleteById(id);
	}

	@Override
	public Feedback getOne(Long id) {
		return fdao.getOne(id);
	}

	@Override
	public void delete(Feedback entity) {
		fdao.delete(entity);
	}

	@Override
	public Feedback getById(Long id) {
		return fdao.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		fdao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Feedback> entities) {
		fdao.deleteAll(entities);
	}

	@Override
	public Feedback getReferenceById(Long id) {
		return fdao.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		fdao.deleteAll();
	}

	@Override
	public <S extends Feedback> List<S> findAll(Example<S> example) {
		return fdao.findAll(example);
	}

	@Override
	public <S extends Feedback> List<S> findAll(Example<S> example, Sort sort) {
		return fdao.findAll(example, sort);
	}
}
