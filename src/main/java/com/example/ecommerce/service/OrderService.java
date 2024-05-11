package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    List<Order> getAllOrders();

    Order getOrderById(long id);

    void updateOrder(Long id, Order order);

    void deleteOrder(long id);

    void cancelOrder(Long id);

    void updateOrderStatus(Long orderId, String newStatus);

}
