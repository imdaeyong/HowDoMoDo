package com.ssafy.howdomodo.domain;

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
public class Theaters {
	
	private int theaterId;
	private int cityId;
	private String theaterName;
	private String theaterBrand;
	private String theaterAddress;
	private double theaterLat;
	private double theaterLon;
	private boolean isFav;
	
}
