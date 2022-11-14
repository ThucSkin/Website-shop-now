package com.websiteshop.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long feedbackId;
	private String username;
	private Long productId;
	private String discription;
	private Boolean isEdit;
}
