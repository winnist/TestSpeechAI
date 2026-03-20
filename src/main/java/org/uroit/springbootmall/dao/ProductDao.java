package org.uroit.springbootmall.dao;

import org.uroit.springbootmall.dto.ProductRequest;
import org.uroit.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);
}