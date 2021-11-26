package com.seraleman.product_ms.product.dao.services;

import com.seraleman.product_ms.product.Product;

import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<?> list();

    public ResponseEntity<?> show(String id);

    public ResponseEntity<?> create(Product product);

    public ResponseEntity<?> update(String id, Product product);

    public ResponseEntity<?> delete(String id);
}
