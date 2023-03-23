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
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
	<link href="../../css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="../../css/find.css">

	<!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
	rel="stylesheet">   -->

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="../../header4.jsp"></jsp:include>

	<div class="wrap">
		<div class="find">
			<c:choose>
				<c:when test="${not empty members}">
					찾으시는 아이디는 <b>${members.memId}</b> 입니다.<br>
				</c:when>
				<c:when test="${empty members}">
					찾으시는 아이디가 없습니다.<br>
				</c:when>
			</c:choose>
			<div class="box" >
				<input type="button" value="뒤로가기" onclick="history.back()">
				<input type="button" value="메인화면" onclick="location.href='${pageContext.request.contextPath}/page/index.html'">
			</div>
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
	
</body>
</html>