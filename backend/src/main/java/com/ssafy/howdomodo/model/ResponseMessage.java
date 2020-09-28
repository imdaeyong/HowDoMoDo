package com.ssafy.howdomodo.model;

public class ResponseMessage {
	public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "가입하지 않은 이메일이거나, 잘못된 비밀번호입니다.";
    public static final String LOGOUT_SUCCESS = "로그아웃 성공";
    public static final String LOGOUT_FAIL = "로그아웃 실패";
    
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    
    public static final String CREATE_CODE = "인증번호 생성";
    public static final String RESET_PWD = "비밀번호 재설정 성공";
    public static final String FAIL_RESET_PWD = "비밀번호 재설정 실패";
    public static final String SEARCH_NICKNAME_NONE = "닉네임 사용 가능";
    public static final String SEARCH_NICKNAME_EXIST = "이미 존재하는 닉네임입니다.";
    public static final String SEARCH_NICKNAME_SUCCESS = "닉네임으로 조회 성공";
    public static final String SEARCH_NICKNAME_FAIL = "닉네임으로 조회 실패";
    public static final String SEARCH_ALLUSERS_FAIL = "전체 회원 목록 조회 실패";
    public static final String SEARCH_ALLUSERS_SUCCESS = "전체 회원 목록 조회 성공";
    
    public static final String UNUSED_USER = "사용 가능한 Email입니다";
    public static final String ALREADY_USER = "이미 존재하는 Email입니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String FAIL_CREATE_USER = "회원 가입 실패";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String FAIL_UPDATE_USER = "회원 정보 수정 실패";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    
    public static final String AUTHORIZED = "인증 성공";
    public static final String UNAUTHORIZED = "인증 실패";
    public static final String FORBIDDEN = "인가 실패";
    public static final String TOKEN_REFRESH = "토큰 갱신 성공";
    
    public static final String SEARCH_SIDO_INFO_SUCCESS = "시도구군 정보 조회 성공";
    public static final String SEARCH_SIDO_INFO_FAIL = "시도구군 정보 조회 실패";
    
    public static final String SEARCH_THEATERS_INfO_NONE = "영화관 정보 없음";
    public static final String SEARCH_THEATERS_INFO_SUCCESS = "영화관 정보 조회 성공";
    
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String SERVICE_UNAVAILABLE = "현재 서비스를 사용하실 수 없습니다. 잠시후 다시 시도해 주세요.";

    public static final String DB_ERROR = "데이터베이스 에러";
}
