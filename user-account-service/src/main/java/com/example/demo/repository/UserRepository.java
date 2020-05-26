package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import java.lang.String;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
   
    User findByUserId(String userid);
    User findByEmail(String email);
}
