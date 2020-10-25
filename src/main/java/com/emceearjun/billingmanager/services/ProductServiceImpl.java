package com.emceearjun.billingmanager.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emceearjun.billingmanager.entities.ProductEntity;
import com.emceearjun.billingmanager.exceptions.ProductExistsException;
import com.emceearjun.billingmanager.exceptions.ProductNotFoundException;
import com.emceearjun.billingmanager.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public ProductEntity getProductById(String productId) throws ProductNotFoundException {
		ProductEntity productFound = null;

		try {
			productFound = productRepository.findById(productId).get();
		} catch (NoSuchElementException e) {
			throw new ProductNotFoundException(productId);
		}

		return productFound;
	}

	@Override
	public ProductEntity addProduct(ProductEntity newProduct) throws ProductExistsException {
		String productId = newProduct.getProductId();
		boolean productIdExists = productRepository.existsById(productId);
		if (productIdExists) {
			throw new ProductExistsException(productId);
		}
		ProductEntity productToAdd = new ProductEntity(newProduct.getName(), newProduct.getDescription(),
				newProduct.getPrice());
		return productRepository.insert(productToAdd);
	}

	@Override
	public ProductEntity updateProduct(String productId, ProductEntity updatedProduct) throws ProductNotFoundException {
		try {
			productRepository.findById(productId).get();
			updatedProduct.setProductId(productId);
			return productRepository.save(updatedProduct);
		} catch (NoSuchElementException e) {
			throw new ProductNotFoundException(productId);
		}
	}

	@Override
	public boolean removeProduct(String productId) throws ProductNotFoundException {
		try {
			productRepository.findById(productId).get();
			productRepository.deleteById(productId);
			return true;
		} catch (NoSuchElementException e) {
			throw new ProductNotFoundException(productId);
		}
	}

}
