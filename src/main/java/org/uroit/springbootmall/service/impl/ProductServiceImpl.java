package org.uroit.springbootmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uroit.springbootmall.dao.ProductDao;
import org.uroit.springbootmall.model.Product;
import org.uroit.springbootmall.service.ProductService;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public Product getProductById(Integer productId){
        Product product = productDao.getProductById(productId);
        return product;
    }
}
