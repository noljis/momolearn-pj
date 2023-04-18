# ✏️ MOMOLEARN(모모런) 

<img width="1266" alt="00  메인화면" src="https://user-images.githubusercontent.com/117498827/230244583-f5d347ce-80b8-4297-96bb-ca9b394c96dc.png">


## 💡 프로젝트 소개
⚡ Spring Boot + Spring Data JPA 를 이용한 프로젝트입니다. <br>
⚡ 재능기부를 중심으로 하는 온라인 강의 플랫폼 입니다. <br> 
⚡ 누구나 강사 신청이 가능하며, 수강생들이 부담 없이 강의를 수강할 수 있습니다. <br>
⚡ 수강생들끼리 소통할 수 있는 커뮤니티와 같은 다양한 기능을 제공하여 학습을 보다 효과적으로 진행할 수 있도록 도와줍니다. <br>


## ⏰ 개발기간
2023.03.13 ~ 2023.04.07



## 👩‍💻 팀원

|   이름   |                        깃허브 주소                         | 
| :------: | :--------------------------------------------------------: | 
|  🌱김솔진  | [https://github.com/noljis](https://github.com/noljis)   | 
|  🌌박혜인  | [https://github.com/PHyeinP](https://github.com/PHyeinP) |
|  🐲이민지  | [https://github.com/minddi00](https://github.com/minddi00) |
|  🍋이시현  | [https://github.com/SIHYEONee](https://github.com/SIHYEONee) |


## ‼️ DB설정을 위해 다음 파일을 받아주세요
- [application-db.zip](https://github.com/HtaTeam2/momolearn-pj/files/11047249/application-db.zip) (경로 : src/main/resources)



## ☁ ERD

<img src="https://user-images.githubusercontent.com/111185073/229674426-24f719f6-ee88-48f1-9b1f-89627b420b43.png" width="80%"/>


## <a name="list"></a>📜List 
**💡소제목을 클릭하시면 다시 리스트로 돌아옵니다.**



⚒️[기술 스택](#1) <br>
💻[주요 기능](#2) <br>
💣[트러블 슈팅](#3) <br>
✨[프로젝트 개선할 점](#4) <br>



## ⚒️<a name="1">[기술 스택](#list)</a>
<p>
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=green">
<img src="https://img.shields.io/badge/spring data jpa-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> 
<img src="https://img.shields.io/badge/java-0F2B77?style=for-the-badge&logo=openjdk&logoColor=white"> 
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
 <br>
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/ajax-5A29E4?style=for-the-badge&logo=ajax&logoColor=white">
<img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
<img src="https://img.shields.io/badge/bootstrap-9933FF?style=for-the-badge&logo=bootstrap&logoColor=white">
<br>
<img src="https://img.shields.io/badge/spring tool suite 4-6DB33F?style=for-the-badge&logo=spring&logoColor=green">
<img src="https://img.shields.io/badge/Visual Studio Code-00A6CC?style=for-the-badge&logo=visualstudiocode&logoColor=blue">
<img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/sourcetree-0052CC?style=for-the-badge&logo=sourcetree&logoColor=white">
<br>
<img src="https://img.shields.io/badge/slack-4F007A?style=for-the-badge&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/notion-00C9C2?style=for-the-badge&logo=notion&logoColor=black">
<img src="https://img.shields.io/badge/discord-5865F2?style=for-the-badge&logo=discord&logoColor=black">
<img src="https://img.shields.io/badge/chat gpt-412991?style=for-the-badge&logo=openai&logoColor=white">

</p>

## 💻<a name="2">[주요 기능](#list)</a>

<img src="https://user-images.githubusercontent.com/111185073/230519744-787b374d-6c0d-457f-b083-bd7d1fadc75c.png"/>

**💡소제목을 클릭하시면 다시 리스트로 돌아옵니다.**

 1. [회원가입](#join) (일반 회원가입/소셜 간편가입)
 2. [로그인](#login) 
 3. [강사 신청](#teacher)
 4. [강의 등록](#lecture)
 5. [수강 신청](#payment)
 6. [게시판](#community)

### 1. <a name="join">[회원가입](#2)</a>
#### (1) 일반 회원가입
![회원가입](https://user-images.githubusercontent.com/117498827/230251565-fa1a97aa-7689-4471-877f-816c69f8548c.gif)

⚡필수 기입 속성은 ID, PW, 이름, Email 입니다.<br>
⚡최초 가입시 등급은 student(학생)등급입니다. <br>
⚡프로필 사진은 등록하지 않으면 'user.jsp'라는 모모런 기본 프로필로 등록이 됩니다.<br>
<img width="142" alt="image" src="https://user-images.githubusercontent.com/117498827/230252114-9eee75df-41e8-4be2-9d44-2edc9cc9674c.png">

#### (2) 소셜 간편가입
![소셜회원가입](https://user-images.githubusercontent.com/117498827/230406090-d0f1cd02-46fd-4f40-b951-c8f2ab39fc59.gif)
⚡소셜 회원가입 시 개인정보 제공에 대한 동의서를 제시합니다. 동의 항목은 닉네임, 이메일주소입니다.<br>
⚡가입정보는 회원테이블에 저장되며, 이후 일반로그인으로 로그인이 가능합니다.<br>
⚡가입시 카카오계정의 ID부분이 모모런의 ID가 됩니다.<br>

### 2. <a name="login">[로그인](#2)</a>
![일반 로그인](https://user-images.githubusercontent.com/117498827/230252820-0b2446d8-be9a-4a7f-9132-3ef179198280.gif)
⚡필수 기입 속성은 ID, PW입니다.<br>

### 3. <a name="teacher">[강사 신청](#2)</a>
<p align="center">
 
<img src="https://user-images.githubusercontent.com/117498827/230254773-c21529f7-6f8d-4d69-8f75-4514dc600bcf.gif" alt="강사신청" width="500"/>
<img src="https://user-images.githubusercontent.com/117498827/230516168-2917b3f3-35e2-4006-a7c8-667d2064b712.gif" alt="강사 승인" width="500"/><br>

</p>

⚡강사 신청 가능 등급은 student(학생)입니다.<br>
⚡한 회원당 신청서는 하나만 작성할 수 있습니다.<br>
⚡기존 회원의 정보가 신청서에 입력되며, 필수 기입 속성은 연락처, 포트폴리오url, 희망분야, 자기소개입니다.<br>
⚡관리자는 회원들의 신청서를 모두 조회할 수 있고, 승인할 수 있습니다.<br>
⚡신청서는 수정과 삭제가 가능하며, 관리자가 승인처리시 수정과 삭제가 불가능합니다.<br>


### 4. <a name="lecture">[강의 등록](#2)</a>

![강의등록](https://user-images.githubusercontent.com/117498827/230260375-5400e8c9-afdc-4c3f-a680-8d9b238d7634.gif)

⚡강의등록 가능 등급은 teacher(강사)입니다.<br>
⚡카테고리는 추후 변경할 수 없습니다.<br>
⚡강의등록 후 강의에 종속되는 강좌들을 등록합니다.<br>
⚡강의는 강의의 주인인 강사가 수정과 삭제가 가능합니다. 강좌 마다 수정이 가능하며, 강좌 추가가 가능합니다.<br>


### 5. <a name="payment">[수강 신청](#2)</a>

<p align="center">
 
<img src="https://user-images.githubusercontent.com/117498827/230262928-e6d9a2cc-b91c-4d66-a0a6-04b90b636f2d.gif" alt="장바구니_강의결제" width="500"/>
<img src="https://user-images.githubusercontent.com/117498827/230263110-17e9becb-c758-45a3-9a82-db8b99885824.gif" alt="장바구니_강의결제 후 수강신청" width="500"/><br>

</p>

⚡전체 등급이 수강신청 가능하며, 결제는 카카오페이를 사용합니다.<br>
⚡수강바구니에서 원하는 강의를 선택하여 수강신청 또는 삭제할 수 있습니다.<br>
⚡총 결제액이 0원일 경우 결제과정을 거치지 않고 바로 수강됩니다.<br>
⚡결제 후에 내 강의페이지로 이동합니다.<br>

### 6. <a name="community">[게시판](#2)</a>
#### (1) 커뮤니티
![게시판_커뮤니티](https://user-images.githubusercontent.com/117498827/230266186-db556184-1471-4e07-95cb-a90a7a01be2d.gif)
⚡전체 등급이 읽기/작성/수정/삭제가 가능하며, 수정과 삭제는 작성자만 가능합니다.<br>
⚡속성별로 조회가 가능하며, 페이지당 게시글 10개까지 조회합니다.<br>
⚡상단에는 실시간 인기글이 5개씩 노출됩니다.<br>
⚡좋아요 버튼을 누른 게시글 목록을 확인할 수 있습니다.<br>

#### (2) 공지사항
![공지](https://user-images.githubusercontent.com/117498827/230266720-ecc349d6-f650-4e42-9d5a-041904e37198.gif)
⚡조회는 전체 등급이 가능하며, 작성/수정/삭제는 관리자 등급만 가능합니다.<br>
⚡사진 등록시 해당 사진이 썸네일로 노출됩니다.<br>


1. 회원가입/탈퇴/로그인
2. 소셜 간편회원가입
3. ID/PW 찾기
4. 회원/관리자 프로필 관리
5. 강사 신청/신청서 관리
6. 강사 승인/등록
7. 강의 검색
8. 강사 강의/강좌 관리
9. 장바구니/결제
10. 강의 수강
11. 내 강의
12. 커뮤니티 게시판-글 관리/댓글 관리/좋아요/좋아요한 글 
13. 커뮤니티 게시판-검색/실시간랭킹/페이징/회원정보,작성글 확인
14. 공지사항 게시판

## [💣](#list)<a name="3">3. 트러블 슈팅</a>
☑ Lecture 조회 쿼리에서 강의를 조회한 후 카테고리를 조회하여 1+N 문제 발생<br>
👉join fetch를 사용하여 해결하려고 하였으나, 쿼리 중복이 발생하여 강의 개수만큼 카테고리의 배열이 출력되는 이슈가 새롭게 발생하여 EntityGraph를 사용하여 해결하였습니다.<br>
☑ multipart도입 후 max-file-size: 3MB #파일의 최대 사이즈를 적용해도 1MB까지만 업로드 가능한 이슈<br>
👉 yml에서 경로를 수정해주었습니다(server 하위 → servlet 하위로 이동)<br>
```yaml
multipart:
    enabled: true
    location: src/main/webapp/img
    max-file-size: 3MB
    max-request-size: 3MB
```
☑ 카카오페이 API 도입시 결제금액이 0원일경우 예외 발생<br>
👉 0원일 경우 결제로 가지 않고 바로 수강신청되는 js함수를 생성하여 해결하였습니다.<br>
☑ 기존에 사용하던 Swagger2.9.2버전에 대한 오류<br>
👉 Swagger를 2.9.2버전에서 3.0버전으로 업그레이드 해주었습니다.(gradle의존경 변경)<br>
## [✨](#list)<a name="4">4. 프로젝트를 진행하며 느낀 점</a>
✅ JPA가 확실히 SQL문을 직접 쓰지 않아도 되는 것은 좋지만, 1+N문제 등을 발생시키지 않고 JPA를 더 잘 사용하기 위해 SQL을 더 깊이있게 공부해야 한다는 필요성을 느꼈습니다.<br>
✅ `@Autowired`로 주입을 할 경우 순환참조 문제가 발생할 수 있어, final을 사용한 생성자 주입방식을 사용해야 한다는 점을 알게 되었습니다. <br>
✅ 프로젝트에서 데이터베이스 테이블 간의 관계를 더욱 명확하게 정의하고 구조화할 필요성을 인지하였습니다.
