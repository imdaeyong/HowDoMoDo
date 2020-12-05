# 📖 How Do Mo Do Project 

![version](https://img.shields.io/badge/version-1.0.0-orange?)![spring](https://img.shields.io/badge/spring-4.0.0-yellow?logo=spring)![spring-boot](https://img.shields.io/badge/springboot-4.0.0-yellow?logo=spring) [![mysql](https://jaywcjlove.github.io/sb/ico/mysql.svg)](http://www.mysql.com/)![aws-ec2](https://img.shields.io/badge/aws-ec2-blue)

### 🏠 [App Download Homepage](http://j3a305.p.ssafy.io:8080/home/index.html)
### 🎥 [App 시연영상](https://www.youtube.com/watch?v=thH3XNfE6p4)
![image](https://user-images.githubusercontent.com/39832802/98457831-2e60af80-21ce-11eb-996c-c378998fe66e.png)
<hr>



### 📂 Contents

- [Project 소개](#%EF%B8%8F-영화-관련-추천-서비스)
- [기술 스택](#-기술-스택)
- [사용 기술](#-사용-기술)
- [Git Flow](#-git-flow) 
- [역할분담](#-역할-분담)
- [Jira](#-jira)
- [화면 구성 Flow](#-화면-구성-flow)
- [프로젝트 산출물](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc)    
    - [프로젝트 계획서](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/blob/develop/doc/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EA%B3%84%ED%9A%8D%EC%84%9C/%EC%84%9C%EC%9A%B83%EB%B0%98_A305_%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8_%EA%B3%84%ED%9A%8D%EC%84%9C.pdf)
    - [기능 명세서](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc/%EA%B8%B0%EB%8A%A5%20%EB%AA%85%EC%84%B8)
    - [와이어프레임](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/blob/master/doc/%EC%99%80%EC%9D%B4%EC%96%B4%ED%94%84%EB%A0%88%EC%9E%84/%EC%B5%9C%EC%A2%85_%EC%99%80%EC%9D%B4%EC%96%B4_%ED%94%84%EB%A0%88%EC%9E%84.pdf)
    - [ERD](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc/ERD)
    - [API문서](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc/API%EB%AC%B8%EC%84%9C)
    - [UCC Download](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc/UCC%EC%98%81%EC%83%81)
    - [발표자료](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc/%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C)
    - [회의록](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a305/tree/develop/doc/%ED%9A%8C%EC%9D%98%EB%A1%9D)

<hr>

### 🖥️ 영화 관련 추천 서비스

- ☝ **영화사 통합 플랫폼**

  - **3개의 영화사(CGV, 롯데시네마, 메가박스)** 상영시간 통합을 통한 **빠른 정보 검색과 활동을 고려한 영화관 선택** 

- ☝ 카드사 결제정보 빅데이터를 활용한 영화관 **주변 활동 분석 및 추천!** (맛집, 쇼핑 등의 여가활동)

- ☝ 영화의 수많은 **리뷰 긍/부정 분석을 통해 영화를 추천**!


<hr>

### 📃 기술 스택

![image](https://user-images.githubusercontent.com/33771279/95332900-fd4b4180-08e6-11eb-812e-89241f116cfe.png)

**SW Architecture**

![image](https://user-images.githubusercontent.com/33771279/95333316-95492b00-08e7-11eb-9815-db977c6ddd1b.png)

**BACKEND**

1. Programming Languages : [ Java 8 ] 
2. Frameworks : [ Spring ] 
   - Tool : [ Spring boot ]
3. SQL data storage : [ MySQL ]
5. Web Server : [ Nginx ]
6. Web application server : [ Apache Tomcat ]
7. Hosting : [ AWS ]

**FRONTEND**

1. Programming Languages : [ Kotlin ]
2. Framework Tool : [ Android Studio ]

**BigData/AI/Crawling**

1. Programming Languages : [ Python ]
2. Framework Tool : [ Django ] [ Spark ]
3. SQL data storage : [ MariaDB]
4. Hosting : [ AWS ]



<hr>

### 📃 사용 기술

#### [Backend]

> **Spring boot** : How Do Mo Do Project의 전반적인 기능 Rest Controller 구현
>
> **Swagger** : Swagger를 이용하여 RESTful API 문서 자동화
>
> **MariaDB** :  Docker에 구성하여 데이터를 관리
>
> **AWS** : EC2 서비스를 이용하여 Ubuntu 서버를 구축(호스팅)
>
> **Nginx** : 웹서버를 구축



#### [Frontend]

> **MVVM**: MVVM (Model, View, View Model) 패턴을 활용하여 뷰 모델과 모델이 뷰로부터 독립적인 형태를 만들어서 UI로부터 				비즈니스 로직과 프레젠테이션 로직을 분리
>
> - 뷰는 **UI**와 **UI 로직**을 다룬다.
> - 뷰 모델은 **프레젠테이션 로직**과 뷰를 위한 **상태**를 다룬다.
> - 모델은 **비즈니스 로직**과 **데이터**를 다룬다.
>
> **Naver 검색 API**: 네이버 블로그 검색 결과를 출력해주는 REST API를 활용하여 영화에 대한 블로그 리뷰 View를 구성
>
> **Recycler View**: Recycler View를 활용하여 리스트화된 데이터를 효과적으로 보여줄 수 있도록 View를 구성
>
> - *Adapter* : 새로운 뷰의 추가를 위한 어뎁터
>   - 데이터를 가져와서 뷰에 적용
>   - Recycler View에게 View Holder를 전달
> - *View Holder* : 실제 아이템의 레퍼런스를 가지고 있고, 뷰에 새로운 데이터를 넣어 업데이트할 때 캐시로 사용  
>   - 'findviewbyid'를 Layout에 뷰가 추가될 때가 아닌 뷰가 생성될 때만 호출되게 최소화
> - *Layout Manager* : Recycler View에게 뷰를 어떻게 보여줄지 알려주는 역할
>   - 수직, 수평 , 그리드 등으로 보여주는거나 아이템을 추가 제거할때 애니메이션 효과 사용
>
> **Retrofit2**: API에 특화된 CRUD 방식의 웹 서버 연결을 편하게 구현할 수 있는 라이브러리를 활용하여 통신 작업을 진행
>
> **OkHttp**: HTTP & HTTP /2 통신 클라이언트 라이브러리를 활용하여 Request, Response 방식의 통신 활용
>
> **Glide**: 구글의 안드로이드 이미지 로딩 라이브러리인 Glide를 활용하여 Recycler View에 영화 포스터를 넣기 위해 사용
>
> **Koin AndroidX**: DI기술 사용을 위해 코틀린을 위한 DI 라이브러리 Koin AndroidX를 사용
>
> - **DI** (Dependency Injection): 의존성 주입 
>   - **구성요소간의 의존 관계가 소스코드 내부가 아닌 외부 설정 파일등을 통해 정의되게하는 디자인 패턴**
>     - EX) `카페에서 커피를 만드는데 커피 머신이 어떤 부품으로 구성되어있는지 바리스타는 알필요가 없다`
>     - 이렇게 분리시켜 놓으면 객체의 생성과 사용을 분리시킬 수 있고, 재사용이 유연해진다.
>   - `재사용성`을 높임
>   - `테스트`에 용이
>   - 코드를 `단순화`
>   - `종속된 코드`를 감축
>   - `결합도`를 낮추면서 `유연성`과 `확장성`이 향상
>
> **KAKAO Map**: 영화관 리스트를 지도에서 보여주기 위해서 카카오맵 API를 이용.



#### [Big Data]

> **Pyspark**: pyspark 라이브러리를 활용하여 '19.09~'19.12 (4개월) 간의 카드사 데이터 분석
>
> - 데이터 전처리 및 SQLContext를 활용한 그룹핑 작업
>
> - persist()를 활용한 Action 결과 Dataframe 캐쉬 저장 (DISK AND CACHE)
>
> - 단계별 모듈화로 사전 분석을 통해 연산 과정을 단축시킴
>
> - 동일한 instance로 접근하여 생성한 cache값을 활용할 수 있도록 singleton pattern 적용
>
>   
>
> **Bertforsequenceclassification**:  Transformers의 pre-trained model 활용 (약 15만 개 리뷰 긍부정 데이터 학습)
>
> - 사전 학습은 Colab에서 진행
> - LSTM 대비 정확도 상승
> - Crawling을 활용하여 '네이버 영화' 페이지의 리뷰 두 페이지 분량 추출 및 평가 데이터 폼 구축
> - 현 개발환경에 GPU가 없어 상대적 속도 감소

<hr>

### 📃 Git Flow

- **MR / Pull Rule**

  - 스프린트 완료 후 일주일에 한번은 `develop`에 MR
  - `Front` / `Back` 에 각자 `Feature`를 MR,  `develop`  Pull 하여 최신화 

- **Commit Message Rule**

  `{상태} {기능} | {JIRA issue ID}`

  - ex)  `Feat Login Function | S03P11A305-1`
  - ex) `Update Movie Function Communication | S03P11A305-2` 
  

  `[마무리] | {날짜 }| {JIRA issue ID}`

  - ex) `[마무리] | 2020.07.21 | S03P11A305-1`
  

  `[MR] | '{소스브랜치}' into '{타겟브랜치}'`

-  **진행상황** 
>  - Todo
>  - In Progress
>  - Done

- **Git Branch 전략**

  - Master

    - Develop

      - Front

        - Front_doc
        - Front_epic Name
      - Back
        - Back_doc
        - Back_Setting
        - Back_Users
        - Back_Theaters
        - Back_Current_Movielist
        - Back_django
        - Back_api
        - Back_Crawling
        - Back_SelectTheaters
      - bigdata_recommand
      - pn_score_analysis
      - release

<hr>

### 📃 역할 분담

- - #### PM & BigData

  🕵‍♂ **이선수**

  ```
  1. bigdata analysis of activitiy 

  	1) spark 세션 생성 및 분석 데이터 로드

  	2) 시구군 기준 그룹핑 dataframe 생성

  	3) param에 따른 dataframe 분석

  	4) 결과값 response (json)

  	5) Django RESTful api 활용 (GET)

  	6) python 모듈화 및 singleton pattern 적용 (여러 세션의 접근에 동일한 사전분석 데이터를 활용하기 위해)

  2. positive/negative score analysis

  	1) Bertforsequenceclassification 15만개 긍부정 리뷰 사전 학습 및, 학습 모델 추출 (Colab 환경에서 학습 후 추출) 

  	2) Crawling을 통해 '네이버 영화' 홈페이지의 리뷰 2페이지 분량 추출

  	3) 평가 데이터 토큰화 및 어텐션 마스킹, 파이토치의 텐서형으로 변환

  	4) Django RESTful api 활용 (GET)

  	5) python 모듈화 및 singleton pattern 적용 (여러 세션의 접근에 동일한 사전분석 데이터를 활용하기 위해)

  	6) AWS 환경설정 및 배포 관리
  ```
<br>
 <hr>

- #### Frontend

  👱 **김찬영**
  ```
    1. 상영관 선택 페이지

	    1) 중첩 Recycler View를 통한 상영 영화 시간 정보 구성

		    - 영화사 페이지 크롤링 데이터 활용

	    2) Recycler View를 통한 날짜 데이터 정보 구성

		    - 현재 시간으로부터 7일간 날짜를 저장하여 출력

    2. 장소 선택 페이지 

	    1) 시/도, 구/군 선택 서버 연동하여 데이터 출력

   	    2) 구/군 데이터를 통해 카드데이터 분석 결과 출력
  ```

  👨 **김대용**
  ```
  1. Signup Page

	  1) Signup Activity, Signup ViewModel로 뷰 구성 및 Backend와 통신

	  2) 데이터 유효값 확인

		  - 이메일, 닉네임 중복체크 기능 구현 - 둘 중에 하나라도 중복 체크가 되지않았으면 회원가입 불가

		    - 생년월일 및 이메일, 닉네임 Validate Check

  2. Theater Page

	  1) Recycler View, Bottom Sheet, Kakao Map을 이용한 View 구성

		  - 하단 Bottom Sheet 안에 Recycler View를 이용해 영화관 리스트 구성

		  - 리스트 클릭시 맵에서 해당 위치 Marker로 이동 및 하단 버튼 활성화

		  - 해당 아이템 오른쪽 즐겨찾기 버튼 누를시 즐겨찾기 추가/삭제

	  	- 영화관별로 마커이미지 상이, 마커 클릭시 해당 영화관 정보를 담은 InfoWindow 출력

  		- InfoWindow 클릭시 현재 위치로부터 해당 위치까지 길찾기 기능(KakaoMap)

  3. Favorite Page

	  1) View 구성

		  - Bottom Tab에서 즐겨찾기 클릭시 RecyclerView를 이용한 즐겨찾기 리스트 구현

	  	- 오른쪽에 별모양을 눌러 즐겨찾기 삭제 기능

	  	- 상영관 클릭시 영화선택 -> 시간대 선택 페이지로 바로 이동.

  4. 시연 UCC 제작
  
  ```

  👦 **김형택**

  ```
  1. Main Page

  	1) Recycler View를 통한 실시간 상영 영화 정보 구성

	  	- 실시간 상영 영화 API 활용

	  	- 예매하기 버튼을 클릭하여 해당 영화에 대한 긍정/부정 분석 결과를 Dialog로 출력

	  	- BigData 분석 결과를 통한 긍정/ 부정 점수를 통신하고 결과값을 받아와 Dialog에 적용

	  	- 영화관 선택 화면으로 연결

  	2) Recycler View를 통한 영화 블로그 리뷰 정보 구성

	  	- Spinner를 통해 영화를 선택

	  	- 선택된 영화를 네이버 블로그 검색 API 활용

  		- Web View Activity를 활용하여 해당 블로그로 연결

  2. Login Page

  	1) Login Activity, View Model, layout을 통해 view를 구성

  	2) Backend 파트와 로그인 관련 통신

  3. MyPage

  	1) 회원정보 수정 Activity, View Model, layout을 통해 view를 구성

  	2) 회원 탈퇴 Activity, View Model, layout을 통해 view를 구성

  	3) Backend 파트와 회원정보 수정, 탈퇴 기능 통신

  4. Dialog Activity

	  1) 회원 탈퇴 및 영화 리뷰 긍정/부정 분석 정보 Dialog Activity를 구현

	  2) 사용자에게 효과적으로 보여질 수 있도록 layout을 구현

  5. Splash Page

	  1) App 처음 실행 화면 Splash Activity, layout을 통해 view를 구성
  ```
<br>
 <hr>

- #### Backend

  👨 **권오정** 

  ```
  1. 운영서버 환경 구축

  	1) Database 서버

	  	- Docker 설치

	  	- Docker MariaDB 이미지 Database 서버 설치

	  2) Django 서버

	  	- Anacoda 설치

	  	- Django 서버 구축

  2. Crwaling

	  1) 영화관 정보

		  - 각 영화사 극장명, 주소 수집

		  - 네이버 오픈 API (map-geocode)로 극장 위도, 경도 수집

	  2) 현재 상영 영화 정보

		  - 네이버 영화 > 현재 상영영화 > 상영영화 정보 수집

	  3) 현재 사영중인 영화관 정보

		  - 영화사 > 극장 > 사영영화 예매 정보 수집

  3. 소개 UCC 제작
  ```

  👩 **전수현**

  ```
  1. Spring Server

	  1) 회원정보 CRUD

	  2) 시구군 및 즐겨찾기 CRUD

  2. Database

	  1) movie_project 데이터베이스 생성

	  2) 회원, 권한, 지역, 영화관 테이블 생성
  ```

<br>
<hr>
<br>
  

### 📃 Jira

##### 프로젝트 관리 도구로 Jira를 사용, Issue를 등록하여 프로젝트를 진행

- Epic : 전체적인 큰 기능들을 Epic으로 구성
  - Ex) Front | User Function (회원 관리) , Back | Theater Function (상영관)

- Story : Epic과 연결하고 Epic에 관련된 기능 구현을 위주로 구성
  - Ex) Front | Main Page , Back | Movie CRUD, BigData | 긍부정 분석 
- Bug : 테스트 과정에서 발견된 bug를 등록
  - Ex) Front | Kakao Map



### 📃 화면 구성 Flow

#### 홈 화면

![image](https://user-images.githubusercontent.com/45157374/95287243-593ea780-08a0-11eb-9b9c-9311a42c1313.png)


- 현재 상영중인 영화리스트를 상단에 가로 형태로 보여줍니다. (네이버 영화 크롤링)

- 하단에 영화 제목으로 네이버 블로그 검색결과를 보여줍니다. -> 영화의 후기를 참조 할 수 있습니다.

- 예매하러가기 클릭 시 영화 긍부정 데이터를 보여주게 됩니다.

<br>


#### 영화 긍부정 데이터 분석 화면

![Screenshot_20201007-204701_HowDoMoDo](https://user-images.githubusercontent.com/45157374/95327869-a9892a00-08df-11eb-9ba4-2f835fa8883a.jpg)

- 해당 영화를 선택하기 전에 사용자에게 관객의 긍부정 데이터를 제공함으로서 영화 선택에 도움을 줍니다.

- 댓글 빅데이터를 분석하여 긍부정 데이터 출력

<br>


#### 장소 선택 및 카드데이터 분석 결과 출력 화면

![Screenshot_20201007-204712_HowDoMoDo](https://user-images.githubusercontent.com/45157374/95328101-01c02c00-08e0-11eb-9af3-b4a79e82b713.jpg)
![image](/uploads/7a7fa42e9680f979616332ad64d1867b/image.png)

- 시/도, 구/군을 선택하여 사용자가 영화 관람을 원하는 장소를 선택합니다.

- 해당 장소에서 사용된 카드 데이터를 분석하여 사용자에게 어떠한 활동이 많이 아루어졌는지 보여줍니다.

- 그 결과 사용자가 원하는 활동이 있는 장소를 고를 수 있도록 도와줍니다.

<br>



#### 영화관 선택 화면

![image](/uploads/38361594fbeb06829f27eda3ab6937fd/image.png)
![Screenshot_20201007-204805_HowDoMoDo](https://user-images.githubusercontent.com/45157374/95328339-65e2f000-08e0-11eb-8a9a-0d26e605ec66.jpg)
![image](/uploads/bc675b09c061f1d9ae333f8a68abb007/image.png)

- 해당 장소에 있는 3개 회사(메가박스, CGV, 롯데시네마)를 모두 출력해줍니다.

- 리스트에서 영화관을 선택하게 되면 지도의 가운데로 이동시킵니다.

- 지도의 마커를 클릭하면 해당 영화관까지의 길찾기 정보를 보여줍니다.

- 영화관을 선택한 뒤 다음 단계로 넘어가면 상영시간대를 선택합니다.


<br>


#### 상영 시간 선택 화면


![Screenshot_20201007-204831_HowDoMoDo](https://user-images.githubusercontent.com/45157374/95329496-fe2da480-08e1-11eb-928c-97529d37ace2.jpg)

- 선택한 영화의 상영시간대를 보여줍니다.

- 해당 날짜에 영화 상영 계획이 없으면 보여주지 않습니다.

- 최대 일주일간의 영화 상영 계획표를 보여줍니다.


<br>

#### 예매 정보 화면

![image](https://user-images.githubusercontent.com/45157374/95329661-48168a80-08e2-11eb-8e76-85c80ca48740.png)

- 지금까지 선택한 예매 정보를 보여줍니다. 

- 사용자로 하여금 정보를 한번 더 확인 시키고 예매사 연결 버튼을 통해 선택한 영화사 어플로 연결시켜줍니다.

##### 영화사 어플 화면

![image](https://user-images.githubusercontent.com/45157374/95329811-86ac4500-08e2-11eb-9268-1711238a71f4.png)

- CGV의 영화 어플로 연결한 화면입니다.

<br>



#### 홈 돌아가는 화면

![image](https://user-images.githubusercontent.com/45157374/95329911-ae9ba880-08e2-11eb-95f0-89e2dd36810f.png)


- 마지막 정리 멘트를 보여주며, 홈 화면으로 돌아가게 합니다.

<br>


#### 즐겨찾기 화면

![Screenshot_20201007-204634_HowDoMoDo](https://user-images.githubusercontent.com/45157374/95330011-da1e9300-08e2-11eb-8b14-1a85a4f06627.jpg)


- 사용자가 즐겨찾기한 영화관을 보여줍니다.

- 영화관을 선택하게 되면 영화를 선택하는 다이얼로그를 띄워줍니다.


##### 즐겨찾기에서 영화 고르는 화면

![image](https://user-images.githubusercontent.com/45157374/95330096-00dcc980-08e3-11eb-9795-4f1d5d6ee726.png)


<br>


#### 마이페이지 화면

![image](https://user-images.githubusercontent.com/45157374/95330157-1520c680-08e3-11eb-9c56-47c5c12a8102.png)

- 내 정보 및 비밀번호 변경이 가능합니다.

- 로그아웃 및 회원탈퇴를 진행할 수 있습니다.

