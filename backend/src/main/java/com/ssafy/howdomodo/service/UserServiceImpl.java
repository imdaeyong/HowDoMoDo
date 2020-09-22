package com.ssafy.howdomodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.howdomodo.domain.Users;
import com.ssafy.howdomodo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users join(Users user) {
		return userRepository.save(user);
	}

	@Override
	public Users findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public Users ckNick(String userNick) {
		return userRepository.findByUserNick(userNick);
	}

}
