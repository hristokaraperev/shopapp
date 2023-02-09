package com.fmi.shopapp.dao;

import com.fmi.shopapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
