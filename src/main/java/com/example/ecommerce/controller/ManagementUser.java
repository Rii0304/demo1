package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("ManagementUser")
public class ManagementUser {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public String showUserList(Model model) {
        List<Customer> users = customerService.getAllUsers();
        model.addAttribute("users", users);
        return "managementUser";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Customer> customerOptional = customerService.getUserById(id);
        if (customerOptional.isPresent()) {
            model.addAttribute("customer", customerOptional.get());
            return "editCustomer";
        } else {
            return "error";
        }
    }
    @PostMapping("/update/{id}")
    public String editCustomer(@PathVariable Long id, @ModelAttribute Customer updatedCustomer) {
        updatedCustomer.setId(id);
        customerService.updateUser(updatedCustomer);
        return "redirect:/ManagementUser";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        customerService.deleteUser(id);
        return "redirect:/ManagementUser";
    }
}
