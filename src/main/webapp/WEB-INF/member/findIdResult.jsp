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
	    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/find.css">

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>

    <jsp:include page="/separate/head.jsp"></jsp:include>
    
</head>
<body>
	<jsp:include page="/separate/header4.jsp"></jsp:include>

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
				<input class="btn btn-primary" type="button" value="뒤로가기" onclick="history.back()">
				<input class="btn btn-primary" type="button" value="메인화면" onclick="location.href='${pageContext.request.contextPath}/'">
			</div>
		</div>
	</div>

	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>

</body>
</html>