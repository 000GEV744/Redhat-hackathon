package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserAccount;
import java.lang.String;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>{

    UserAccount findByAccountNumber(String accountNumber);
       
    @Transactional
    Long deleteByAccountNumber(String accountNumber);
}
