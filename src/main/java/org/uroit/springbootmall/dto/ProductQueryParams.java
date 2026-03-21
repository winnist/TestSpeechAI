package org.uroit.springbootmall.dto;

import org.uroit.springbootmall.constant.ProductCategory;

public class ProductQueryParams {
    private ProductCategory category;
    private String search;
    private String orderby;
    private String sort;

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
