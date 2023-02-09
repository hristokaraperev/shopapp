package com.fmi.shopapp.controllers;

import com.fmi.shopapp.dao.OrderDao;
import com.fmi.shopapp.model.Location;
import com.fmi.shopapp.model.Order;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/orders")
    List<Order> all() {
        return orderDao.findAll();
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order newOrder) {
        return orderDao.save(newOrder);
    }

    @GetMapping("/orders/{id}")
    Order one(@PathVariable Long id) {
        return orderDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/orders/{id}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable Long id) {

        return orderDao.findById(id)
                .map(order -> {
                    order.setStatus(newOrder.getStatus());
                    order.setCustomer(newOrder.getCustomer());
                    order.setProducts(newOrder.getProducts());
                    return orderDao.save(order);
                })
                .orElseGet(() -> orderDao.save(newOrder));
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderDao.deleteById(id);
    }
}
