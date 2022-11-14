package com.websiteshop.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto implements Serializable {
	private static final long serialVersionUID = 1L;
    private Long orderDetailId;
    private Double price;
    private Double discount;
    private String status;
    private Integer quantity;
    private String discription;

}
