package com.example.demo.repository;


import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Bonus: Find products below low_stock_threshold
    List<Product> findByStockQuantityLessThanEqual(Integer threshold);
}
