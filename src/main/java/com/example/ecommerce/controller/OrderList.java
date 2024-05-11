package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping
public class OrderList {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order")
    public String getOrderList(Model model) {
        List<Order> orderList = orderService.getAllOrders();
        model.addAttribute("orders", orderList);
        return "viewOrder";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/order";
    }
}
