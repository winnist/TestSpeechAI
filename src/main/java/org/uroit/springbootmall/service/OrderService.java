package org.uroit.springbootmall.service;

import org.uroit.springbootmall.dto.CreateOrderRequest;
import org.uroit.springbootmall.dto.OrderQueryParams;
import org.uroit.springbootmall.model.Order;
import org.uroit.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
