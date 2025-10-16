package com.store.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.app.Models.User;
import com.store.app.Repos.UserRepo;

//Service 

@Service
public class UserService {

    //Autowired
    //create userrepo boject

    //GetUsers
    //AddUser
    //EditUser
    //DeleteUser

        @Autowired
    private UserRepo userRepo;

    public List<User> GetUsers (){
        return userRepo.findAll();
    } 

    public User AddUser(User newuser){
        return userRepo.save(newuser);
    }

    public User EditUser (Long id, User updateuser){
        User olduser = userRepo.findById(id).orElse(null);
        if(olduser != null){
            olduser.setName(updateuser.getName());
            olduser.setEmail(updateuser.getEmail());
            olduser.setPhone(updateuser.getPhone());
            olduser.setPassword(updateuser.getPassword());
            return userRepo.saveAndFlush(olduser);
        }
      
        return null;
    }
    public String DeleteUser(Long id){
        User founduser = userRepo.findById(id).orElse(null);
        if(founduser !=null){
            userRepo.deleteById(id);
            return founduser.getName() +" Delted !";
        }
        else{
            return "User not found !";
        }
    }
}
