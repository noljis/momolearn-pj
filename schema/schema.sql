-- 1. members 유저
CREATE TABLE members (
  mem_id VARCHAR(20) NOT NULL,
  pw VARCHAR(20) NOT NULL,
  name VARCHAR(10) NOT NULL,
  email VARCHAR(100) NOT NULL,
  profile VARCHAR(255) NOT NULL DEFAULT 'user.jpg',
  grade VARCHAR(20) NOT NULL DEFAULT 'student',
  regdate DATETIME NOT NULL,
  PRIMARY KEY (mem_id)
);


-- 2. my_lectures 내 강의
CREATE TABLE my_lectures (
  mylec_id INT NOT NULL AUTO_INCREMENT,
  member_id VARCHAR(20) NOT NULL,
  lecture_id INT NOT NULL,
  lec_rege DATETIME NOT NULL,
  PRIMARY KEY (mylec_id),
  FOREIGN KEY (member_id) REFERENCES members (mem_id),
  FOREIGN KEY (lecture_id) REFERENCES lectures (id)
);


-- 3. apply_teacher 강사 신청내역
CREATE TABLE apply_teacher (
  id INT NOT NULL AUTO_INCREMENT,
  apply_id VARCHAR(20) NOT NULL,
  phone_num VARCHAR(20) NOT NULL,
  hope_field VARCHAR(20) NOT NULL,
  intro VARCHAR(255) NOT NULL,
  pf_link VARCHAR(100) NOT NULL,
  apply_rege DATETIME NOT NULL,
  approve VARCHAR(15) NOT NULL DEFAULT 'false',
  PRIMARY KEY (id),
  UNIQUE (apply_id),
  FOREIGN KEY (apply_id) REFERENCES members (mem_id)
);

-- 4. teachers 강사
CREATE TABLE teachers (
  teacher_no INT NOT NULL AUTO_INCREMENT,
  phone_num VARCHAR(20) NOT NULL,
  hope VARCHAR(20) NOT NULL,
  intro VARCHAR(255) NOT NULL,
  pf_link VARCHAR(100) NOT NULL,
  apply_id VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (teacher_no),
  FOREIGN KEY (apply_id) REFERENCES apply_teacher (apply_id)
);

-- 5. lectures 강의
CREATE TABLE lectures (
  id INT NOT NULL AUTO_INCREMENT,
  teacher_no INT NOT NULL,
  title VARCHAR(50) NOT NULL,
  image VARCHAR(255) NOT NULL DEFAULT 'default.jpg',
  price INT NOT NULL,
  cnt INT NOT NULL DEFAULT 0,
  regdate DATETIME NOT NULL,
  info VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  apply_cnt INT NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (teacher_no) REFERENCES teachers (teacher_no)
);

-- 6. category_lecture 카테고리-강의
CREATE TABLE category_lecture (
  cate_lec_id INT NOT NULL AUTO_INCREMENT,
  lecture_id INT NOT NULL,
  category_id INT NOT NULL,
  PRIMARY KEY (cate_lec_id),
  FOREIGN KEY (lecture_id) REFERENCES lectures (id),
  FOREIGN KEY (category_id) REFERENCES category (cate_id)
);

-- 7. category 카테고리
CREATE TABLE category (
  cate_id INT NOT NULL AUTO_INCREMENT,
  cate_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (cate_id)
);

-- 8. courses 강의
CREATE TABLE courses (
  course_id INT NOT NULL AUTO_INCREMENT,
  lecture_id INT NOT NULL,
  title VARCHAR(50) NOT NULL,
  time VARCHAR(12) NOT NULL,
  url VARCHAR(255) NOT NULL,
  PRIMARY KEY (course_id),
  FOREIGN KEY (lecture_id) REFERENCES lectures (id)
);

