package com.example.ecommerce.controller;

import com.example.ecommerce.bean.CartItem;
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

@Controller
@RequestMapping
public class Guest {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
//    @GetMapping
//    public String showHomeGuest() {
//        return "homeGuest";
//    }
    @GetMapping("/guest")
    public String showHomePage(Model model) {
        List<Product> newestProducts = productService.findTop4ByIdDesc();
        model.addAttribute("newestProducts", newestProducts);
        return "homeGuest";
    }
    @GetMapping("/guest/products")
    public String getAllProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 12;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Product> productPage = productService.getAllProducts(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "allProGuest";
    }
    @GetMapping("/guest/products/{id}")
    public String getProductByID(@PathVariable("id") Long id, Model model) {
        Product  products = productService.getProductByID(id);
        model.addAttribute("prod", products);
        return "guestDetailProd";
    }
    @GetMapping("/guest/view")
    public String showCart(Model model) {
        model.addAttribute("CART_ITEM", cartService.getAllItem());
        model.addAttribute("TOTAL", cartService.getAmount());
        return "cartGuest";
    }
    @GetMapping("/guest/add/{id}")
    public String addCartGuest(@PathVariable("id") Integer id) {
        Product pro = productService.getProductByID(Long.valueOf(id));
        if (pro != null) {
            CartItem item = new CartItem();
            item.setProductId(pro.getId());
            item.setName(pro.getProductName());
            item.setPrice(pro.getPrice());
            item.setQty(1);
            cartService.add(item);
        }
        return "redirect:/guest/products";
    }
    @GetMapping("/guest/remove/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        cartService.remove(id);
        return "redirect:/guest/view";
    }

    @GetMapping("/guest/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/guest/view";
    }
    @PostMapping("/guest/update")
    public String update(@RequestParam("qty")Integer qty,@RequestParam("id") String id) {
        cartService.update(Integer.parseInt(id), qty);
        return "redirect:/guest/view";
    }

    @GetMapping("/search-guest")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "searchGuest";
    }
//    @PostMapping("/guest/searchPrice")
//    public String searchByPrice(@RequestParam("Min") Double minPrice, @RequestParam("Max") Double maxPrice, Model model) {
//
//        List<Product> products = productService.findProductsByPriceRange(minPrice, maxPrice);
//        model.addAttribute("products", products);
//        return "searchGuest";
//    }

    @PostMapping("/guest/searchPrice")
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

        return "searchGuest";
    }
}
