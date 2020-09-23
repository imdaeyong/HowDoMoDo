package com.ssafy.howdomodo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Users {

	@Id
	private int userCode;
	
	@Column(length = 100, nullable = false, unique = true)
	private String userEmail;
	
	@Column(length = 100, nullable = false)
	private String userName;
	
	@Column(length = 100, nullable = false, unique = true)
	private String userNick;
	
	@Column(length = 100, nullable = false)
	private String userPw;
	
	@Column(length = 100, nullable = false)
	private int userGender;
	
	@Column(length = 100, nullable = false)
	private Date userBirth;
	
}
