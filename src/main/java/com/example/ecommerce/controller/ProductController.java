package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ProductController {

    @Autowired
    private  ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/products")
    public String getAllProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 12;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Product> productPage = productService.getAllProducts(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "allProduct";
    }

    @GetMapping("/products/{id}")
    public String getProductByID(@PathVariable("id") Long id, Model model) {
        Product  products = productService.getProductByID(id);
        model.addAttribute("prod", products);
        return "productDetail";
    }
}

