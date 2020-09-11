# ERD 설계

### Author: 전 수 현

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

#### 7. 채팅 내용

> - message_id : 메세지 id (기본키)
> - **theater_id : 극장 ID**
> - **user_code : 유저 코드**
> - message_content : 채팅 내용
> - message_time : 채팅이 보내진 시각

```sql
CREATE TABLE ‘chatroom’ (
  `message_id` int AUTO_INCREMENT,
  `user_code` int DEFAULT NULL,
  `theater_id ` int DEFAULT NULL,
  `message_content` text(200) NOT NULL,
  `message_time` DATETIME DEFAULT CURRENT_TIMESTAMP
  PRIMARY KEY(`message_id`),
  CONSTRAINT `fk_message_theater` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`theater_id`) on delete cascade,
   CONSTRAINT `fk_message_users` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

