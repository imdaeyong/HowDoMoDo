package com.ssafy.howdomodo.service;

import java.util.List;

import com.ssafy.howdomodo.domain.Cities;
import com.ssafy.howdomodo.domain.FavoriteTheaters;
import com.ssafy.howdomodo.domain.Theaters;

public interface TheaterService {
	
	public List<Cities> readSidoinfo();

	public int getCityId(String siName, String guName);

	public List<Theaters> getTheatersInfo(int cityId);

	public int addFavTheaters(FavoriteTheaters favoriteTheaters);

	public List<FavoriteTheaters> getFavList(int userCode);

	public int deleteFavTheaters(int userCode, int theaterId);
}
