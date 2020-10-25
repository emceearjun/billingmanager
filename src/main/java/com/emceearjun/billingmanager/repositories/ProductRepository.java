package com.emceearjun.billingmanager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emceearjun.billingmanager.entities.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

}