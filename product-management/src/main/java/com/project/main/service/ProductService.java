package com.project.main.service;

import com.project.main.dto.CommonDTO;
import com.project.main.dto.ProductDTO;
import com.project.main.dto.UserDTO;

public interface ProductService {

	public CommonDTO findByProductId(Long productId);

	public String saveProduct(ProductDTO productDTO);

	public UserDTO getUser(Integer id) throws Exception;
}
