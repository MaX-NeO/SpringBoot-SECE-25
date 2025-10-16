package com.store.app.Repos;

//extends to JPARepo <User, Long>

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.app.Models.User;

public interface UserRepo extends  JpaRepository<User, Long>{

}
