package com.example.ecommerce.controller;

import com.example.ecommerce.bean.OrderRequest;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    CustomerService customerService;

    @GetMapping("view/checkout")
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("CART_ITEM", cartService.getAllItem());
        model.addAttribute("TOTAL", cartService.getAmount());
        model.addAttribute("orderRequest", new OrderRequest());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String loggedInUsername = userDetails.getUsername();

        Customer loggedInCustomer = customerService.findCustomerByUsername(loggedInUsername);
        if (loggedInCustomer != null) {
            model.addAttribute("customerId", loggedInCustomer.getId());
        }

        return "checkout";
    }


    @PostMapping("checkout")
    public String checkout(@ModelAttribute("orderRequest") OrderRequest orderRequest, HttpSession session) {
        Order order = new Order();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String loggedInUsername = userDetails.getUsername();

        Customer loggedInCustomer = customerService.findCustomerByUsername(loggedInUsername);
        if (loggedInCustomer != null) {
            order.setCustomerId(loggedInCustomer.getId());
        }

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

