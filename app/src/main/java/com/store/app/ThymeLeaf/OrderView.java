package com.store.app.ThymeLeaf;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.app.Models.Order;
import com.store.app.Models.User;
import com.store.app.Services.OrderService;
import com.store.app.Services.UserService;

@Controller
@RequestMapping("/orders")
public class OrderView {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getOrders(Model model) {
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("users", userService.GetUsers());
        return "order-form";
    }

    @PostMapping("/add")
    public String addOrder(
            @ModelAttribute("order") Order newOrder,
            @RequestParam("user.id") Long userId,
            @RequestParam("productNamesString") String productNamesString) {

        List<String> productNames = List.of(productNamesString.split("\\s*,\\s*"));
        newOrder.setProductNames(productNames);

        User user = userService.GetUsers()
                .stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
        newOrder.setUser(user);

        orderService.addOrder(newOrder);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return "redirect:/orders";
        }

        String productNamesString = String.join(", ", order.getProductNames());

        model.addAttribute("order", order);
        model.addAttribute("productNamesString", productNamesString);
        model.addAttribute("users", userService.GetUsers());
        return "order-form"; 
    }

    @PostMapping("/edit/{id}")
    public String editOrder(
            @PathVariable Long id,
            @ModelAttribute("order") Order updatedOrder,
            @RequestParam("user.id") Long userId,
            @RequestParam("productNamesString") String productNamesString) {

        List<String> productNames = List.of(productNamesString.split("\\s*,\\s*"));
        updatedOrder.setProductNames(productNames);

        User user = userService.GetUsers()
                .stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
        updatedOrder.setUser(user);

        orderService.editOrder(id, updatedOrder);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
