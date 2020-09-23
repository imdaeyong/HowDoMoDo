package com.ssafy.howdomodo.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.howdomodo.domain.Users;
import com.ssafy.howdomodo.mapper.UsersMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper userMapper;
	
	@Override
	public int join(Users user) {
		try {
			int res = userMapper.join(user);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public String findByUserEmail(String userEmail) {
		String email = null;
		try {
			email = userMapper.findByUserEmail(userEmail);
			return email;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String findByUserNick(String userNick) {
		String nick = null;
		try {
			nick = userMapper.findByUserNick(userNick);
			return nick;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
