
//  package com.websiteshop.entity;
//  
//  import java.io.Serializable;
//  
//  import javax.persistence.Entity;
//  import javax.persistence.JoinColumn;
//  import javax.persistence.ManyToOne;
//  
//  import javax.persistence.Table;
//  
//  
//  
//  import lombok.Data;
//  
//  @SuppressWarnings("serial")
//  
//  @Data
//  
//  @Entity @Table(name = "Statitic")
//  public class Statitic implements Serializable{
//  
//  @ManyToOne
//  
//  @JoinColumn(name = "Order")
//  Order order;
//  
//  @ManyToOne
//  
//  @JoinColumn(name = "OrderDetailId")
//  OrderDetail OrderDetail;
//  }
// 