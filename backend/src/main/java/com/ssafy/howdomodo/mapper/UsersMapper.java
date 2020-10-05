package com.ssafy.howdomodo.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.howdomodo.domain.Users;

@Mapper
public interface UsersMapper {
	
	public int join(Users user) throws SQLException;
	public Users findByUserEmail(String userEmail) throws SQLException;
	public String findByUserNick(String userNick) throws SQLException;
	public int updateUser(Users user) throws SQLException;
	public int updatePwd(Users user) throws SQLException;
	public Users findByUserCode(int userCode) throws SQLException;
	public int deleteByUserCode(int userCode) throws SQLException;
	public Users findByUserEmailAndName(@Param("userEmail") String userEmail,@Param("userName") String userName) throws SQLException;
}
