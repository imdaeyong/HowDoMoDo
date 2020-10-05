package com.ssafy.howdomodo.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.howdomodo.config.security.SecurityUtil;
import com.ssafy.howdomodo.domain.Users;
import com.ssafy.howdomodo.model.Response;
import com.ssafy.howdomodo.model.ResponseMessage;
import com.ssafy.howdomodo.model.RestException;
import com.ssafy.howdomodo.model.StatusCode;
import com.ssafy.howdomodo.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

//http://localhost:8080/backend/swagger-ui.html

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
	 
	@Autowired
	private final UserService userService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@ApiOperation(value = "회원가입")
	@PostMapping("/join")
	public ResponseEntity singUp(@RequestBody Users user) {
		
		// 이메일 닉네임 중복체크 한번 더 수행 후 회원가입 진행
		if(userService.findByUserEmail(user.getUserEmail()) != null)
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.ALREADY_USER), HttpStatus.OK);
		if(userService.findByUserNick(user.getUserNick()) != null)
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.SEARCH_NICKNAME_EXIST), HttpStatus.OK);
		
		UUID uuid = UUID.randomUUID();
		int userUuid = Math.abs(uuid.hashCode());
		user.setUserCode(userUuid);
		user.setUserPw(securityUtil.encryptSHA256(user.getUserPw()));
		int res = userService.join(user);
		if ( res == -1) {
			throw new RestException(ResponseMessage.FAIL_CREATE_USER, HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Response>(new Response(StatusCode.CREATED, ResponseMessage.CREATED_USER), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Email 중복체크")
	@GetMapping("/join/{email}")
	public ResponseEntity emailCheck(@PathVariable String email) {
		Users user = userService.findByUserEmail(email);
		if(user == null)
			return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.UNUSED_USER), HttpStatus.OK);
		else	
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.ALREADY_USER), HttpStatus.OK);
	}
	
	@ApiOperation(value = "닉네임 중복체크")
	@GetMapping("/check/{nickname}")
	public ResponseEntity nickCheck(@PathVariable String nickname) {
		String userNick = userService.findByUserNick(nickname);
		if(userNick == null)
			return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.SEARCH_NICKNAME_NONE), HttpStatus.OK);
		else	
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.SEARCH_NICKNAME_EXIST), HttpStatus.OK);
	}
	
	@ApiOperation(value="로그인")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Users user, HttpSession session) {
		Users member = userService.findByUserEmail(user.getUserEmail());
		if(member == null) // 사용자 정보가 없는 경우
			throw new RestException(ResponseMessage.LOGIN_FAIL, HttpStatus.NOT_FOUND);
		
		// 비밀번호가 일치하지 않는 경우
		if(!member.getUserPw().equals(securityUtil.encryptSHA256(user.getUserPw()))) {
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.LOGIN_FAIL), HttpStatus.FORBIDDEN);
		}
		
		session.setAttribute("user", member);
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, member),HttpStatus.OK);
	}
	
	@ApiOperation(value="비밀번호 재설정")
	@PutMapping("/pw")
	public ResponseEntity resetPw(@RequestBody Users user) {
		user.setUserPw(securityUtil.encryptSHA256(user.getUserPw()));
		int res = userService.updatePwd(user);
		if(res == -1)
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.FAIL_RESET_PWD), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.RESET_PWD), HttpStatus.OK);	
	}
	
	@ApiOperation(value="회원정보 수정")
	@PutMapping("")
	public ResponseEntity updateUsers(@RequestBody Users user) {
		int res = userService.updateUser(user);
		if(res == -1)
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.FAIL_UPDATE_USER), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.CREATED, ResponseMessage.UPDATE_USER), HttpStatus.CREATED);
	}
	
	@ApiOperation(value="회원정보 조회")
	@GetMapping("/{userCode}")
	public ResponseEntity searchUserByUserCode(@PathVariable int userCode) {
		Users user = userService.findByUserCode(userCode);
		if(user == null)
			throw new RestException(ResponseMessage.NOT_FOUND_USER, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.READ_USER, user), HttpStatus.OK);
	}
	
	@ApiOperation(value="로그아웃")
	@GetMapping("/logout")
	public ResponseEntity logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.LOGOUT_SUCCESS), HttpStatus.OK);
	}
	
	@ApiOperation(value="회원탈퇴")
	@DeleteMapping("/{userCode}")
	public ResponseEntity deleteUser(@PathVariable int userCode, HttpSession session) {
		int res = userService.deleteUser(userCode);
		session.invalidate();
		if(res == -1)
			return new ResponseEntity<Response>(new Response(StatusCode.FORBIDDEN, ResponseMessage.DELETE_FAIL), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.NO_CONTENT,ResponseMessage.DELETE_USER), HttpStatus.OK);
	}
}
