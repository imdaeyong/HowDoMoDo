package com.ssafy.howdomodo.service;

import org.springframework.stereotype.Service;

import com.ssafy.howdomodo.domain.Users;

@Service("UserService")
public interface UserService {
	
	public Users join(Users user);
	public Users findByUserEmail(String userEmail);
}