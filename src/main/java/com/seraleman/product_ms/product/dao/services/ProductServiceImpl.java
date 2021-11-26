package com.seraleman.product_ms.product.dao.services;

import java.util.ArrayList;
import java.util.List;

import com.seraleman.product_ms.product.Product;
import com.seraleman.product_ms.product.dao.IProductDao;
import com.seraleman.product_ms.services.response.IResponseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IResponseService response;

    @Override
    public ResponseEntity<?> list() {

        List<Product> products = new ArrayList<>();

        try {
            products = productDao.findAll();
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }

        if (products.size() == 0) {
            return response.empty();
        }
        return response.list(products);
    }

    @Override
    public ResponseEntity<?> show(String id) {

        Product product = null;

        try {
            product = productDao.findById(id).orElse(null);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }

        if (product == null) {
            return response.notFound(id);
        }
        return response.found(product);
    }

    @Override
    public ResponseEntity<?> create(Product product) {

        Product productNew = null;

        try {
            productNew = productDao.save(product);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
        return response.created(productNew);
    }

    @Override
    public ResponseEntity<?> update(String id, Product product) {

        Product productCurrent = null;

        try {
            productCurrent = productDao.findById(id).orElse(null);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }

        if (productCurrent == null) {
            return response.empty();
        }

        try {
            productCurrent.setBrand(product.getBrand());
            productCurrent.setCategory(product.getCategory());
            productCurrent.setName(product.getName());
            productDao.save(productCurrent);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
        return response.updated(productCurrent);
    }

    @Override
    public ResponseEntity<?> delete(String id) {

        Product product = null;

        try {
            product = productDao.findById(id).orElse(null);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }

        if (product == null) {
            return response.notFound(id);
        }

        try {
            productDao.deleteById(id);
        } catch (DataAccessException e) {
            return response.errorDataAccess(e);
        }
        return response.deleted(id);
    }

}
