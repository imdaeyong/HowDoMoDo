package com.ssafy.howdomodo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.howdomodo.domain.Cities;
import com.ssafy.howdomodo.domain.Theaters;
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

	@Override
	public int getCityId(String siName, String guName) {
		try {
			int res = theaterMapper.getCityId(siName, guName);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Theaters> getTheatersInfo(int cityId) {
		try {
			List<Theaters> theaters = theaterMapper.getTheaterInfo(cityId);
			return theaters;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
