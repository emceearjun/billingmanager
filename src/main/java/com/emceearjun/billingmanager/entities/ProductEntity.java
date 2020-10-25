package com.emceearjun.billingmanager.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.emceearjun.billingmanager.utils.CommonUtils;

@Document(collection = "products")
public class ProductEntity {

	@Id
	private String productId;
	
	private String name;
	private String description;
	private double price;
	
	public ProductEntity(String name, String description, double price) {
		super();
		this.productId = CommonUtils.generateProductId();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
