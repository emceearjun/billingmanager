package com.emceearjun.billingmanager.services;

import java.util.List;

import com.emceearjun.billingmanager.entities.ProductEntity;
import com.emceearjun.billingmanager.exceptions.ProductExistsException;
import com.emceearjun.billingmanager.exceptions.ProductNotFoundException;

public interface ProductService {
	List<ProductEntity> getAllProducts();
	ProductEntity getProductById(String productId) throws ProductNotFoundException;
	ProductEntity addProduct(ProductEntity newProduct) throws ProductExistsException;
	ProductEntity updateProduct(String productId, ProductEntity updatedProduct) throws ProductNotFoundException;
	boolean removeProduct(String productId) throws ProductNotFoundException;
}
