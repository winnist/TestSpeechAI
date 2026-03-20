package org.uroit.springbootmall.dao;

import org.uroit.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
