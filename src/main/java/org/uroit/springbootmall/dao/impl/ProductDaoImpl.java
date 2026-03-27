package org.uroit.springbootmall.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.uroit.springbootmall.constant.ProductCategory;
import org.uroit.springbootmall.dao.ProductDao;
import org.uroit.springbootmall.dto.ProductQueryParams;
import org.uroit.springbootmall.dto.ProductRequest;
import org.uroit.springbootmall.model.Product;
import org.uroit.springbootmall.rowmapper.ProductRowMapper;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "select count(*) from product where 1=1";

        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    public Product getProductById(Integer productId){

        String sql =
                "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date"+
                        " FROM product WHERE product_id=:productId";

        HashMap<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productListlist = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper()) ;

        if(productListlist.size()>0){
            return productListlist.get(0);
        }else{
            return null;
        }


    }

    public Integer createProduct(ProductRequest productRequest){

        String sql =
                "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)"
                        + " VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        System.out.println(now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateStock(Integer productId, Integer stock){
        String sql = "update product set stock=:stock, last_modified_date=:lastModifiedDate "+
                " where product_id = :productId";
        Date now = new Date();
        HashMap<String, Object> map = new HashMap<>();
        map.put("stock", stock);
        map.put("productId", productId);
        map.put("lastModifiedDate", now);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql =
                "update product set product_name=:productName, category=:category, image_url=:imageUrl, price=:price, stock=:stock, description=:description, last_modified_date=:lastModifiedDate"
                +" WHERE product_Id=:productId";


        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();

        map.put("lastModifiedDate", now);
        System.out.println(now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "delete from product where product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams){


        String sql = "select * from product where 1=1";


        HashMap<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, productQueryParams);

        sql = sql +" ORDER BY " + productQueryParams.getOrderby() + " " +productQueryParams.getSort();

        sql = sql + " limit :limit offset :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());


        System.out.println(sql);
       // List<Product> products = namedParameterJdbcTemplate.query(sql, new ProductRowMapper());

        //總數
        List<Product> products = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return products;

    }

    private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams){
        if(productQueryParams.getCategory()!= null){
            System.out.println(productQueryParams.getCategory().toString());
            // sql = sql + " AND category = '"+category.toString()+"'";
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch() != null){
            //sql = sql + " AND product_name LIKE "+ "'%"+ search +"%'";
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%"+productQueryParams.getSearch()+"%");
        }
        return sql;
    }
}
