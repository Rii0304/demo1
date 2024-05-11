package com.example.ecommerce.controller;

import com.example.ecommerce.bean.OrderRequest;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("order")
public class CheckoutController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @GetMapping("view/checkout")
    public String showCart(Model model) {
        model.addAttribute("CART_ITEM", cartService.getAllItem());
        model.addAttribute("TOTAL", cartService.getAmount());
        model.addAttribute("orderRequest", new OrderRequest());
        return "checkout";
    }

    @PostMapping("checkout")
    public String checkout(@ModelAttribute("orderRequest") OrderRequest orderRequest) {
        Order order = new Order();

        order.setCustomerId(order.getCustomerId());
        order.setTotalPrice(BigDecimal.valueOf(cartService.getAmount()));
        order.setFullName(orderRequest.getFullName());
        order.setPhone(orderRequest.getPhone());
        order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        order.setOrderStatus("Processing");
        order.setOrderDate(new Date());
        orderService.saveOrder(order);

        cartService.clear();

        return "redirect:/home";
    }

}
