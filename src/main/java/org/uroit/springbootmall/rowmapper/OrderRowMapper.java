package org.uroit.springbootmall.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.uroit.springbootmall.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {

        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setCreatedDate(resultSet.getTimestamp("created_date"));
        order.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        order.setTotalAmount(resultSet.getInt("total_amount"));

        return order;
    }
}
