package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(Customer customer, RedirectAttributes redirectAttributes, Model model) {
        customer.setRole("USER");
        try {
            if (customerService.register(customer)) {
                redirectAttributes.addFlashAttribute("successMessage", "Sign Up Success! Check your email to activate the account.");
                return "redirect:/login";
            } else {
                model.addAttribute("error", "Username already exists!");
                return "register";
            }
        } catch (MessagingException e) {
            model.addAttribute("error", "Failed to send confirmation email.");
            return "register";
        }
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam("email") String email) {
        Customer customer = customerService.findCustomerByEmail(email);
        if (customer != null) {
            customer.setEnabled(true);
            customerService.updateUser(customer);
            return "redirect:/login?activated";
        }
        return "redirect:/login?error";
    }
}
