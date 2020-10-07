# 📖 How Do Mo Do Project 

![version](https://img.shields.io/badge/version-0.0.1-orange?)![spring](https://img.shields.io/badge/spring-4.0.0-yellow?logo=spring)![spring-boot](https://img.shields.io/badge/springboot-4.0.0-yellow?logo=spring)[![mysql](https://jaywcjlove.github.io/sb/ico/mysql.svg)](http://www.mysql.com/)![aws-ec2](https://img.shields.io/badge/aws-ec2-blue)

### 🏠 [App Download Homepage](http://j3a305.p.ssafy.io:8080/home/index.html)

<hr>

### 📂 Contents

- [Project 소개](#-영화-관련-추천-서비스)
- [기술 스택](#-기술-스택)
- [사용 기술](#-사용-기술)
- [Git Flow](#-Git-Flow) 
- [역할분담](#-역할-분담)
- [Jira](#-jira)
- [화면 구성 Flow](#-화면-구성-Flow)

<hr>

### 🖥️ 영화 관련 추천 서비스

- 프로젝트 소개


<hr>

### 📃 기술 스택

이미지 첨부할 것

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



<hr>

### 📃 사용 기술

**Spring boot** : How Do Mo Do Project의 전반적인 기능 Rest Controller 구현

**Swagger** : Swagger를 이용하여 RESTful API 문서 자동화

**MySql** : 

**AWS** : EC2 서비스를 이용하여 Ubuntu 서버를 구축(호스팅)

**Nginx** : 웹서버를 구축



**Recycler View**: 

**MVVM**: 

**OkHttp**: 

**Glide**: 

**Koin AndroidX**: 

**KAKAO Map**: 



<hr>

### 📃 Git Flow

- **MR / Pull Rule**

  - 스프린트 완료 후 일주일에 한번은 `develop`에 MR
  - `Front` / `Back` 에 각자 `Feature`를 MR,  `develop`  Pull 하여 최신화 

- **Commit Message Rule**

  `{상태} {기능} | {JIRA issue ID}`

  - ex)  `Feat Login Function | S03P11A305-1`
- ex) `Update Movie Function Communication | S03P11A305-2` `  
  
`[마무리] | {날짜 }| {JIRA issue ID}`
  
- ex) `[마무리] | 2020.07.21 | S03P11A305-1`
  
`[MR] | '{소스브랜치}' into '{타겟브랜치}'`
  
>  **진행상황** 
  >
> - Todo
  > - In Progress
> - Done
  
- **Git Branch 전략**

  - Master

    - Develop

      - Front

        - Front_doc
        - Front_epic Name

      - Back

        - Back_doc
        
        - Back_epic Name
        
          

### 📃 역할 분담

- PM

  🕵‍♂ **이선수**

- Frontend

  👩‍💻 **김찬영** 👨‍💻 김대용 👩‍💻 김형택

- Backend

  👨‍💻 **권오정** 👩‍💻 전수현

- Big Data

  👩‍💻 **이선수**

  

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

- 예매하러가기 클릭 시 예매 플로우로 들어가게 됩니다.

