# 기능별 View 명세

### Author: 김 형 택

> ### 목차 
>
> 1. **기능별 View 명세**
>    - 1) 유저 관련 기능 ( Front | Function_Users )
>    - 2) 상영관 관련 기능 ( Front | Function_Theaters )
>    - 3) 예매 관련 기능 ( Front | Funtcion_Ticketing )
>    - 4) 추천 관련 기능 ( Front | Function_Recommend(Nearby) )
>    - 5) Map 관련 기능 ( Front | Function_Map - API )
>    - 6) 채팅 관련 기능 ( Front | Function_Chat )

<hr>

### 1. 기능별 View 명세 (Front)

##### 5) Map 관련 기능 ( Front | Function_Map - API )

> **Front | Function_Map (API) **
>
> - Front | VIEW 13 | 해당 지점 위치 찾기 View
> - Front | VIEW 11 | map.ver 맵 구현 ( 마커 및 활동 정보)
> - Front | VIEW 11 | map.ver 마커 선택 시 경로추천 페이지 이동
> - Front | VIEW 7 | 토글시 맵 출력(Function_Map 호출)
> - Front | VIEW 7 | 토글시 맵에서 상영관 마커 표시
> - Front | VIEW 7 | 상영관 마커 클릭시 인포윈도우 구성

##### 6) 채팅 관련 기능 ( Front | Function_Chat )

> - **Front | Function_Chat **
>   - Front | VIEW14 | 채팅 방 목록 View ( 지역 선택: 드롭다운)
>     - 지역별 영화관 채팅 목록 출력 (CGV, 롯데 시네마 등등)
>     - 영화관 이름, 입장하기 버튼, 현재 입장 인원, 채팅방 이미지
>   - Front | VIEW14 | 채팅 방 목록 View ( 극장 선택: 즐겨찾기)
>     - 즐겨찾기 해둔 영화관 채팅 목록 출력
>     - 즐겨찾기 해제 버튼, 입장하기 버튼, 영화관 이름,현재 입장 인원, 채팅방 이미지
>   - Front | VIEW15 | 채팅 방 내부 View
>     - 사용자별 채팅 현황입력 키보드 모양
>     - 상대방 왼쪽, 본인 오른쪽 레이아웃 (사용자 닉네임 표시)
>     - 상단 채팅방 이름 보여주기