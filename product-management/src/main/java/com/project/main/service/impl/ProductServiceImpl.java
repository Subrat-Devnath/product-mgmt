package com.project.main.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.common.reflection.ClassDetailsByReflection;
import com.project.main.dto.Charges;
import com.project.main.dto.CommonDTO;
import com.project.main.dto.DiscountDTO;
import com.project.main.dto.ProductDTO;
import com.project.main.dto.UserDTO;
import com.project.main.entity.DiscountEntity;
import com.project.main.entity.ProductEntity;
import com.project.main.repository.RepositoryService;
import com.project.main.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CommonDTO findByProductId(Long productId) {
		CommonDTO commonDTO = new CommonDTO();
		try {
			logger.info("ID of product: {}", productId);

			ProductEntity findByProductId = repositoryService.getProductByProductId(productId);

			logger.info("Product: {} of  that id: {} ", findByProductId, productId);

			DiscountEntity discount = repositoryService
					.getDiscountByProductCategory(findByProductId.getProductCategory());

			ProductDTO productDto = modelMapper.map(findByProductId, ProductDTO.class);

			DiscountDTO discountDto = modelMapper.map(discount, DiscountDTO.class);

			productDto.setDiscountDto(discountDto);

			Double totalDisc = getDiscount(productDto, discountDto);

			Double gstAmount = getGST(discountDto, productDto);

			Double finalPrice = finalPrice(productDto, totalDisc, discountDto, gstAmount);

			Charges charges = new Charges();
			charges.setGst(gstAmount);
			charges.setDeliveryCharge(discountDto.getDeliveryCharge());

			commonDTO.setProductId(productDto.getProductId());
			commonDTO.setName(productDto.getProductName());
			commonDTO.setProductType(productDto.getProductType());
			commonDTO.setCategory(productDto.getProductCategory());
			commonDTO.setBasePrice(productDto.getProductPrice());
			commonDTO.setDiscount(totalDisc);
			commonDTO.setCharges(charges);
			commonDTO.setFinalPrice(finalPrice);

		} catch (Exception e) {
			logger.error("Error occour for that user", e);
		}

		return commonDTO;
	}

	private Double getDiscount(ProductDTO productDto, DiscountDTO discountDto) {

		Double discount = discountDto.getDiscount();
		int stringToInt = Integer.parseInt(productDto.getProductPrice());
		double intToDouble = stringToInt;

		Double productPrice = intToDouble;

		Double minDisc = 100 - discount;

		Double afterDiscountPrice = (minDisc * productPrice) / 100;

		return productPrice - afterDiscountPrice;
	}

	private Double getGST(DiscountDTO discountDto, ProductDTO productDto) {
		Double gst;
		Double discount;

		discount = discountDto.getDiscount();
		int stringToInt = Integer.parseInt(productDto.getProductPrice());
		double intToDouble = stringToInt;

		Double productPrice = intToDouble;

		Double minDisc = 100 - discount;

		Double afterDiscountPrice = (minDisc * productPrice) / 100;

		gst = discountDto.getGst();

		Double gstDouble = ((100 - gst) * afterDiscountPrice) / 100;

		return afterDiscountPrice - gstDouble;

	}

	private Double finalPrice(ProductDTO productDto, Double totalDisc, DiscountDTO discountDto, Double gstAmount) {
		int stringToInt = Integer.parseInt(productDto.getProductPrice());
		double intToDouble = stringToInt;
		Double totaDouble = (intToDouble - totalDisc);
		return totaDouble + gstAmount + discountDto.getDeliveryCharge();
	}

	@Override
	public String saveProduct(ProductDTO productDTO) {
		repositoryService.saveProductAndDiscount(productDTO);
		return "Product Saved";
	}

	@Override
	public UserDTO getUser(Integer id) throws Exception {
		String resource = "http://USER-MGMT/user-mgmt/user/get/user/";
		UserDTO user = restTemplate.getForObject(resource + id, UserDTO.class);
		UserDTO userAfter = new UserDTO();
		UserDTO setWithoutNullValuesFromDTO = ClassDetailsByReflection.setWithoutNullValuesFromDTO(user, userAfter);
		return setWithoutNullValuesFromDTO != null ? user : null;
	}
}
