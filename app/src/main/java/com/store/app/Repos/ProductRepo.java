package com.store.app.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.app.Models.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
