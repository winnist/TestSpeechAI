package org.uroit.springbootmall.model;

import org.uroit.springbootmall.constant.ProductCategory;

import java.sql.Timestamp;
import java.util.Date;


public class Product {
    private    Integer productId;
    private    String productName;
    private    ProductCategory category;
    private    String imageUrl;
    private    Integer price;
    private    Integer stock;
    private    String description;
    private Timestamp createdDate;
    private Timestamp  lastModifiedDate;

    public Timestamp  getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp  createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp  lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
