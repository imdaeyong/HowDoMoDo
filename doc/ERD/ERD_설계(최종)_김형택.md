# ERD 설계 (최종)

### Author: 김 형 택

> ### 목차 
>
> 1. **ERD 설계**
>
>    - 1) 회원 정보 테이블
>
>      -  email, 이름, 닉네임, pw, 성별 
>
>    - 2) 지역별 극장 테이블
>
>    - 3) 예매 테이블
>
>    - 4) 추천 테이블
>
>      - 활동 전후 - 빅데이터 분석 결과
>
>    - 5) 맛집 테이블
>
>    - 6) 채팅 테이블 
>
>      - 사용자 아이디, 채팅방 정보
>

<hr>

### 1. ERD 설계

##### 5) 맛집 테이블 (맛집 빅데이터 사용)

> - **store_id** : 맛집 고유 ID (int)
> - store_name** : 맛집 이름 (varchar)
> - **city_id** : 도시 ID (주소)
> - **store_type** : 업종 분류 (varchar)
> - **store_review** : 맛집 리뷰 갯수 (int)
>   - 간단한 블로그 리뷰 크롤링으로 맛집 추천 가능 (확장 기능)
> - **store_star** : 평점 (float)
> - **store_info** : 맛집 정보 (varchar)
>   - 소개글, 영업 시간, 휴일 등 관련된 정보

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
