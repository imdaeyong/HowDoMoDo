package com.ssafy.howdomodo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.howdomodo.domain.FavoriteTheaters;
import com.ssafy.howdomodo.domain.Theaters;
import com.ssafy.howdomodo.mapper.TheatersMapper;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheatersMapper theaterMapper;

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

	@Override
	public int addFavTheaters(FavoriteTheaters favoriteTheaters) {
		try {
			int res = theaterMapper.addFavTheaters(favoriteTheaters);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<FavoriteTheaters> getFavList(int userCode) {
		try {
			List<FavoriteTheaters> list = theaterMapper.getFavList(userCode);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteFavTheaters(int userCode, int theaterId) {
		try {
			int res = theaterMapper.deleteFavTheaters(userCode, theaterId);
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<String> readSiInfo() {
		try {
			List<String> siList = theaterMapper.readSiInfo();
			return siList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> readGuInfo(String siName) {
		try {
			List<String> list = theaterMapper.readGuInfo(siName);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
