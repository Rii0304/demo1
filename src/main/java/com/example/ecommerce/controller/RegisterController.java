package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class RegisterController {

    @Autowired
    public CustomerService customerService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(Customer customer, RedirectAttributes redirectAttributes, Model model) {
        customer.setRole("USER");
        if (customerService.register(customer)) {
            redirectAttributes.addFlashAttribute("successMessage", "Sign Up Success!");
            return "redirect:/login";
        } else {
            model.addAttribute("error");
            return "register";
        }
    }
}
