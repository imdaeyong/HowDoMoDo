package com.ssafy.howdomodo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.howdomodo.domain.Cities;
import com.ssafy.howdomodo.mapper.TheatersMapper;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheatersMapper theaterMapper;
	
	@Override
	public List<Cities> readSidoinfo() {
		List<Cities> cities;
		try {
			cities = theaterMapper.getAllSido();
			return cities;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
