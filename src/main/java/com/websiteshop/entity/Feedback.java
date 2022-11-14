package com.websiteshop.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "FeedBacks")
public class Feedback  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedbackId;
	private String username;
	private Long productId;
	private String discription;
}
