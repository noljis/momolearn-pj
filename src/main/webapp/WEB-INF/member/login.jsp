<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<title>로그인 페이지</title>

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <jsp:include page="/separate/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/separate/header4.jsp"></jsp:include>
	<!-- Header Start -->
    <div class="container-fluid py-4">
        <div class="container">
            <div class="row justify-content">
                <div class="col-lg-10">
                    <div class="display-3" style="font-size: 20px;">로그인</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->

	<div class="wrap">
		<div class="login">
			<h2 id="h2" class="text-primary" >로그인</h2>
			
			<form name="f" action="${pageContext.request.contextPath}/member/login" method="post">
			
			<input type="hidden" name="returnUrl" value="${param.returnUrl }"/><!--  -->
				
				<div class="login_id">
					<h5>아이디</h5>
					<input type="text" id="memId" name="memId" placeholder="아이디를 입력해주세요.">
				</div>
				
				<div class="login_pw">
					<h5>비밀번호</h5>
					<input class="input" type="password" name="password" placeholder="비밀번호를 입력해주세요.">
				</div>
				
				<div class="login_etc">
					<div class="checkbox">
						<a id="a" href="${pageContext.request.contextPath}/member/findIdView">아이디 찾기</a>
						&nbsp; <a>|</a> &nbsp;
						<a id="a" href="${pageContext.request.contextPath}/member/findPwdView">비밀번호 찾기</a>
					</div>
				</div>
				
				<div class="submit">
					<input type="button" style="background-color: #36cedb;" value="로그인" onclick="blank()" >
				</div>
				
				<div class="text">
					<p id="p">sns계정으로 시작하기</p>
				</div>
				
				<div class="login_sns">
					<li><a id="a2" href="https://kauth.kakao.com/oauth/authorize?client_id=b05da66ce6b812c049b788547193fbdc&redirect_uri=http://localhost/momolearn/member/kakaoLogin&response_type=code"><i class="fa-solid fa-comment"></i> 카카오로 로그인</a></li>
				</div>
				
				<div class="text">
					아직 회원이 아니신가요? &nbsp; <a href="${pageContext.request.contextPath}/member/joinView"> 회원가입 하러가기</a>
				</div>
				
			</form>
		</div>
	</div>

	<jsp:include page="/separate/footer.jsp"></jsp:include>
	<jsp:include page="/separate/script.jsp"></jsp:include>
	
	<script>	
	function blank() {
		
		if (f.memId.value == "") {
			
			alert("아이디를 입력하세요.");
			f.memId.focus();
			
			return false;
		}
		
		if (f.password.value == "") {
			
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			
			return false;
		}
		
		f.submit();

	}
	</script>
</body>
</html>