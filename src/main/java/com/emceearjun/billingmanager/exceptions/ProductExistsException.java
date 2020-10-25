package com.emceearjun.billingmanager.exceptions;

import com.emceearjun.billingmanager.constants.MessageConstants;

public class ProductExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public ProductExistsException(String productId) {
		this.message = String.format(MessageConstants.PRODUCT_ID_EXISTS, productId);
	}

	public String getMessage() {
		return this.message;
	}
}
