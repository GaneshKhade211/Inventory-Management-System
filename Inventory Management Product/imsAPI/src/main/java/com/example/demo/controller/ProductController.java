package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

   
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product updated) {
        return productService.updateProduct(id, updated);
    }

    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

   
    @PostMapping("/{id}/increase")
    public Product increaseStock(@PathVariable Integer id, @RequestParam int amount) {
        return productService.increaseStock(id, amount);
    }

   
    @PostMapping("/{id}/decrease")
    public Product decreaseStock(@PathVariable Integer id, @RequestParam int amount) {
        return productService.decreaseStock(id, amount);
    }

   
    @GetMapping("/low-stock")
     public List<Product> getLowStockProducts() {
        return productService.getLowStockProducts();
        
    }
}
