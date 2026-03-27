package org.uroit.springbootmall.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.uroit.springbootmall.model.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(resultSet.getInt("order_id"));
        orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
        orderItem.setProductId(resultSet.getInt("product_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setAmount(resultSet.getInt("amount"));

        orderItem.setProductName(resultSet.getString("product_name"));
        orderItem.setImageUrl(resultSet.getString("image_url"));
        return orderItem;
    }
}
