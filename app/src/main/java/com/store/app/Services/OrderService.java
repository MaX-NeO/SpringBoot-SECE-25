package com.store.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.app.Models.Order;
import com.store.app.Models.User;
import com.store.app.Repos.OrderRepo;
import com.store.app.Repos.UserRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public Order addOrder(Order newOrder) {
        if (newOrder.getUser() != null && newOrder.getUser().getId() != null) {
            User user = userRepo.findById(newOrder.getUser().getId()).orElse(null);
            if (user != null) {
                newOrder.setUser(user);
            }
        }
        return orderRepo.save(newOrder);
    }

    public Order editOrder(Long id, Order updateOrder) {
        Order oldOrder = orderRepo.findById(id).orElse(null);
        if (oldOrder != null) {
            oldOrder.setStatus(updateOrder.getStatus());
            oldOrder.setTotal(updateOrder.getTotal());
            oldOrder.setProductNames(updateOrder.getProductNames());
            oldOrder.setOrderDate(updateOrder.getOrderDate());
            return orderRepo.saveAndFlush(oldOrder);
        }
        return null;
    }

    public String deleteOrder(Long id) {
        Order foundOrder = orderRepo.findById(id).orElse(null);
        if (foundOrder != null) {
            orderRepo.deleteById(id);
            return "Order ID " + id + " deleted successfully!";
        } else {
            return "Order not found!";
        }
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return orderRepo.findByUser(user);
        }
        return List.of();
    }
}
