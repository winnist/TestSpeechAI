package org.uroit.springbootmall.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.uroit.springbootmall.dao.OrderDao;
import org.uroit.springbootmall.dto.OrderQueryParams;
import org.uroit.springbootmall.model.Order;
import org.uroit.springbootmall.model.OrderItem;
import org.uroit.springbootmall.rowmapper.OrderItemRowMapper;
import org.uroit.springbootmall.rowmapper.OrderRowMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "select count(*) from `order` where 1=1";
        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilter(sql, map, orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date from `order` where 1=1 ";
        Map<String, Object> map = new HashMap<>();
        // 查詢條件
        sql = addFilter(sql, map, orderQueryParams);

        //排序
        sql = sql + " order by created_date desc";

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        for( Order i : orderList){
            List<OrderItem> items = this.getOderItemsByOrderId(i.getOrderId());
            i.setOrderItemList(items);
        }
        return orderList;
    }

    @Override
    public List<OrderItem> getOderItemsByOrderId(Integer orderId) {
        String sql = "select a.order_item_id, a.amount, a.quantity, a.product_id, a.order_id, b.product_name, b.image_url "+
                " from order_item a left join product b " +
                " on a.product_id = b.product_id " +
                " where a.order_id = :orderId";
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
        return orderItemList;
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date) " +
                " VALUES(:userId, :totalAmount, :createdDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date from `order` "
                +" where order_Id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        Order order = namedParameterJdbcTemplate.queryForObject(sql, map, new OrderRowMapper());

        return order;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        // 使用batchUpdate 一次性加入數據, 效率更高
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount) " +
                " VALUES(:orderId, :productId, :quantity, :amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
        for(int i = 0; i<orderItemList.size(); i++){
            OrderItem orderItem = orderItemList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    private String addFilter(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams){
        if(orderQueryParams.getUserId() != null){
            sql = sql + " AND user_id = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }
}
