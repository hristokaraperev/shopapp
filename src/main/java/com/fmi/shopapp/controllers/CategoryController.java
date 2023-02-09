package com.fmi.shopapp.controllers;

import com.fmi.shopapp.dao.CategoryDao;
import com.fmi.shopapp.model.Address;
import com.fmi.shopapp.model.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/categories")
    List<Category> all() {
        return categoryDao.findAll();
    }

    @PostMapping("/categories")
    Category newCategory(@RequestBody Category newCategory) {
        return categoryDao.save(newCategory);
    }

    @GetMapping("/categories/{id}")
    Category one(@PathVariable Long id) {
        return categoryDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/categories/{id}")
    Category replaceCategory(@RequestBody Category newCategory, @PathVariable Long id) {

        return categoryDao.findById(id)
                .map(category -> {
                    category.setStatus(newCategory.getStatus());
                    category.setName(newCategory.getName());
                    return categoryDao.save(category);
                })
                .orElseGet(() -> {
                    return categoryDao.save(newCategory);
                });
    }

    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoryDao.deleteById(id);
    }
}
