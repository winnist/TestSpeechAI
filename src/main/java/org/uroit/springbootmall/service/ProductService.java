package org.uroit.springbootmall.service;

import org.uroit.springbootmall.dto.ProductRequest;
import org.uroit.springbootmall.model.Product;

public interface ProductService {

     Product getProductById(Integer productId);

     Integer createProduct(ProductRequest productRequest);

     void  updateProduct(Integer productId, ProductRequest productRequest);
}
