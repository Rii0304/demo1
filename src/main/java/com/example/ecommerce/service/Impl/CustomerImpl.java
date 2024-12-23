package com.example.ecommerce.service.Impl;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Customer login(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }
    @Override
    public boolean register(Customer customer) throws MessagingException {
        Customer existingCustomer = customerRepository.findByUsername(customer.getUsername());

        if (existingCustomer != null && existingCustomer.isEnabled()) {
            return false;
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);

        String subject = "Confirm account registration";
        String content = "<p>Hello " + customer.getFullName() + ",</p>"
                + "<p>Please click the link below to activate your account:</p>"
                + "<a href='http://localhost:8080/activate?email=" + customer.getEmail() + "'>Activate account</a>";
        emailService.sendEmail(customer.getEmail(), subject, content);

        return true;
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
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
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

    @Override
    public List<Customer> searchUser(String keyword) {
        return customerRepository.searchUser(keyword);
    }
}
