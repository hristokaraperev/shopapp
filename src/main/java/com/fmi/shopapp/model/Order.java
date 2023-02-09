package com.fmi.shopapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@EqualsAndHashCode(exclude = {"customer","products"})
@ToString(exclude = {"customer","products"})
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp created;
    @UpdateTimestamp
    private Timestamp updated;
    @Column(nullable = false)
    private Boolean status;
    @ManyToOne
    private SystemUser customer;
    @ManyToMany
    private List<Product> products;
}
