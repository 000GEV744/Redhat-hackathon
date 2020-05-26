package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VerificationModel;

import java.lang.String;
import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationModel, Long> {

	VerificationModel findByToken(String token);
}
