# ERD 설계

### Author: 이 선 수 

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



#### 1. 회원정보

> - **user_code** : 유저 코드(int) pk
> - **user_email** : 유저 이메일(varchar)
> - **user_name** : 유저 성명(varchar)
> - **user_nick** : 유저 닉네임(varchar)
> - **user_pw** : 유저 비밀번호(varchar)
> - **user_gender** : 유저 성별(int)
> - **user_birth** : 유저 생년월일(date)
> - **user_loginkind** : 유저 로그인 유형(int)
> - user_role : 유저 권한(varchar)



#### 6. 채팅목록

> - chatlist_id : 채팅 목록 ID int (AI, PK)
> - **user_code : 유저 코드 int** 
> - **theater_id : 극장 ID** 



```sql
CREATE TABLE `users` (
  `user_code` int,
  `user_email` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_nick` varchar(45) NOT NULL,
  `user_pw` varchar(45) NOT NULL,
  `user_gender` int NOT NULL,
  `user_birth` date NOT NULL,
  `user_loginkind` int NOT NULL,
  PRIMARY KEY (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `chatlist` (
  `chatlist_id` int  AUTO_INCREMENT,
  `user_code` int DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  PRIMARY KEY (`chatlist_id`),
  CONSTRAINT `fk_users2` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) on delete cascade,
  CONSTRAINT `fk_theater` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`theater_id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

```



