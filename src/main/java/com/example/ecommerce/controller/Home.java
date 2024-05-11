package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class Home {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showHome() {
        return "home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Product> newestProducts = productService.findTop4ByIdDesc();
        model.addAttribute("newestProducts", newestProducts);
        return "home";
    }
}
