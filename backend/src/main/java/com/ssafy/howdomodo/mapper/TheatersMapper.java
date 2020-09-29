package com.ssafy.howdomodo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.ssafy.howdomodo.domain.Cities;
import com.ssafy.howdomodo.domain.FavoriteTheaters;
import com.ssafy.howdomodo.domain.Theaters;

@Mapper
public interface TheatersMapper {

	List<Cities> getAllSido() throws SQLException;

	int getCityId(@Param("siName") String siName,@Param("guName") String guName) throws SQLException;

	List<Theaters> getTheaterInfo(int cityId) throws SQLException;

	int addFavTheaters(FavoriteTheaters favoriteTheaters) throws SQLException;

	List<FavoriteTheaters> getFavList(int userCode) throws SQLException;

	int deleteFavTheaters(@Param("userCode") int userCode, @Param("theaterId") int theaterId) throws SQLException;

}
