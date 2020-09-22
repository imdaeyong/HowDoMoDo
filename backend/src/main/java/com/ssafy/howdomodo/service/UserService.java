package com.ssafy.howdomodo.service;

import com.ssafy.howdomodo.domain.Users;

public interface UserService {
	
	public int join(Users user);
	public String findByUserEmail(String userEmail);
}