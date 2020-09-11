# ERD 설계

### Author: 권 오 정

> ### 테이블 목록 
>
> 1. 회원정보 
>    - email, 이름, 닉네임, pw, 성별
> 2. 지역별 극장
> 3. 예매 기록 
>    - 추천을 해줄 것이라면 추가적인 추천 요소가 필요
> 4. 추천 테이블
>    - 활동 전후 - 빅데이터 분석 결과
> 5. 맛집 테이블
> 6. 채팅목록 
>    - 사용자 아이디, 채팅방 정보
> 7. 채팅내용
>    - 가능하다면 로그, 안되면 테이블, 테이블에 파일 주소를 가지고 파일에 채팅 내용 저장

### My Role

#### 2. 지역별 극장

> - **theater_id** : 영화관 고유 ID (int)
> - **theater_name** : 영화관 이름 (varchar)
> - **theater_address** : 영화관 주소 (varchar)
> - **theater_brand** : 영화관 브랜드(cgv, 롯데, 메가) (int)
> - **theater_lat** : 위도 (float)
> - **theater_lon** : 경도 (float)



```sql
CREATE TABLE `cities` (
    `city_id` int NOT NULL AUTO_INCREMENT,
    `si_name` varchar(100),
    `gu_name` varchar(100),
    PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `theaters` (
    `theater_id` int NOT NULL AUTO_INCREMENT,
    `city_id` int NOT NULL,
    `theater_name` varchar(255) NOT NULL,
    `theater_address` varchar(255) NOT NULL,
    `theater_brand` varchar(50) NOT NULL,
    `theater_lat` float,
    `theater_lon` float,
	PRIMARY KEY (`theater_id`),
    CONSTRAINT `city_fk` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```