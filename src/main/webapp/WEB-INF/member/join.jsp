<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<title>회원가입 페이지</title>

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>
   
	<link href="${pageContext.request.contextPath}/css/join.css" rel="stylesheet">

	<jsp:include page="/separate/head.jsp"></jsp:include>

</head>
<body>

    <jsp:include page="/separate/header4.jsp"></jsp:include>

	<div class="wrap">
		
			<div class="join">
				<h2 class="text-primary">회원가입</h2>
				<span  style="font-weight: bold; color: navy;">* 필수입력</span>
				<!-- 파일업로드를 먼저 해주기 위함 -->
				<form name="f" action="${pageContext.request.contextPath}/member/join" method="post" enctype="multipart/form-data">
					<div class="join_a">
						<h4>*아이디</h4>
						<input type="text" name="memId" id="memId" placeholder="아이디를 입력해주세요."
							minlength="5" maxlength="20" onsubmit="return checkId(this)">
						<input type="button" style="background-color: #78d5e7; color: white; font-weight: bolder;" value="ID중복확인" onclick="dedupId()" >
					</div>
					<div class="join_a">
						<h4>*비밀번호</h4>
						<input type="password"  name="password" placeholder="비밀번호를 입력해주세요."
							maxlength="16">
					</div>
					<div class="join_a">
						<h4>*이메일</h4>
						<input type="text" name="email" placeholder="이메일을 입력해주세요."
							maxlength="100">
					</div>
					<!-- <div class="join_grade">
						<h4>등급</h4>
						<label>
							<input type="radio" name="grade" value="student" checked>학생 &nbsp;&nbsp;
						</label> 
						<label>
							<input type="radio" name="grade" value="teacher">강사 &nbsp;&nbsp;
						</label>
						<label>
							<input type="radio" name="grade" value="amdin">관리자 
						</label>
					</div> -->
					<div class="join_a">
						<h4>*이름</h4>
						<input type="text" name="name" placeholder="이름을 입력해주세요."
							minlength="1" maxlength="5">
					</div>
					<!--사진 업로드: https://getbootstrap.com/docs/5.3/forms/form-control/?#file-input-->
					<br>
					<div class="">
						<h4>*프로필사진</h4>
						<div class="mb-3">
							<input class="form-control" type="file" id="profile" name="file">
						</div>
					</div>
					<div class="submit">
						<input type="button" style="background-color: #78d5e7;" value="회원가입" onclick="blank()">
					</div>

					<div class="text">
						<p id="p">sns계정으로 시작하기</p>
					</div>
					<div class="join_sns">
						<li><a href="" id="a"><i class="fa-sharp fa-solid fa-n"></i></a></li>
						<li><a href="" id="a"><i class="fa-solid fa-comment"></i></a></li>
					</div>

				</form>
			</div>
		
	</div>
	
	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
    
    <script>
    
    var check = false; // 중복 체크 여부 확인 
    
	//id 중복체크
	function dedupId() {
		
		
		axios.post('${pageContext.request.contextPath}/member/checkOk', {}, {
			params : {
				memId : document.getElementById("memId").value
			}
		})
		 .then(function (resData) {
			 validate(resData['data']);
		 })
	}
    
	//사용여부
	function validate(val) {
		const memId = document.getElementById("memId").value;
		
	
		if (memId.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			
		}else{
			if(memId == ""){
				alert('아이디를 입력하세요.');
				return;
				
			}else if(val == true) {
				alert('사용가능한 아이디 입니다.');
				check = true;
				
			}else {
				alert('이미 존재하는 아이디 입니다.');
				check = false;
			}
		}
		
	}

	
	//입력 태그 null값 경고
	function blank() {
		//아이디
		if(f.memId.value==""){
			alert("아이디를 입력하세요..");
			f.memId.focus();
			return false;
		}
		//비밀번호
		if(f.password.value==""){
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		}
		
		//이메일
		if(f.email.value==""){
			alert("이메일을 입력하세요.");
			f.email.focus();
			return false;
		}
		//이름
		if(f.name.value==""){
			alert("이름을 입력하세요.");
			f.name.focus();
			return false;
		}
		
		//중복 체크 확인
		if(check == false){
			alert("아이디 중복확인 해주세요.");
		}
		
		if(check == true){
			f.submit();
		}
		
	}
	</script>

</body>
</html>