package com.ssafy.howdomodo.service;

import java.util.List;

import com.ssafy.howdomodo.domain.FavoriteTheaters;
import com.ssafy.howdomodo.domain.Theaters;

public interface TheaterService {
	
	public int getCityId(String siName, String guName);

	public List<Theaters> getTheatersInfo(int cityId);

	public int addFavTheaters(FavoriteTheaters favoriteTheaters);

	public List<FavoriteTheaters> getFavList(int userCode);

	public int deleteFavTheaters(int userCode, int theaterId);

	public List<String> readSiInfo();

	public List<String> readGuInfo(String siName);

	public FavoriteTheaters checkFavTheaters(int userCode, int theaterId);
}
