# ERD 설계 (최종)

### Author: 김 대 용

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

<hr>

### 1. ERD 설계

##### 5) 예매 기록 + @ 

>- **history_id** : 예약번호 (pk)  
>- **user_code** : int // 사용자 uid
>- movie_id: int // 영화 id? 같은값
>- movie_name: varchar // 영화이름
>- **theater_id**:int // 영화관 고유 ID
>- theater_name : varchar // 영화관 지점명
>- room_name : varchar //상영관(ex. 1관, 2관…)
>- select_time:datetime // 예매한 시간 (현재시간 x)

> (3-1)  예매 좌석 정보
>- **seat_id** : 예매고유번호(AI,PK)
>- **history_id**: 예약번호
>- seat_code : 좌석코드


```sql
CREATE TABLE `historys` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `user_code` int DEFAULT NULL,
  `movie_id` varchar(45) DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  `theater_name` varchar(45) DEFAULT NULL,
  `room_name` varchar(45) DEFAULT NULL,
  `select_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`),
  KEY `user_fore_idx` (`user_code`),
  KEY `theater_fore_idx` (`theater_id`),
  CONSTRAINT `theater_fore` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`theater_id`) ON DELETE CASCADE,
  CONSTRAINT `user_fore` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `selected_seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `history_id` int DEFAULT NULL,
  `seat_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `history_fore_idx` (`history_id`),
  CONSTRAINT `history_fore` FOREIGN KEY (`history_id`) REFERENCES `historys` (`history_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```


