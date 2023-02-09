package com.fmi.shopapp.controllers;

import com.fmi.shopapp.dao.SystemUserDao;
import com.fmi.shopapp.model.Product;
import com.fmi.shopapp.model.SystemUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SystemUserController {

    @Autowired
    private SystemUserDao systemUserDao;

    @GetMapping("/users")
    List<SystemUser> all() {
        return systemUserDao.findAll();
    }

    @PostMapping("/users")
    SystemUser newUser(@RequestBody SystemUser newUser) {
        return systemUserDao.save(newUser);
    }

    @GetMapping("/users/{id}")
    SystemUser one(@PathVariable Long id) {
        return systemUserDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/users/{id}")
    SystemUser replaceUser(@RequestBody SystemUser newUser, @PathVariable Long id) {

        return systemUserDao.findById(id)
                .map(user -> {
                    user.setStatus(newUser.getStatus());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setIsEmployee(newUser.getIsEmployee());
//                    user.setOrders(newUser.getOrders());
                    return systemUserDao.save(user);
                })
                .orElseGet(() -> systemUserDao.save(newUser));
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        systemUserDao.deleteById(id);
    }
}
