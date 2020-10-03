package com.ssafy.howdomodo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.howdomodo.domain.FavoriteTheaters;
import com.ssafy.howdomodo.domain.Theaters;

@Mapper
public interface TheatersMapper {

	int getCityId(@Param("siName") String siName,@Param("guName") String guName) throws SQLException;

	List<Theaters> getTheaterInfo(int cityId) throws SQLException;

	int addFavTheaters(FavoriteTheaters favoriteTheaters) throws SQLException;

	List<FavoriteTheaters> getFavList(int userCode) throws SQLException;

	int deleteFavTheaters(@Param("userCode") int userCode, @Param("theaterId") int theaterId) throws SQLException;

	List<String> readSiInfo() throws SQLException;

	List<String> readGuInfo(String siName) throws SQLException;

}
