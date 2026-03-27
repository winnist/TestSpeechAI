package org.uroit.springbootmall.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.uroit.springbootmall.model.Order;
import org.uroit.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    List<OrderItem> getOderItemsByOrderId(Integer orderId);
    Integer createOrder(Integer userId, Integer totalAmount);
    Order getOrderById(Integer orderId);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
