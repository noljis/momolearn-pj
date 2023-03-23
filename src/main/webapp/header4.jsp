<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
session.setAttribute("id", "test04");
session.setAttribute("name", "강사1");
session.setAttribute("profile", "test04.jpg");
session.setAttribute("grade", "teacher");
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MOMOLEARN</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../lib/animate/animate.min.css" rel="stylesheet">
    <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
    
    <style type="text/css">
    nav div {
    	text-align:right;
    }
    </style>
</head>
<body>
    <!-- Spinner Start -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary shadow">
        <a href="../index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>MOMOLEARN</h2>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
                <!-- <a href="index.html" class="nav-item nav-link active">홈</a> -->
       
            </div>
            <a href="../page/lecture/lectures.html" class="nav-item nav-link"></a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="../page/community/community.html" class="nav-item nav-link"></a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="../page/notice/notice.html" class="nav-item nav-link"></a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="../member/login.jsp" class="btn btn-primary">로그인</a>&nbsp;&nbsp;&nbsp;
            <a href="../member/join.jsp" class="btn btn-primary">가입하기</a>&nbsp;&nbsp;&nbsp;
       
        </div>
    </nav>
    <!-- Navbar End -->

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="../lib/wow/wow.min.js"></script>
<script src="../lib/easing/easing.min.js"></script>
<script src="../lib/waypoints/waypoints.min.js"></script>
<script src="../lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="../js/main.js"></script>
<!-- axios 사용을 위한 추가 설정 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="../js/search.js"></script>

</body>
</html>