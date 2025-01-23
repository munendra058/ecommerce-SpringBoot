package com.spring.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.ecommerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.productDesc) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);

}
