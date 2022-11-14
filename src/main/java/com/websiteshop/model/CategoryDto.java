package com.websiteshop.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long categoryId;
	@NotEmpty
	private String name;
	private Boolean isEdit = false;
}
