package com.fmi.shopapp.controllers;

import com.fmi.shopapp.dao.ProductDao;
import com.fmi.shopapp.model.Order;
import com.fmi.shopapp.model.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/products")
    List<Product> all() {
        return productDao.findAll();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return productDao.save(newProduct);
    }

    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id) {
        return productDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return productDao.findById(id)
                .map(product -> {
                    product.setStatus(newProduct.getStatus());
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setCategory(newProduct.getCategory());
                    return productDao.save(product);
                })
                .orElseGet(() -> productDao.save(newProduct));
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        productDao.deleteById(id);
    }
}
