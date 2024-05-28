package com.example.ecommerce.service.Impl;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer login(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }
    @Override
    public boolean register(Customer customer) {
        Customer existingCustomer = customerRepository.findByUsername(customer.getUsername());
        if (existingCustomer != null) {
            return false;
        } else {
            customerRepository.save(customer);
            return true;
        }
    }
    @Override
    public Optional<Customer> getUserById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer updateUser(Customer customer) {
        customer.setFullName(customer.getFullName());
        customer.setDateOfBirth(customer.getDateOfBirth());
        customer.setAddress(customer.getAddress());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setEmail(customer.getEmail());
        customer.setUsername(customer.getUsername());
        customer.setPassword(customer.getPassword());
        return customerRepository.save(customer);
    }
    @Override
    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }
    @Override
    public void deleteUser(Long id) {
        customerRepository.deleteById(id);
    }
}