-- 9. cart 수강바구니
CREATE TABLE cart (
cart_id INT NOT NULL AUTO_INCREMENT,
member_id VARCHAR(20) NOT NULL,
lecture_id INT NOT NULL,
PRIMARY KEY (cart_id),
FOREIGN KEY (member_id) REFERENCES Members (mem_id),
FOREIGN KEY (lecture_id) REFERENCES Lectures (id)
);

-- 10. board 게시판
CREATE TABLE board (
  com_no INT NOT NULL AUTO_INCREMENT,
  mem_id VARCHAR(20) NOT NULL,
  type VARCHAR(20) NOT NULL,
  com_title VARCHAR(50) NOT NULL,
  subject VARCHAR(20) NOT NULL,
  com_regdate DATETIME NOT NULL,
  com_content TEXT NOT NULL,
  com_view_count INT NOT NULL DEFAULT 0,
  PRIMARY KEY (com_no),
  FOREIGN KEY (mem_id) REFERENCES members (mem_id)
);


-- 11. comment 댓글
CREATE TABLE comment (
cmt_no INT NOT NULL AUTO_INCREMENT,
com_no INT NOT NULL,
mem_id VARCHAR(20) NOT NULL,
cmt_content VARCHAR(500) NOT NULL,
cmt_regdate DATETIME(6) NOT NULL,
PRIMARY KEY (cmt_no),
FOREIGN KEY (com_no) REFERENCES Board (board_no),
FOREIGN KEY (mem_id) REFERENCES Members (mem_id)
);

-- 12. likes 좋아요
CREATE TABLE likes (
like_no INT NOT NULL AUTO_INCREMENT,
com_no INT NOT NULL,
mem_id VARCHAR(20) NOT NULL,
PRIMARY KEY (like_no),
FOREIGN KEY (com_no) REFERENCES Board (com_no),
FOREIGN KEY (mem_id) REFERENCES Members (mem_id)
);



