package com.store.app.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.app.Models.Order;
import com.store.app.Models.User;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
