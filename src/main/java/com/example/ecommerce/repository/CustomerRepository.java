package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsernameAndPassword(String username, String password);

    Customer findByUsername(String username);
}