-- members 
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('admin', 'admin', '관리자', 'admin@gmail.com', 'admin.jpg', 'admin', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test01', '1234', '학생1', 'test01@gmail.com', 'test01.jpg', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test02', '1234', '학생2', 'test02@gmail.com', 'test02.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test03', '1234', '학생3', 'test03@gmail.com', 'test03.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test04', '1234', '학생4', 'test04@gmail.com', 'test04.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test05', '1234', '학생5', 'test05@gmail.com', 'test05.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test06', '1234', '학생6', 'test06@gmail.com', 'test06.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test07', '1234', '강사1', 'test07@gmail.com', 'test07.jpg', 'teacher', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test08', '1234', '강사2', 'test08@gmail.com', 'test08.jpg', 'teacher', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test09', '1234', '강사3', 'test09@gmail.com', 'test09.jpg', 'teacher', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test10', '1234', '강사4', 'test10@gmail.com', 'test10.jpg', 'teacher', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test11', '1234', '예비1', 'test11@gmail.com', 'test11.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test12', '1234', '예비2', 'test12@gmail.com', 'test12.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test13', '1234', '학생7', 'test13@gmail.com', 'test13.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test14', '1234', '학생8', 'test14@gmail.com', 'test14.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test15', '1234', '학생9', 'test15@gmail.com', 'test15.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test16', '1234', '학생10', 'test16@gmail.com', 'test16.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test17', '1234', '학생11', 'test17@gmail.com', 'test17.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test18', '1234', '학생12', 'test18@gmail.com', 'test18.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test19', '1234', '학생13', 'test19@gmail.com', 'test19.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test20', '1234', '학생14', 'test20@gmail.com', 'test20.png', 'student', NOW());
-- test06, test07: 강사 신청폼만 낸 상태 Teachers데이터X
INSERT INTO apply_teacher (apply_id, phone_num, hope_field, intro, pf_link, apply_rege, approve) VALUES ('test11', '010-1234-1234', '컴퓨터과학 기초', '안녕하세요. 컴퓨터 과학을 전공하고 있는 대학원생입니다.', 'https://github.com/', NOW(), 'false');
INSERT INTO apply_teacher (apply_id, phone_num, hope_field, intro, pf_link, apply_rege, approve) VALUES ('test12', '010-1234-1111', 'python', '안녕하십니까. 파이썬을 보다 쉽게 배우는 법을 공유하고 싶어 지원합니다.', 'https://github.com/', NOW(), 'false');
-- apply_teacher test04, test05: 강사, teachers 데이터O
INSERT INTO apply_teacher (apply_id, phone_num, hope_field, intro, pf_link, apply_rege, approve) VALUES ('test07', '010-1234-2222', 'java', '안녕하세요. 남궁성입니다.', 'https://www.youtube.com/embed//oJlCC1DutbA', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_field, intro, pf_link, apply_rege, approve) VALUES ('test08', '010-1234-3333', '프론트엔드', '안녕하세요. 니꼴라스입니다.', 'https://www.youtube.com/embed//cNfpkKUYAyo', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_field, intro, pf_link, apply_rege, approve) VALUES ('test09', '010-1234-4444', 'spring', '안녕하세요. 김영한입니다.', 'https://www.youtube.com/embed//ZgtvcyH58ys', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_field, intro, pf_link, apply_rege, approve) VALUES ('test10', '010-1234-5555', '프론트엔드, 깃허브', '안녕하세요. 엘리입니다.', 'https://www.youtube.com/embed/X91jsJyZofw', NOW(), 'true');
-- teachers
-- test07
INSERT INTO teachers (phone_num, hope, intro, pf_link, apply_id) VALUES ('010-1234-2222', 'java', '안녕하세요. 남궁성입니다.', 'https://www.youtube.com/embed//oJlCC1DutbA', 3);
-- test08
INSERT INTO teachers (phone_num, hope, intro, pf_link, apply_id) VALUES ('010-1234-3333', '프론트엔드', '안녕하세요. 니꼴라스입니다.', 'https://www.youtube.com/embed//cNfpkKUYAyo', 4);
-- test09
INSERT INTO teachers (phone_num, hope, intro, pf_link, apply_id) VALUES ('010-1234-4444', 'spring', '안녕하세요. 김영한입니다.', 'https://www.youtube.com/embed//qyGjLVQ0Hog', 5);
-- test10
INSERT INTO teachers (phone_num, hope, intro, pf_link, apply_id) VALUES ('010-1234-5555', '프론트엔드', '안녕하세요. 엘리입니다.', 'https://www.youtube.com/embed/X91jsJyZofw', 6);
-- board
-- 공지사항 게시글
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('admin', 'notice', '공지사항입니다.', '공지', NOW(), '첫번째 공지입니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('admin', 'notice', '긴급공지입니다.', '공지', NOW(), '두번째 공지입니다. 서버 점검이 있겠습니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('admin', 'notice', '이벤트입니다.', '이벤트', NOW(), '이벤트 소식입니다. 일주일동안 진행됩니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('admin', 'notice', '두번째 이벤트입니다.', '이벤트', NOW(), '두번째 이벤트입니다. 일주일동안 진행됩니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('admin', 'notice', '공지입니다.', '공지', NOW(), '세번째 공지입니다. 서버 점검이 있겠습니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('admin', 'notice', '서버 점검이 종료되었습니다.', '공지', NOW(), '서버 점검이 끝났습니다. 시스템 재개하였습니다', 0);
-- 커뮤니티 게시글
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test01', 'community', 'test01의 커뮤니티 글입니다.', '자유', NOW(), '커뮤니티 게시글 내용입니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test02', 'community', 'test02의 커뮤니티글입니다. ', '질문', NOW(), '멋진 개발자가 될 수 있을까요?', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test01', 'community', '자바 프로그래밍 초보 가이드', '질문', NOW(), '자바 초보자를 위한 가이드입니다. 어떻게 시작해야 할까요?', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test03', 'community', '어떤 상황에서 이런 오류가 발생하나요?', '질문', NOW(), '궁금한 게 있어서 문의드립니다', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test01', 'community', '다음 주 출시될 제품 소개', '정보', NOW(), '이번에 출시될 제품은 이런 특징을 가지고 있습니다.', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test07', 'community', 'JPA를 이용한 데이터베이스 관리', '정보', NOW(), 'JPA를 사용하여 데이터베이스를 관리하는 방법을 알려드립니다.', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test05', 'community', '코딩 초보를 위한 자바스크립트 공부법', '정보', NOW(), '자바스크립트를 처음 공부하는 분들을 위한 팁입니다. 우선 기본 문법부터 천천히 익혀보세요.', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test02', 'community', '파이썬 질문', '질문', NOW(), '파이썬에서 문자열을 뒤집는 방법에 대해 알려주세요.', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test06', 'community', '파이썬으로 데이터 분석하기', '정보', NOW(), '파이썬을 이용하여 데이터를 분석하는 방법에 대해 알아봅니다.', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test03', 'community', '스프링 부트로 웹 애플리케이션 만들기', '질문', NOW(), '스프링 부트와 JPA를 이용하여 웹 애플리케이션을 만들어보려고 합니다. 참고할 만한 자료가 있을까요?', 0);
INSERT INTO Board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test04', 'community', 'JPA로 데이터베이스 연동하기', '질문', NOW(), 'JPA를 이용하여 데이터베이스와 연동하는 방법에 대해 공부하고 있습니다. JPA의 장단점과 사용법을 알고 싶습니다.', 0);
-- 커뮤니티 댓글
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (8, 'test02', '1등', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (8, 'test03', '허허', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (9, 'test05', '가입축하합니다ㅎㅎ', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (9, 'test01', '감사합니다', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (9, 'test04', '친하게 지내요~', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (10, 'test07', '같이 공부해요!', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (11, 'test11', '많은관심 감사합니다', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (12, 'test11', '저도 궁금해요', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (12, 'test12', '공부 너무 어려워요~', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (12, 'test02', '좋은 정보 감사합니다', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (13, 'test03', '글 잘 읽었습니다', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (14, 'test04', '글을 정말 잘 쓰시네요', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (14, 'test05', '좋은 글입니다.', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (15, 'test08', '좋은 정보입니다.', NOW());
INSERT INTO comment (com_no, mem_id, cmt_content, cmt_regdate) VALUES (16, 'test09', '공부하는데 많은 도움이 되었어요.', NOW());
-- 좋아요
INSERT INTO likes (com_no, mem_id) VALUES (7, 'test02');
INSERT INTO likes (com_no, mem_id) VALUES (7, 'test03');
INSERT INTO likes (com_no, mem_id) VALUES (8, 'test04');
INSERT INTO likes (com_no, mem_id) VALUES (9, 'test05');
INSERT INTO likes (com_no, mem_id) VALUES (9, 'test06');
INSERT INTO likes (com_no, mem_id) VALUES (9, 'test08');
INSERT INTO likes (com_no, mem_id) VALUES (10, 'test07');
INSERT INTO likes (com_no, mem_id) VALUES (12, 'test07');
INSERT INTO likes (com_no, mem_id) VALUES (12, 'test02');
INSERT INTO likes (com_no, mem_id) VALUES (13, 'test03');
INSERT INTO likes (com_no, mem_id) VALUES (14, 'test01');
INSERT INTO likes (com_no, mem_id) VALUES (14, 'test09');
INSERT INTO likes (com_no, mem_id) VALUES (15, 'test11');
INSERT INTO likes (com_no, mem_id) VALUES (15, 'test13');
-- 강사1의 강의(남궁성)
INSERT INTO lectures (teacher_no, title, image, price, cnt, regdate, info, description, apply_cnt) VALUES (1, '자바의 정석', '자바의 정석.jpg', 5000, 10, NOW(), '자바의 정석입니다.', '자바의 역사부터 기본문법, 객체지개념까지 다룹니다.', 2);
-- 강사2의 강의(니꼴라스)
INSERT INTO lectures (teacher_no, title, image, price, cnt, regdate, info, description, apply_cnt) VALUES (2, '무료로 듣는 HTML', '무료로 듣는 HTML.jpg', 0, 2, NOW(), '무료로 듣는 HTML입니다!', '니꼴라스와 함께 HTML입문해봐요~', 4);
-- 강사3의 강의(김영한)
INSERT INTO lectures (teacher_no, title, image, price, cnt, regdate, info, description, apply_cnt) VALUES (3, '스프링 입문강의', '스프링 입문강의.png', 6000, 11, NOW(), '스프링 입문을 보다 쉽게', '스프링 입문자 분들을 위해 모모런에서 진행하는 저렴한 스프링 강의 입니다.', 3);
-- 강사4의 강의(엘리)
INSERT INTO lectures (teacher_no, title, image, price, cnt, regdate, info, description, apply_cnt) VALUES (4, '깃허브 입문하기', '깃허브 입문하기.png', 2000, 4, NOW(), '깃허브의 기본을 알려드립니다.', '요즘 현업에서 필수인 깃과 깃허브 개념을 속성으로 정리해드립니다. ', 4);

-- category
INSERT INTO category (cate_name) VALUES ('JAVA');
INSERT INTO category (cate_name) VALUES ('HTML');
INSERT INTO category (cate_name) VALUES ('JSP');
INSERT INTO category (cate_name) VALUES ('SPRING');
INSERT INTO category (cate_name) VALUES ('입문');
INSERT INTO category (cate_name) VALUES ('GIT');
-- category_lecture
-- 강의1의 카테고리 : JAVA
INSERT INTO category_lecture (lecture_id, category_id) VALUES (1, 1);
INSERT INTO category_lecture (lecture_id, category_id) VALUES (1, 5);
-- 강의2의 카테고리: HTML
INSERT INTO category_lecture (lecture_id, category_id) VALUES (2, 2);
INSERT INTO category_lecture (lecture_id, category_id) VALUES (2, 5);
-- 강의3의 카테고리: HTML
INSERT INTO category_lecture (lecture_id, category_id) VALUES (3, 1);
INSERT INTO category_lecture (lecture_id, category_id) VALUES (3, 4);
INSERT INTO category_lecture (lecture_id, category_id) VALUES (3, 5);
-- 강의4의 카테고리: HTML
INSERT INTO category_lecture (lecture_id, category_id) VALUES (4, 5);
INSERT INTO category_lecture (lecture_id, category_id) VALUES (4, 6);
-- courses
-- 강의1의 강좌 10개
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '1강.자바란? 자바의 역사', '00:12:45', 'https://www.youtube.com/embed//oJlCC1DutbA');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '2강.자바의 특징', '00:07:32', 'https://www.youtube.com/embed//J1xJhrr63VY');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '3강.자바 개발도구 설치-Windows', '00:14:57', 'https://www.youtube.com/embed//L8mGi7-q6j4');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '4강.자바 개발도구 설치-MacOS', '00:12:24', 'https://www.youtube.com/embed//XTKnmmfJqms');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '5강.자바 API문서 설치와 사용법', '00:06:03', 'https://www.youtube.com/embed//C3P1umV-NOI');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '6강.첫 번째 자바프로그램 작성', '00:13:20', 'https://www.youtube.com/embed//E8fTLFuc7X4');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '7강.이클립스 설치하고 개발하기', '00:17:36', 'https://www.youtube.com/embed//emllFzqD1-0');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '8강.이클립스 단축키, 자동완성기능, 주석', '00:13:42', 'https://www.youtube.com/embed//UrbO_1sijvs');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '9강.화면에 출력, 덧셈 뺄셈 계산하기', '00:12:26', 'https://www.youtube.com/embed//C163_91Ohic');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '10강.변수란? 변수의 선언과 저장', '00:11:19', 'https://www.youtube.com/embed//yjRnG1iju1U');
-- 강의2의 강좌 2개
INSERT INTO courses (lecture_id, title, time, url) VALUES (2, '1강.HTML 로 코딩하기', '00:03:36', 'https://www.youtube.com/embed//cNfpkKUYAyo');
INSERT INTO courses (lecture_id, title, time, url) VALUES (2, '2강.개발자 99%가 모르는 신박한 HTML 태그 5개', '00:08:21', 'https://www.youtube.com/embed//EMOlLLTAZMs');
-- 강의3의 강좌 11개
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '0강.강의 소개', '00:05:01', 'https://www.youtube.com/embed//qyGjLVQ0Hog');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '1강.프로젝트 생성', '00:16:29', 'https://www.youtube.com/embed//NNClHeXzIBA');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '2강.라이브러리 살펴보기', '00:12:51', 'https://www.youtube.com/embed//ymmGSVUwTDM');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '3강. View 환경설정', '00:14:08', 'https://www.youtube.com/embed//P6AgXuh-fxA');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '4강. 빌드 및 실행', '00:03:36', 'https://www.youtube.com/embed//SSzsqDeS2JI');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '5강. 정적 컨텐츠', '00:06:31', 'https://www.youtube.com/embed//yZVTnaudGXk');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '6강. MVC와 템플릿 엔진', '00:10:32', 'https://www.youtube.com/embed//H8LG-GncT94');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '7강. API', '00:15:57', 'https://www.youtube.com/embed//ec1jW_jBCmI');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '8강. 비즈니스 요구사항 정리', '00:04:53', 'https://www.youtube.com/embed//0_oeeYDLSjM');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '9강. 회원 도메인과 리포지토리만들기', '00:08:27', 'https://www.youtube.com/embed//Gul8sv7cf8g');
INSERT INTO courses (lecture_id, title, time, url) VALUES (3, '10강. 회원 도메인과 리포지토리테스트', '00:16:25', 'https://www.youtube.com/embed//OmcCT0bU4Kk');
-- 강의4의 강좌 
INSERT INTO courses (lecture_id, title, time, url) VALUES (4, '1강. 깃, 깃허브 이건 알고 시작하자', '00:06:49', 'https://www.youtube.com/embed//lPrxhA4PLoA');
INSERT INTO courses (lecture_id, title, time, url) VALUES (4, '2강. 깃허브 계정 제대로 꾸미기', '00:11:22', 'https://www.youtube.com/embed//w9DfC2BHGPA');
INSERT INTO courses (lecture_id, title, time, url) VALUES (4, '3강. 깃, 깃허브 제대로 배우기', '00:47:13', 'https://www.youtube.com/embed//Z9dvM7qgN9s');
INSERT INTO courses (lecture_id, title, time, url) VALUES (4, '4강. 깃허브 액션이란?', '00:12:11', 'https://www.youtube.com/embed//iLqGzEkusIw');
-- cart
INSERT INTO cart(member_id, lecture_id) VALUES('test13', 1);
INSERT INTO cart(member_id, lecture_id) VALUES('test13', 2);
INSERT INTO cart(member_id, lecture_id) VALUES('test14', 2);
INSERT INTO cart(member_id, lecture_id) VALUES('test15', 1);
INSERT INTO cart(member_id, lecture_id) VALUES('test15', 2);
INSERT INTO cart(member_id, lecture_id) VALUES('test15', 3);
INSERT INTO cart(member_id, lecture_id) VALUES('test15', 4);
-- mylectures
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test01', 1, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test01', 2, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test01', 3, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test01', 4, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test02', 1, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test02', 2, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test02', 3, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test03', 2, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test03', 3, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test03', 4, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test04', 2, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test04', 4, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test05', 4, NOW());
