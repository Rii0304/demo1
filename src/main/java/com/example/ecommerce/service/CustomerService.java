package com.example.ecommerce.service;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import jakarta.mail.MessagingException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer login(String username, String password);

    boolean register(Customer customer) throws MessagingException;
    List<Customer> getAllUsers();

    void deleteUser(Long id);
    List<Customer> searchUser(String keyword);
    Customer updateUser(Customer updatedCustomer);
    Optional<Customer> getUserById(Long id);

    Customer findCustomerByUsername(String username);

    Customer findCustomerByEmail(String email);
}
