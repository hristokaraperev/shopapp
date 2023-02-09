package com.fmi.shopapp.dao;

import com.fmi.shopapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductDao extends JpaRepository<Product, Long> {
}
