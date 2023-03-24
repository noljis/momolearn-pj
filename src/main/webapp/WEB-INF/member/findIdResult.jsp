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
<title>아이디 찾기</title>

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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
	<link href="${pageContext.request.contextPath}//css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/find.css">

	<!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
	rel="stylesheet">   -->

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="../../separate/header4.jsp"></jsp:include>

	<div class="wrap">
		<div class="find">
			<c:choose>
				<c:when test="${not empty member}">
					회원님의 아이디는 <b>${member.memId}</b> 입니다.<br>
				</c:when>
				<c:when test="${empty member}">
					존재하지 않는 아이디입니다.<br>
				</c:when>
			</c:choose>
			<div class="box" >
				<input type="button" value="뒤로가기" onclick="history.back()">
				<input type="button" value="메인화면" onclick="location.href='${pageContext.request.contextPath}/page/index.html'">
			</div>
		</div>
	</div>

	<jsp:include page="../../separate/footer.jsp"></jsp:include>

</body>
</html>