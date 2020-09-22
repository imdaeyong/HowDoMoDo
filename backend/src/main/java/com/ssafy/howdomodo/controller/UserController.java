package com.ssafy.howdomodo.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
//	private final PasswordEncoder passwordEncoder;
//	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@ApiOperation(value = "회원가입")
	@PostMapping("/signUp")
	public ResponseEntity singUp(@RequestBody Users user) {

		UUID uuid = UUID.randomUUID();
		int userUuid = Math.abs(uuid.hashCode());
		user.setUserCode(userUuid);
		user.setUserPw(securityUtil.encryptSHA256(user.getUserPw()));
		if (userService.join(user) == -1) {
			throw new RestException(ResponseMessage.FAIL_CREATE_USER, HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Response>(new Response(StatusCode.CREATED, ResponseMessage.CREATED_USER), HttpStatus.CREATED);
	}
	
//	@ApiOperation(value="로그인")
//	@PostMapping("/login")
//	public Object login(@RequestBody Users user, HttpSession session) {
//		final Response result = new Response();
//		Users member = userService.findByUserEmail(user.getUserEmail());
//		if(member.getUserPw().equals(user.getUserPw())) {
//			session.setAttribute("user", user);
//			result.status = true;
//			result.object = session.getAttribute("user");
//		} else {
//			result.status = false;
//			result.object = FAIL;
//		}
//		
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="로그아웃")
//	@GetMapping("/logout")
//	public Object logout(HttpSession session) {
//		final Response result = new Response();
//		session.invalidate();
//		result.status = true;
//		result.object = SUCCESS;
//		
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
}
