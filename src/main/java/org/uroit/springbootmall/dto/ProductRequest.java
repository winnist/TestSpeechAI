package org.uroit.springbootmall.dto;

import org.jspecify.annotations.NonNull;
import org.uroit.springbootmall.constant.ProductCategory;

public class ProductRequest {
    @NonNull
    private    String productName;
    @NonNull
    private ProductCategory category;
    @NonNull
    private    String imageUrl;
    @NonNull
    private    Integer price;
    @NonNull
    private    Integer stock;

    private    String description;

    public @NonNull String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    public @NonNull ProductCategory getCategory() {
        return category;
    }

    public void setCategory(@NonNull ProductCategory category) {
        this.category = category;
    }

    public @NonNull String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public @NonNull Integer getPrice() {
        return price;
    }

    public void setPrice(@NonNull Integer price) {
        this.price = price;
    }

    public @NonNull Integer getStock() {
        return stock;
    }

    public void setStock(@NonNull Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
