package com.project.main.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO implements Serializable {

	private static final long serialVersionUID = 238100299014033472L;

	private String productCategory;

	private Double discount;

	private Double gst;

	private Integer deliveryCharge;

	
}
