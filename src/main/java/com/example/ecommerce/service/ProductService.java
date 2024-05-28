package com.example.ecommerce.service;

import com.example.ecommerce.domain.UpSertProduct;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;


public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Product getProductByID(Long id);
    void deleteProduct(Long id);
    List<Product> findTop4ByIdDesc();
    List<Product> searchProducts(String keyword);
    Product updateProd(Product updatedProd) throws IOException;
    void createProd(UpSertProduct product) throws IOException;
}

