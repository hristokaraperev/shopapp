package com.fmi.shopapp.dao;

import com.fmi.shopapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AddressDao extends JpaRepository<Address, Long> {
}
