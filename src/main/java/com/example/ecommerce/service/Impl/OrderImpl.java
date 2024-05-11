package com.example.ecommerce.service.Impl;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }
    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
    @Override
    public void cancelOrder(Long id) {
       orderRepository.deleteById(id);
    }
}
