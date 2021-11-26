package com.seraleman.product_ms.product.dao;

import com.seraleman.product_ms.product.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductDao extends MongoRepository<Product, String> {

}
