package com.store.app.ThymeLeaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.app.Models.User;
import com.store.app.Services.UserService;

@Controller
@RequestMapping("/users")
public class UserView {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.GetUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User newUser) {
        userService.AddUser(newUser);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User existingUser = userService.GetUsers()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("user", existingUser);
        return "user-form";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute("user") User updatedUser) {
        userService.EditUser(id, updatedUser);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.DeleteUser(id);
        return "redirect:/users";
    }
}
