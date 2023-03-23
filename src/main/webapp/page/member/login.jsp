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

    <!-- Favicon -->
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
	
    <!-- Libraries Stylesheet -->
    <link href="../../lib/animate/animate.min.css" rel="stylesheet">
    <link href="../../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
	<link href="../../css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="../../css/login.css">

	<!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
	rel="stylesheet">   -->

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="../../header3.jsp"></jsp:include>

	<div class="wrap">
		<div class="login">
			<h2 id="h2" class="text-primary" >로그인</h2>
			<form name="f" action="${pageContext.request.contextPath}/member/login" method="post">
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
						<input type="checkbox"> 아이디 저장<br>
						<a id="a" href="member/findIdForm">아이디 찾기</a>
						&nbsp; <a>|</a> &nbsp;
						<a id="a" href="member/findPwdForm">비밀번호 찾기</a>
					</div>
				</div>
				<div class="submit">
					<input type="button" style="background-color: #36cedb;" value="로그인" onclick="blank()" >
				</div>
				<div class="text">
					<p id="p">sns계정으로 시작하기</p>
				</div>
				<div class="login_sns">
					<li><a id="a" href=""><i class="fa-sharp fa-solid fa-n"></i></a></li>
					<li><a id="a" href=""><i class="fa-solid fa-comment"></i></a></li>
				</div>
				<div class="text">
					아직 회원이 아니신가요? &nbsp; <a href="./join.jsp"> 회원가입 하러가기</a>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../../footer.jsp"></jsp:include>
	
	
	<!-- Back to Top -->
	<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../../lib/wow/wow.min.js"></script>
	<script src="../../lib/easing/easing.min.js"></script>
	<script src="../../lib/waypoints/waypoints.min.js"></script>
	<script src="../../lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="../../js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.0/axios.min.js"></script>
	
	<script>	
	function blank() {
		//아이디 빈칸이라면 경고
		if (f.memId.value == "") {
			alert("아이디를 입력하세요.");
			f.memId.focus();
			return false;

		}
		//비밀번호 빈칸이라면 경고
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