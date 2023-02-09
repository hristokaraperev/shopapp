package com.fmi.shopapp.controllers;

import com.fmi.shopapp.dao.AddressDao;
import com.fmi.shopapp.model.Address;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressDao addressDao;

    @GetMapping("/addresses")
    List<Address> all() {
        return addressDao.findAll();
    }

    @PostMapping("/addresses")
    Address newAddress(@RequestBody Address newAddress) {
        return addressDao.save(newAddress);
    }

    @GetMapping("/addresses/{id}")
    Address one(@PathVariable Long id) {
        return addressDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/addresses/{id}")
    Address replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) {

        return addressDao.findById(id)
                .map(address -> {
                    address.setStatus(newAddress.getStatus());
                    address.setCountry(newAddress.getCountry());
                    address.setCity(newAddress.getCity());
                    address.setStreet(newAddress.getStreet());
                    address.setNumber(newAddress.getNumber());
                    address.setPostCode(newAddress.getPostCode());
                    return addressDao.save(address);
                })
                .orElseGet(() -> {
                    return addressDao.save(newAddress);
                });
    }

    @DeleteMapping("/addresses/{id}")
    void deleteAddress(@PathVariable Long id) {
        addressDao.deleteById(id);
    }
}
