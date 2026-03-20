package org.uroit.springbootmall.service;

import org.springframework.http.ResponseEntity;
import org.uroit.springbootmall.dto.ProductRequest;
import org.uroit.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

     Product getProductById(Integer productId);

     Integer createProduct(ProductRequest productRequest);

     void  updateProduct(Integer productId, ProductRequest productRequest);

     void deleteProductById(Integer productId);

     List<Product> getProducts();
}
