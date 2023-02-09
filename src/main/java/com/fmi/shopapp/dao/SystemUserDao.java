package com.fmi.shopapp.dao;

import com.fmi.shopapp.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SystemUserDao extends JpaRepository<SystemUser, Long> {
}
