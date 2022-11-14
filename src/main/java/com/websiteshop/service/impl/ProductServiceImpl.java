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

import com.websiteshop.dao.ProductDAO;
import com.websiteshop.entity.Product;
import com.websiteshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO pdao;
    
    @Override
	public List<Product> findByNameContaining(String name) {
		return pdao.findByNameContaining(name);
	}

    @Override
    public List<Product> findByCategoryId(Long cid) {
        return pdao.findByCategoryId(cid);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return pdao.save(entity);
    }


    @Override
    public List<Product> findAll() {
        return pdao.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return pdao.findAll(pageable);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return pdao.findAll(sort);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> ids) {
        return pdao.findAllById(ids);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return pdao.saveAll(entities);
    }

    @Override
    public void flush() {
        pdao.flush();
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return pdao.saveAndFlush(entity);
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return pdao.saveAllAndFlush(entities);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return pdao.findById(id);
    }

    @Override
    public void deleteInBatch(Iterable<Product> entities) {
        pdao.deleteInBatch(entities);
    }

    @Override
    public boolean existsById(Long id) {
        return pdao.existsById(id);
    }


    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
        pdao.deleteAllInBatch(entities);
    }


    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        pdao.deleteAllByIdInBatch(ids);
    }


    @Override
    public long count() {
        return pdao.count();
    }

    @Override
    public void deleteAllInBatch() {
        pdao.deleteAllInBatch();
    }

    @Override
    public void deleteById(Long id) {
        pdao.deleteById(id);
    }

    @Override
    public Product getOne(Long id) {
        return pdao.getOne(id);
    }

    @Override
    public void delete(Product entity) {
        pdao.delete(entity);
    }

    @Override
    public Product getById(Long id) {
        return pdao.getById(id);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        pdao.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        pdao.deleteAll(entities);
    }

    @Override
    public Product getReferenceById(Long id) {
        return pdao.getReferenceById(id);
    }

    @Override
    public void deleteAll() {
        pdao.deleteAll();
    }

}
