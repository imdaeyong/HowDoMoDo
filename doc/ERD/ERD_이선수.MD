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
> - user_role : 유저 권한(varchar)



#### 6. 채팅목록

> - **user_code** : 유저 코드 (int)
> - **chatroom_id**: 채팅방 ID (int)



#### 6-1. 채팅방 정보

> - **chatroom_id** : 채팅방 ID (int) pk
> - **chatroom_title** : 채팅방 제목