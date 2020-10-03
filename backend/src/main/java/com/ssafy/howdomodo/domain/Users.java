package com.ssafy.howdomodo.domain;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Repository
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users{

	private int userCode;
	private String userEmail;
	private String userName;
	private String userNick;
	private String userPw;
	private int userGender;
	private String userBirth;
	
	
}
