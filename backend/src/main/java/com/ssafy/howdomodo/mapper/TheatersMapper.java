package com.ssafy.howdomodo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.ssafy.howdomodo.domain.Cities;

@Mapper
public interface TheatersMapper {

	List<Cities> getAllSido() throws SQLException;

}
