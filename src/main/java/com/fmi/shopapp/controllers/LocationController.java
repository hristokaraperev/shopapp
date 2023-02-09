package com.fmi.shopapp.controllers;

import com.fmi.shopapp.dao.LocationDao;
import com.fmi.shopapp.model.Category;
import com.fmi.shopapp.model.Location;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationDao locationDao;

    @GetMapping("/locations")
    List<Location> all() {
        return locationDao.findAll();
    }

    @PostMapping("/locations")
    Location newLocation(@RequestBody Location newLocation) {
        return locationDao.save(newLocation);
    }

    @GetMapping("/locations/{id}")
    Location one(@PathVariable Long id) {
        return locationDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/locations/{id}")
    Location replaceLocation(@RequestBody Location newLocation, @PathVariable Long id) {

        return locationDao.findById(id)
                .map(location -> {
                    location.setStatus(newLocation.getStatus());
                    location.setAddress(newLocation.getAddress());
                    return locationDao.save(location);
                })
                .orElseGet(() -> locationDao.save(newLocation));
    }

    @DeleteMapping("/locations/{id}")
    void deleteLocation(@PathVariable Long id) {
        locationDao.deleteById(id);
    }

}
