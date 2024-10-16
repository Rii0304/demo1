package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "search";
    }
//    @PostMapping("/searchPrice")
//    public String searchByPriceRange(@RequestParam("priceRange") String priceRange, Model model) {
//        List<Product> products;
//
//        if (priceRange.equals("60+")) {
//            products = productService.findByPriceGreaterThan(60.0);
//        } else {
//            String[] range = priceRange.split("-");
//            Double minPrice = Double.parseDouble(range[0]);
//            Double maxPrice = Double.parseDouble(range[1]);
//            products = productService.findByPriceBetween(minPrice, maxPrice);
//        }
//
//        model.addAttribute("products", products);
//        return "searchPrice";
//    }
    @PostMapping("/searchPrice")
    public String searchByPriceRange(@RequestParam("priceRange") String priceRange, Model model) {
        List<Product> products;

        if (priceRange.equals("60+")) {
            products = productService.findByPriceGreaterThan(60.0);
        } else {
            String[] range = priceRange.split("-");
            Double minPrice = Double.parseDouble(range[0]);
            Double maxPrice = Double.parseDouble(range[1]);
            products = productService.findByPriceBetween(minPrice, maxPrice);
        }

        model.addAttribute("products", products);

        return "searchPrice";
    }
}

