package com.example.ecommerce.service.Impl;

import com.example.ecommerce.bean.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScope
@Service
public class CartImpl implements CartService {
    Map<Integer, CartItem> maps = new HashMap<>();

    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getProductId());
        if(cartItem  == null){
            maps.put(item.getProductId(), item);
        }else {
            cartItem.setQty(cartItem.getQty()+1);
        }
    }

    @Override
    public void remove(Integer id){
        maps.remove(id);
    }

    @Override
    public CartItem update(int proId, int qty){
        CartItem cartItem = maps.get(proId);
        cartItem.setQty(qty);
        return cartItem;
    }
    @Override
    public void clear() {
        maps.clear();
    }
    @Override
    public Collection<CartItem> getAllItem(){
        return maps.values();
    }
    @Override
    public double getAmount(){
        return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
    }

}
