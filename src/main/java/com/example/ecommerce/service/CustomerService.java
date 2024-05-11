package com.example.ecommerce.service;

import com.example.ecommerce.entity.Customer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer login(String username, String password);

    boolean register(Customer customer);
    List<Customer> getAllUsers();

    void deleteUser(Long id);

    Customer updateUser(Customer updatedCustomer);
    Optional<Customer> getUserById(Long id);

}
