package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/ManagementOrder")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String showAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "ManagementOrder";
    }
    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "editOrder";
    }
    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") Long id, @ModelAttribute Order order) {
        orderService.updateOrder(id, order);
        return "redirect:/ManagementOrder";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return "redirect:/ManagementOrder";
    }
}
