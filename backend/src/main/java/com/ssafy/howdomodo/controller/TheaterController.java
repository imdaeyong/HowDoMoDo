package com.ssafy.howdomodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.howdomodo.domain.Cities;
import com.ssafy.howdomodo.domain.Theaters;
import com.ssafy.howdomodo.model.Response;
import com.ssafy.howdomodo.model.ResponseMessage;
import com.ssafy.howdomodo.model.RestException;
import com.ssafy.howdomodo.model.StatusCode;
import com.ssafy.howdomodo.service.TheaterService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/theaters")
public class TheaterController {
	
	@Autowired
	private final TheaterService theaterService;
	
	@ApiOperation(value="시도 정보")
	@GetMapping()
	public ResponseEntity readSidoinfo() {
		List<Cities> cities = theaterService.readSidoinfo();
		if(cities == null)
			throw new RestException(ResponseMessage.SEARCH_SIDO_INFO_FAIL, HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.SEARCH_SIDO_INFO_SUCCESS, cities), HttpStatus.OK);
	}
	
	@ApiOperation(value="시도 정보에 따른 영화관을 반환합니다.")
	@GetMapping("/{siName}/{guName}")
	public ResponseEntity theatersInfo(@PathVariable("siName") String siName, @PathVariable("guName") String guName) {
		System.out.println(siName+ " / " + guName);
		int cityId = theaterService.getCityId(siName, guName);
		List<Theaters> theaters = theaterService.getTheatersInfo(cityId);
		if(theaters == null)
			throw new RestException(ResponseMessage.SEARCH_THEATERS_INfO_NONE, HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.SEARCH_THEATERS_INFO_SUCCESS, theaters), HttpStatus.OK);
	}
}
