//package com.example.ecommerce.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "userrole")
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
//    private Role role;
//
//    public UserRole() {}
//
//    public UserRole(Customer customer, Role role) {
//        this.customer = customer;
//        this.role = role;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//}
