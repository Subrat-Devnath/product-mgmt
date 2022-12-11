package com.project.main.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 6899863442128530723L;
	
	private Long productId;
	
	private String productName;
	
	private String productType;
	
	private String productCategory;
	
	private String productPrice;
	
	private DiscountDTO discountDto;
	
	private Double discount;

	private Double gst;

	private Integer deliveryCharge;

	
	
}
