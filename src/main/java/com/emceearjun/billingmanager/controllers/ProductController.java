package com.emceearjun.billingmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emceearjun.billingmanager.constants.MessageConstants;
import com.emceearjun.billingmanager.entities.ProductEntity;
import com.emceearjun.billingmanager.exceptions.ProductExistsException;
import com.emceearjun.billingmanager.exceptions.ProductNotFoundException;
import com.emceearjun.billingmanager.models.ResponseModel;
import com.emceearjun.billingmanager.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/view")
	public ResponseEntity<ResponseModel<List<ProductEntity>>> viewAllProducts()
			throws NoSuchMethodException, SecurityException {

		List<ProductEntity> products = null;
		try {
			products = productService.getAllProducts();
		} catch (Exception e) {
			return new ResponseModel<List<ProductEntity>>()
					.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}

		return new ResponseModel<List<ProductEntity>>()
				.setStatus(HttpStatus.OK)
				.setSuccess(true)
				.setMessage("List of products")
				.setPayload(products)
				.build();
	}
	
	@GetMapping(path = "/view/{productId}")
	public ResponseEntity<ResponseModel<ProductEntity>> viewProduct(@PathVariable("productId") String productId) {

		ProductEntity product = null;
		try {
			product = productService.getProductById(productId);
		} catch (ProductNotFoundException e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.BAD_REQUEST)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		} catch (Exception e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}

		return new ResponseModel<ProductEntity>()
				.setStatus(HttpStatus.OK)
				.setSuccess(true)
				.setMessage("List of products")
				.setPayload(product)
				.build();
	}

	@PostMapping(path = "/add")
	public ResponseEntity<ResponseModel<ProductEntity>> addProduct(@RequestBody ProductEntity newProduct) {

		ProductEntity addedProduct = null;
		
		try {
			addedProduct = productService.addProduct(newProduct);
		}
		catch (ProductExistsException e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.BAD_REQUEST)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}
		catch (Exception e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}

		return new ResponseModel<ProductEntity>()
				.setStatus(HttpStatus.OK)
				.setSuccess(true)
				.setMessage(MessageConstants.PRODUCT_ADDED.formatted(addedProduct.getProductId()))
				.setPayload(addedProduct)
				.build();
	}
	
	@PutMapping(path = "/update/{productId}")
	public ResponseEntity<ResponseModel<ProductEntity>> updateProduct(@PathVariable("productId") String productId, @RequestBody ProductEntity productToUpdate) {

		ProductEntity updatedProduct = null;
		
		try {
			updatedProduct = productService.updateProduct(productId, productToUpdate);
		}
		catch (ProductNotFoundException e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.BAD_REQUEST)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}
		catch (Exception e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setSuccess(false)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}

		return new ResponseModel<ProductEntity>()
				.setStatus(HttpStatus.OK)
				.setSuccess(true)
				.setMessage(MessageConstants.PRODUCT_UPDATED.formatted(updatedProduct.getProductId()))
				.setPayload(updatedProduct)
				.build();
	}
	
	@DeleteMapping(path = "/remove/{productId}")
	public ResponseEntity<ResponseModel<ProductEntity>> removeProduct(@PathVariable("productId") String productId) {

		boolean isProductRemoved = false;
		
		try {
			isProductRemoved = productService.removeProduct(productId);
		}
		catch (ProductNotFoundException e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.BAD_REQUEST)
					.setSuccess(isProductRemoved)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}
		catch (Exception e) {
			return new ResponseModel<ProductEntity>()
					.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setSuccess(isProductRemoved)
					.setMessage(e.getMessage())
					.setPayload(null)
					.build();
		}

		return new ResponseModel<ProductEntity>()
				.setStatus(HttpStatus.OK)
				.setSuccess(isProductRemoved)
				.setMessage(MessageConstants.PRODUCT_REMOVED.formatted(productId))
				.setPayload(null)
				.build();
	}

}