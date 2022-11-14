
package com.websiteshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websiteshop.dao.OrderDAO;
import com.websiteshop.dao.OrderDetailDAO;
import com.websiteshop.entity.Order;
import com.websiteshop.entity.OrderDetail;
import com.websiteshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO odao;

    @Autowired
    OrderDetailDAO ddao;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData, Order.class);
        odao.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        ddao.saveAll(details);

        return order;
    }

    @Override
    public Order findById(Long id) {
        return odao.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return odao.findByUsername(username);
    }

	@Override
	public <S extends Order> S save(S entity) {
		return odao.save(entity);
	}

	@Override
	public <S extends Order> Optional<S> findOne(Example<S> example) {
		return odao.findOne(example);
	}

	@Override
	public List<Order> findAll() {
		return odao.findAll();
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return odao.findAll(pageable);
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return odao.findAll(sort);
	}

	@Override
	public List<Order> findAllById(Iterable<Long> ids) {
		return odao.findAllById(ids);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		return odao.saveAll(entities);
	}

	@Override
	public void flush() {
		odao.flush();
	}

	@Override
	public <S extends Order> S saveAndFlush(S entity) {
		return odao.saveAndFlush(entity);
	}

	@Override
	public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
		return odao.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		return odao.findAll(example, pageable);
	}

	@Override
	public Optional<Order> findByIdd(Long id) {
		return odao.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Order> entities) {
		odao.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return odao.existsById(id);
	}

	@Override
	public <S extends Order> long count(Example<S> example) {
		return odao.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Order> entities) {
		odao.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Order> boolean exists(Example<S> example) {
		return odao.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		odao.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return odao.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return odao.count();
	}

	@Override
	public void deleteAllInBatch() {
		odao.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		odao.deleteById(id);
	}

	@Override
	public Order getOne(Long id) {
		return odao.getOne(id);
	}

	@Override
	public void delete(Order entity) {
		odao.delete(entity);
	}

	@Override
	public Order getById(Long id) {
		return odao.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		odao.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		odao.deleteAll(entities);
	}

	@Override
	public Order getReferenceById(Long id) {
		return odao.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		odao.deleteAll();
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example) {
		return odao.findAll(example);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
		return odao.findAll(example, sort);
	}

    

	

}