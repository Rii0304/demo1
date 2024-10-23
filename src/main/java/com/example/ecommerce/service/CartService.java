package com.example.ecommerce.service;

import com.example.ecommerce.bean.CartItem;
import com.example.ecommerce.entity.Product;

import java.util.Collection;
import java.util.List;

public interface CartService {

    void add(CartItem item);

    void remove(Integer id);

    CartItem update(int id, int qty);

    void clear();

    Collection<CartItem> getAllItem();

    double getAmount();

}
