--members 
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
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test11', '010-1234-1234', '컴퓨터과학 기초', '안녕하세요. 컴퓨터 과학을 전공하고 있는 대학원생입니다.', 'https://github.com/', NOW(), 'false');
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test12', '010-1234-1111', 'python', '안녕하십니까. 파이썬을 보다 쉽게 배우는 법을 공유하고 싶어 지원합니다.', 'https://github.com/', NOW(), 'false');
-- apply_teacher test04, test05: 강사, teachers 데이터O
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test07', '010-1234-2222', 'java', '안녕하세요. 남궁성입니다.', 'https://www.youtube.com/embed//oJlCC1DutbA', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test08', '010-1234-3333', '프론트엔드', '안녕하세요. 니꼴라스입니다.', 'https://www.youtube.com/embed//cNfpkKUYAyo', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test09', '010-1234-4444', 'spring', '안녕하세요. 김영한입니다.', 'https://www.youtube.com/embed//ZgtvcyH58ys', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test10', '010-1234-5555', '프론트엔드, 깃허브', '안녕하세요. 엘리입니다.', 'https://www.youtube.com/embed/X91jsJyZofw', NOW(), 'true');
--teachers
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
-- 커뮤니티 게시글
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test01', 'community', 'test01의 커뮤니티 글입니다.', '자유', NOW(), '커뮤니티 게시글 내용입니다.', 0);
INSERT INTO board (mem_id, type, com_title, subject, com_regdate, com_content, com_view_count) VALUES ('test02', 'community', 'test02의 커뮤니티글입니다. ', '질문', NOW(), '멋진 개발자가 될 수 있을까요?', 0);
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
-- 강의3의 강좌 28개
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