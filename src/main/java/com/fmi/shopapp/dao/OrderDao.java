package com.fmi.shopapp.dao;

import com.fmi.shopapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
