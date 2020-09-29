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
public class FavoriteTheaters {
	
	private int favoriteTheatersId;
	private int userCode;
	private int theaterId;
	private String theaterName;
	private String theaterBrand;
}
