# ERD 설계

### Author: 김 찬 영

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

#### 4. 추천 테이블

> - **recommend_id** : 추천 고유 ID (int)
> - **recommend_title** : 추천 이름 (varchar)
> - **recommend_category** : 추천 분류 (varchar)
> - **recommend_image** : 추천 이미지 (varchar)
> - **recommend_description** : 추천 설명 (varchar)
> - **recommend_lat** : 추천 위도 (double)
> - **recommend_lng** : 추천 경도 (double)

```sql
CREATE TABLE `foods` (
  `store_id` int auto_increment,
  `store_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `city_id` int not null,
  `store_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `store_review` int DEFAULT '0',
  `store_star` float DEFAULT '0',
  `store_info` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  CONSTRAINT `city_id` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```