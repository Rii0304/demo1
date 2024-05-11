package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Object> findById(Long id);

    void deleteById(Long id);
    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> findTop4ByIdDesc(Pageable pageable);
}

