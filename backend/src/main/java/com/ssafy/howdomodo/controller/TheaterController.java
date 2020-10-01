package com.ssafy.howdomodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.howdomodo.domain.Cities;
import com.ssafy.howdomodo.domain.FavoriteTheaters;
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
	
	@ApiOperation(value="영화관 즐겨찾기 등록")
	@PostMapping("/bookmark")
	public ResponseEntity addFavTheaters(@RequestBody FavoriteTheaters favoriteTheaters) {
		int res = theaterService.addFavTheaters(favoriteTheaters);
		if(res == -1)
			throw new RestException(ResponseMessage.ADD_FAIL, HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.CREATED, ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
	}
	
	@ApiOperation(value="회원의 즐겨찾기 조회")
	@GetMapping("/bookmark/{userCode}")
	public ResponseEntity getFavList(@PathVariable int userCode) {
		List<FavoriteTheaters> favList = theaterService.getFavList(userCode);
		if(favList == null)
			throw new RestException(ResponseMessage.SEARCH_FAIL, HttpStatus.FORBIDDEN);
		else {
			if(favList.size() == 0)
				return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.SEARCH_FAV_THEATERS_NONE), HttpStatus.OK);
			else
				return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.SEARCH_FAV_THEATERS_SUCCESS, favList), HttpStatus.OK);
		}
	}
	
	@ApiOperation(value="즐겨찾기 영화관 삭제")
	@DeleteMapping("/bookmark/{userCode}/{theaterId}")
	public ResponseEntity deleteFavTheaters(@PathVariable int userCode, @PathVariable int theaterId) {
		int res = theaterService.deleteFavTheaters(userCode, theaterId);
		if(res == -1)
			throw new RestException(ResponseMessage.DELETE_FAIL, HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Response>(new Response(StatusCode.OK, ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);
	}
}
