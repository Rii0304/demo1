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

    @GetMapping("/products/guest")
    public String getAllProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 12;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Product> productPage = productService.getAllProducts(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "allProGuest";
    }
    @GetMapping("view/guest")
    public String showCart(Model model) {
        model.addAttribute("CART_ITEM", cartService.getAllItem());
        model.addAttribute("TOTAL", cartService.getAmount());
        return "cartGuest";
    }
    @GetMapping("add/guest/{id}")
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
        return "redirect:/products/guest";
    }
    @GetMapping("remove/guest/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        cartService.remove(id);
        return "redirect:/view/guest";
    }

    @GetMapping("clear/guest")
    public String clearCart() {
        cartService.clear();
        return "redirect:/view/guest";
    }
    @PostMapping("update/guest")
    public String update(@RequestParam("qty")Integer qty,@RequestParam("id") String id) {
        cartService.update(Integer.parseInt(id), qty);
        return "redirect:/view/guest";
    }
}
