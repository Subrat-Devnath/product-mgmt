package com.project.main.repositoryimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.config.ResourceNotFountException;
import com.project.main.dto.ProductDTO;
import com.project.main.entity.DiscountEntity;
import com.project.main.entity.ProductEntity;
import com.project.main.product.Dao.DiscountRepository;
import com.project.main.product.Dao.ProductRepository;
import com.project.main.repository.RepositoryService;

@Service
public class RempositoryServiceImpl implements RepositoryService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductEntity getProductByProductId(Long productId) {
		if (productId == null) {
			return null;
		} else {
			return productRepository.findById(productId)
					.orElseThrow(() -> new ResourceNotFountException("User", "Id", productId));
		}

	}

	@Override
	public DiscountEntity getDiscountByProductCategory(String productCategory) {
		return discountRepository.getById(productCategory);
	}

	@Override
	public void saveProductAndDiscount(ProductDTO productDTO) {

		DiscountEntity discount = new DiscountEntity();

		ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);

		productRepository.save(productEntity);

		discount.setDeliveryCharge(productDTO.getDeliveryCharge());
		discount.setDiscount(productDTO.getDiscount());
		discount.setGst(productDTO.getGst());
		discount.setProductCategory(productDTO.getProductCategory());

		discountRepository.save(discount);

	}

}
