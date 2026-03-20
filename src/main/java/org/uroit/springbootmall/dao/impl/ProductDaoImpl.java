package org.uroit.springbootmall.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.uroit.springbootmall.dao.ProductDao;
import org.uroit.springbootmall.model.Product;
import org.uroit.springbootmall.rowmapper.ProductRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate namedParameterJdbcTemplate;

    public Product getProductById(Integer productId){

        String sql =
                "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date"+
                        " FROM product WHERE product_id=?";

        List<Product> productListlist = namedParameterJdbcTemplate.query(sql, new ProductRowMapper(), productId) ;

        if(productListlist.size()>0){
            return productListlist.get(0);
        }else{
            return null;
        }

    }
}
