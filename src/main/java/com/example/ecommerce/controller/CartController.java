package com.example.ecommerce.controller;

import com.example.ecommerce.bean.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @GetMapping("view")
    public String showCart(Model model) {
        model.addAttribute("CART_ITEM", cartService.getAllItem());
        model.addAttribute("TOTAL", cartService.getAmount());
        return "cart";
    }
    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") Integer id){
        Product product = productService.getProductByID(Long.valueOf(id));
        if (product != null){
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getProductName());
            item.setPrice(product.getPrice());
            item.setQty(1);
            cartService.add(item);
        }
        return "redirect:/products";
    }
    @GetMapping("remove/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        cartService.remove(id);
        return "redirect:/cart/view";
    }

    @GetMapping("clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart/view";
    }
    @PostMapping("update")
    public String update(@RequestParam("qty")Integer qty,@RequestParam("id") String id) {
        cartService.update(Integer.parseInt(id), qty);
        return "redirect:/cart/view";
    }
}
