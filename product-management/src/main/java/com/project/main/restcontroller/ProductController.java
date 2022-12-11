package com.project.main.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.main.dto.CommonDTO;
import com.project.main.dto.ProductDTO;
import com.project.main.dto.UserDTO;
import com.project.main.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/get/product")
	public CommonDTO findByProductId(@RequestParam Long productId) {

		return productService.findByProductId(productId);

	}

	@PostMapping("/save/product")
	public String saveProduct(@RequestBody ProductDTO productDTO) {

		return productService.saveProduct(productDTO);
	}

	@GetMapping("/get/user/{id}")
	public UserDTO getUser(@PathVariable Integer id) throws Exception {

		return productService.getUser(id);
	}

}
