package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("edit")
public class EditOrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping
    public String showEdit() {
        return "editOrder";
    }
    @GetMapping("/editOrder/{orderId}")
    public String showEditOrderForm(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "editOrder";
    }

    @PostMapping("/editOrder")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId, @RequestParam("newStatus") String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/order";
    }
}
