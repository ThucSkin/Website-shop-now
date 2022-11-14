package com.websiteshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderDetailId;
    @ManyToOne
    @JoinColumn(name = "Orderid")
    Order order;
    @ManyToOne
    @JoinColumn(name = "Productid")
    Product product;
    Integer price;
    Integer discount;
    // @Column(nullable = true)
    String status;
    Integer quantity;
    String discription;

}
