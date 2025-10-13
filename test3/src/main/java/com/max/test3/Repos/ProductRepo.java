package com.max.test3.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.max.test3.Models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
