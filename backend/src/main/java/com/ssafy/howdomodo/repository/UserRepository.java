package com.ssafy.howdomodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.howdomodo.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Users findByUserEmail(String userEmail);
	
}
