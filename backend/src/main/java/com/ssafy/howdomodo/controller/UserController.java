package com.ssafy.howdomodo.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.howdomodo.domain.Users;
import com.ssafy.howdomodo.model.Response;
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
	
	private final UserService userService;
	
	private static int uid = 2;
	@ApiOperation(value="회원가입")
	@PostMapping("/singUp")
	public Object singUp(@RequestBody Users user) {
		final Response result = new Response();
		user.setUserCode(uid++); // 나중에 수정 필요
		userService.join(user);
		result.status = true;
		result.data = SUCCESS;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value="로그인")
	@PostMapping("/login")
	public Object login(@RequestBody Users user, HttpSession session) {
		final Response result = new Response();
		Users member = userService.findByUserEmail(user.getUserEmail());
		if(member.getUserPw().equals(user.getUserPw())) {
			session.setAttribute("user", user);
			result.status = true;
			result.object = session.getAttribute("user");
		} else {
			result.status = false;
			result.object = FAIL;
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value="로그아웃")
	@GetMapping("/logout")
	public Object logout(HttpSession session) {
		final Response result = new Response();
		session.invalidate();
		result.status = true;
		result.object = SUCCESS;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
