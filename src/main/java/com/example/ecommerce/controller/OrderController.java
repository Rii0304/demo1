package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ManagementOrder")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Hiển thị tất cả các đơn hàng
    @GetMapping("")
    public String showAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "managementOrder";
    }
    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "edit_order";
    }
    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") long id, @ModelAttribute("order") Order order) {
        order.setId(id);
        orderService.updateOrder(order);
        return "redirect:/ManagementOrder";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return "redirect:/ManagementOrder";
    }

}
