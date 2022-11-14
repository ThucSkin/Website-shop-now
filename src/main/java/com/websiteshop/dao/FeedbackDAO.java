package com.websiteshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websiteshop.entity.Feedback;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Long> {


}
