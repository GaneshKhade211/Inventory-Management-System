package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

   
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    
    public Product updateProduct(Integer id, Product updated) {
        Product product = getProductById(id);
        product.setPname(updated.getPname());
        product.setPdescription(updated.getPdescription());
        product.setStockQuantity(updated.getStockQuantity());
        product.setLowStockThreshold(updated.getLowStockThreshold());
        return productRepository.save(product);
    }

  
    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    
    public Product increaseStock(Integer id, int amount) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than zero");
        }
        Product product = getProductById(id);
        product.setStockQuantity(product.getStockQuantity() + amount);
        return productRepository.save(product);
    }

    
    public Product decreaseStock(Integer id, int amount) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than zero");
        }

        Product product = getProductById(id);
        if (product.getStockQuantity() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock to remove");
        }

        product.setStockQuantity(product.getStockQuantity() - amount);
        return productRepository.save(product);
    }

    
    public List<Product> getLowStockProducts() {
        return productRepository.findByStockQuantityLessThanEqual(5); 
    }
}
