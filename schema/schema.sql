--members 
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('admin', 'admin', '관리자', 'admin@gmail.com', 'admin.jpg', 'admin', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test01', '1234', '학생1', 'test01@gmail.com', 'test01.jpg', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test02', '1234', '학생2', 'test02@gmail.com', 'test02.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test03', '1234', '학생3', 'test03@gmail.com', 'test03.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test04', '1234', '강사1', 'test04@gmail.com', 'test04.jpg', 'teacher', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test05', '1234', '강사2', 'test05@gmail.com', 'test05.jpg', 'teacher', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test06', '1234', '예비1', 'test06@gmail.com', 'test06.png', 'student', NOW());
INSERT INTO members (mem_id, pw, name, email, profile, grade, regdate) VALUES ('test07', '1234', '예비2', 'test07@gmail.com', 'test07.png', 'student', NOW());
-- test06, test07: 강사 신청폼만 낸 상태 Teachers데이터X
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test06', '010-1234-1234', '컴퓨터과학 기초', '안녕하세요. 컴퓨터 과학을 전공하고 있는 대학원생입니다.', 'https://github.com/', NOW(), 'false');
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test07', '010-1234-1111', 'python', '안녕하십니까. 파이썬을 보다 쉽게 배우는 법을 공유하고 싶어 지원합니다.', 'https://github.com/', NOW(), 'false');
-- apply_teacher test04, test05: 강사, teachers 데이터O
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test04', '010-1234-2222', 'java', '안녕하세요. 남궁성입니다.', 'https://youtu.be/oJlCC1DutbA', NOW(), 'true');
INSERT INTO apply_teacher (apply_id, phone_num, hope_filed, intro, pf_link, apply_rege, approve) VALUES ('test05', '010-1234-3333', '프론트엔드', '안녕하세요. 니꼴라스입니다.', 'https://youtu.be/cNfpkKUYAyo', NOW(), 'true');
--teachers
-- test04
INSERT INTO teachers (phone_num, hope, intro, pf_link, apply_id) VALUES ('010-1234-2222', 'java', '안녕하세요. 남궁성입니다.', 'https://youtu.be/oJlCC1DutbA', 3);
-- test05
INSERT INTO teachers (phone_num, hope, intro, pf_link, apply_id) VALUES ('010-1234-3333', '프론트엔드', '안녕하세요. 니꼴라스입니다.', 'https://youtu.be/cNfpkKUYAyo', 4);
-- board
-- 공지사항 게시글
INSERT INTO board (mem_id, type, comTitle, subject, comRegdate, comContent, comViewCount) VALUES ('admin', 'notice', '공지사항입니다.', '공지', NOW(), '첫번째 공지입니다.', 0);
INSERT INTO board (mem_id, type, comTitle, subject, comRegdate, comContent, comViewCount) VALUES ('admin', 'notice', '긴급공지입니다.', '공지', NOW(), '두번째 공지입니다. 서버 점검이 있겠습니다.', 0);
-- 커뮤니티 게시글
INSERT INTO board (mem_id, type, comTitle, subject, comRegdate, comContent, comViewCount) VALUES ('test01', 'community', 'test01의 커뮤니티 글입니다.', '자유', NOW(), '커뮤니티 게시글 내용입니다.', 0);
INSERT INTO board (mem_id, type, comTitle, subject, comRegdate, comContent, comViewCount) VALUES ('test02', 'community', 'test02의 커뮤니티글입니다. ', '질문', NOW(), '멋진 개발자가 될 수 있을까요?', 0);
-- 강사1의 강의
INSERT INTO lectures (teacher_no, title, image, price, cnt, regdate, info, description, apply_cnt) VALUES (1, '자바의 정석', '자바의 정석.jpg', 5000, 10, NOW(), '자바의 정석입니다.', '자바의 역사부터 기본문법, 객체지개념까지 다룹니다.', 0);
-- 강사2의 강의
INSERT INTO lectures (teacher_no, title, image, price, cnt, regdate, info, description, apply_cnt) VALUES (2, '무료로 듣는 HTML', '무료로 듣는 HTML.jpg', 0, 2, NOW(), '무료로 듣는 HTML입니다!', '니꼴라스와 함께 HTML입문해봐요~', 0);
-- category
INSERT INTO category (cate_name) VALUES ('JAVA');
INSERT INTO category (cate_name) VALUES ('HTML');
INSERT INTO category (cate_name) VALUES ('JSP');
INSERT INTO category (cate_name) VALUES ('SPRING');
-- category_lecture
-- 강의1의 카테고리 : JAVA
INSERT INTO category_lecture (lecture_id, category_id) VALUES (1, 1);
-- 강의2의 카테고리: HTML
INSERT INTO category_lecture (lecture_id, category_id) VALUES (2, 2);
-- courses
-- 강의1의 강좌 10개
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '1강.자바란? 자바의 역사', '00:12:45', 'https://youtu.be/oJlCC1DutbA');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '2강.자바의 특징', '00:07:32', 'https://youtu.be/J1xJhrr63VY');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '3강.자바 개발도구 설치-Windows', '00:14:57', 'https://youtu.be/L8mGi7-q6j4');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '4강.자바 개발도구 설치-MacOS', '00:12:24', 'https://youtu.be/XTKnmmfJqms');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '5강.자바 API문서 설치와 사용법', '00:06:03', 'https://youtu.be/C3P1umV-NOI');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '6강.첫 번째 자바프로그램 작성', '00:13:20', 'https://youtu.be/E8fTLFuc7X4');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '7강.이클립스 설치하고 개발하기', '00:17:36', 'https://youtu.be/emllFzqD1-0');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '8강.이클립스 단축키, 자동완성기능, 주석', '00:13:42', 'https://youtu.be/UrbO_1sijvs');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '9강.화면에 출력, 덧셈 뺄셈 계산하기', '00:12:26', 'https://youtu.be/C163_91Ohic');
INSERT INTO courses (lecture_id, title, time, url) VALUES (1, '10강.변수란? 변수의 선언과 저장', '00:11:19', 'https://youtu.be/yjRnG1iju1U');
-- 강의2의 강좌 2개
INSERT INTO courses (lecture_id, title, time, url) VALUES (2, '1강.HTML 로 코딩하기', '00:03:36', 'https://youtu.be/cNfpkKUYAyo');
INSERT INTO courses (lecture_id, title, time, url) VALUES (2, '2강.개발자 99%가 모르는 신박한 HTML 태그 5개', '00:08:21', 'https://youtu.be/EMOlLLTAZMs');
-- cart
INSERT INTO cart(member_id, lecture_id) VALUES('test01', 1);
INSERT INTO cart(member_id, lecture_id) VALUES('test01', 2);
INSERT INTO cart(member_id, lecture_id) VALUES('test02', 2);
INSERT INTO cart(member_id, lecture_id) VALUES('test03', 1);
-- mylectures
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test02', 1, NOW());
INSERT INTO my_lectures(member_id, lecture_id, lec_rege) VALUES('test03', 2, NOW());