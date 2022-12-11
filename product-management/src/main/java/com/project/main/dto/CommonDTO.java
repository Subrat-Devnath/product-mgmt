package com.project.main.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDTO implements Serializable {
	
    
	private static final long serialVersionUID = -4166468631510080180L;

	private Long productId;
	
	private String name;
	
	private String productType;
	
	private String category;
	
	private String basePrice;
	
	private Double discount;
	
	private Charges charges;
	
	private Double finalPrice;
	

}
