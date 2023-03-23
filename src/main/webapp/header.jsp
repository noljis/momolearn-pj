<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
session.setAttribute("id", "test04");
session.setAttribute("name", "강사1");
session.setAttribute("profile", "test04.jpg");
session.setAttribute("grade", "teacher");
%>
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
</head>
<body>
<!-- Spinner Start -->
<div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
        <span class="sr-only">Loading...</span>
    </div>
</div>
<!-- Spinner End -->


<!-- Navbar Start navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0-->
<nav class="navbar navbar-expand-lg bg-body-tertiary shadow">
    <a href="${pageContext.request.contextPath}/page/main.jsp" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>MOMOLEARN</h2>
    </a>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
            <!-- <a href="index.html" class="nav-item nav-link active">홈</a> -->
          <input id="searchLecture" class="form-control me-2" type="search" placeholder="강의 검색" aria-label="Search">
          <button id="btn" class="btn btn-outline-primary" type="submit">Search</button>
        </div>
        <a href="../page/lecture/lectures.jsp" class="nav-item nav-link">강의</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/board/" class="nav-item nav-link">커뮤니티</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/notice/" class="nav-item nav-link">공지사항</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 세션ID 존재할 경우 나오는 메뉴 Start-->
        <a href="" class="nav-item nav-link"><i class="fa fa-shopping-cart" style="font-size:24px;color:#06BBCC"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <div class="nav-item dropdown">
        <a href="" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
            <img class="profile-image rounded-circle" src="${pageContext.request.contextPath}/img/profile/${sessionScope.profile}" style="width: 50px; height: 50px; border: 2px solid #06BBCC;">&nbsp;&nbsp;${sessionScope.name}</a>
        <div class="dropdown-menu fade-down">
            <a href="" class="dropdown-item">내 정보</a>
            <a href="" class="dropdown-item">내 강의</a>
            <c:choose>
                <c:when test="${sessionScope.grade == 'admin'}">
                    <a href="" class="dropdown-item">관리자메뉴</a>
                </c:when>
                <c:when test="${sessionScope.grade == 'teacher'}">
                    <a href="${pageContext.request.contextPath}/lectures/uploadcheck" class="dropdown-item">강의 등록</a>
                </c:when>
            </c:choose>
            <a href="" class="dropdown-item">로그아웃</a>
        </div>
        <!-- 세션ID 존재할 경우 나오는 메뉴 End-->

            
        </div>
        <!-- <a href="login.html" class="btn btn-primary">로그인</a>&nbsp;&nbsp;&nbsp;
        <a href="join.html" class="btn btn-primary">가입하기</a>&nbsp;&nbsp;&nbsp; -->
    </div>
</nav>

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