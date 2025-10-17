package com.store.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.app.Models.User;
import com.store.app.Services.UserService;

@RestController
@RequestMapping ("/api/users")
public class UserController {

    //getmapping "/all"
    //getUsers

    //postmapping "/add"
    //addUser

    //putmapping "/edit/{id}"
    //editUser

    
    //deletemapping "/delete/{id}"
    //deleteUser
        @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.GetUsers();
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.AddUser(user);
    }
    @PutMapping("/edit/{id}")
    public User editUser(@PathVariable Long id, @RequestBody User updateuser){
        return userService.EditUser(id, updateuser);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUSer(@PathVariable Long id){
        return userService.DeleteUser(id);
    }
}
