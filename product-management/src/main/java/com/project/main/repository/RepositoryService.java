package com.project.main.repository;

import com.project.main.dto.ProductDTO;
import com.project.main.entity.DiscountEntity;
import com.project.main.entity.ProductEntity;


public interface RepositoryService {
	
	public DiscountEntity getDiscountByProductCategory(String productCategory);
	
	public ProductEntity getProductByProductId(Long productId);
	
	public void saveProductAndDiscount(ProductDTO productDTO);
	
	

}
