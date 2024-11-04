package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsernameAndPassword(String username, String password);
    @Query("SELECT c FROM Customer c WHERE c.username = :username")
    Customer findByUsername(String username);
    @Query("SELECT p FROM Customer p WHERE p.fullName LIKE %?1% OR p.email LIKE %?1%")
    List<Customer> searchUser(String keyword);
    Customer findCustomerByEmail(String email);
}
