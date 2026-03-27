package org.uroit.springbootmall.dao;

import org.springframework.http.ResponseEntity;
import org.uroit.springbootmall.constant.ProductCategory;
import org.uroit.springbootmall.dto.ProductQueryParams;
import org.uroit.springbootmall.dto.ProductRequest;
import org.uroit.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    void updateStock(Integer productId, Integer stock);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);

}