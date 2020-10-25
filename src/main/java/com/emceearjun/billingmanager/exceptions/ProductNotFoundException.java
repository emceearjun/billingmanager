package com.emceearjun.billingmanager.exceptions;

import com.emceearjun.billingmanager.constants.MessageConstants;

public class ProductNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public ProductNotFoundException(String productId) {
		this.message = String.format(MessageConstants.PRODUCT_NOT_FOUND, productId);
	}

	public String getMessage() {
		return this.message;
	}
}
