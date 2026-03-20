package org.uroit.springbootmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.uroit.springbootmall.constant.ProductCategory;
import org.uroit.springbootmall.dao.ProductDao;
import org.uroit.springbootmall.dto.ProductRequest;
import org.uroit.springbootmall.model.Product;
import org.uroit.springbootmall.service.ProductService;

import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public Product getProductById(Integer productId){
        Product product = productDao.getProductById(productId);
        return product;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    public void  updateProduct(Integer productId, ProductRequest productRequest){
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public List<Product> getProducts(ProductCategory category, String search) {
        return productDao.getProducts(category, search);
    }


}
