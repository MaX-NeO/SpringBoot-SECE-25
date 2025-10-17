package com.store.app.ThymeLeaf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.app.Services.OrderService;
import com.store.app.Services.ProductService;
import com.store.app.Services.UserService;


@Controller
@RequestMapping("/")
public class HomeView {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("usersCount", userService.GetUsers().size());
        model.addAttribute("productsCount", productService.GetProducts().size());
        model.addAttribute("ordersCount", orderService.getOrders().size());
        return "index";
    }
}
