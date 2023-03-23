<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>강의등록</title>
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
	<link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
	
	<!-- Customized Bootstrap Stylesheet -->
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Template Stylesheet -->
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<style>
      body {
        min-height: 100vh;
  
        background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
        background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
        background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
        background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
        background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
      }
  
      .input-form {
        max-width: 680px;
  
        margin-top: 80px;
        padding: 32px;
  
        background: #fff;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
      }
    </style>
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
	    <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
	        <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>MOMOLEARN</h2>
	    </a>
	    <div class="collapse navbar-collapse" id="navbarCollapse">
	        <div class="navbar-nav ms-auto p-4 p-lg-0">
	        </div>
	        <!-- 세션ID 존재할 경우 나오는 메뉴 Start-->
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
	<!-- Navbar End -->
	<!-- form Start -->
    <div>
      <form name="upload" action="${pageContext.request.contextPath}/momolearn/lectures/uploadlecture" method="post" enctype="multipart/form-data">
        
    	<div class="container">
        <div class="input-form-backgroud row">
          <div class="input-form col-md-12 mx-auto">
            <h2 class="mb-2">강의 등록하기</h2>
            <form class="validation-form" novalidate>
              <div class="row">
                <div class="mb-3">
                  <label for="title">강의명</label>
                  <input type="text" class="form-control-plaintext form-control-sm border-bottom" name="title" placeholder="강의명을 입력해주세요." value="" required>
                </div>
              </div>
              <div class="mb-3">
                <label for="category">카테고리</label>
                <input type="text" class="form-control-plaintext form-control-sm border-bottom" name="category" id="category" placeholder="카테고리를 쉼표(,)로 구분하여 입력하세요.">
              </div>
              <div class="mb-3">
                <label for="teacher_no">강사명<br><h5>${member.name}</h5></label>
                <input type="hidden" class="form-control-plaintext form-control-sm border-bottom" name="teachersTeacherNo" value="${teacher.teacherNo}">
              </div>
              <div class="mb-3">
                <label for="image">강의 썸네일</label>
                <div class="mb-3">
                  <input class="form-control" name="image" type="file" id="image">
                </div>
              </div>
    
              <div class="mb-3 row">
                <label for="price" class="col-md-6 col-form-label">강의 가격</label>
                <div class="col-md-6 d-flex align-items-center">
                 <input type="text" class="form-control-plaintext form-control-sm border-bottom" id="price" placeholder="가격을 입력하세요." required>원
                </div>
              </div>
    
              <div class="mb-3">
                <label for="info">강의 한줄 설명<span class="text-muted">&nbsp;</span></label>
                <input type="text" class="form-control-plaintext form-control-sm border-bottom" id="info" placeholder="한줄로 강의를 어필하세요!">
              </div>

              <div class="mb-3">
                <label for="description">강의 상세설명<span class="text-muted">&nbsp;</span></label>
                <textarea class="form-control" id="description" rows="5" placeholder="강의에 대한 상세 설명을 적어주세요!"></textarea>
              </div>
              <hr class="mb-4">
              <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="aggrement" required>
                <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
              </div>
              <div class="mb-4"></div>
              <button class="btn btn-primary btn-lg btn-block" type="submit">강좌 등록하기</button>
            </form>
          </div>
        </div>
      </form>
    </div>
    <!-- form End -->
    
	<!-- Footer Start -->
	<div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
	<div class="container py-5">
	    <div class="row g-6">
	        <div class="col-lg col-md">
	            <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
	                <h2 class="m-0 text-primary"><i class="fa fa-book me-10"></i>MOMOLEARN</h2>
	            </a>
	            &nbsp;&nbsp;&nbsp;&nbsp;<a href="about.html" class="text-primary align-items-right">사이트소개<i class="fa fa-arrow-right ms-3"></i></a> 
	            <p class="mb-2"><i class="fa fa-map-marker-alt me-10"></i> 서울시 강남구 강남대로 420 역삼빌딩 1205호</p>
	            <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>02) 1234 - 5678</p>
	            <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@momolearn.com</p>
	            <div class="d-flex pt-2">
	                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
	                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
	                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
	                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="container">
	    <div class="copyright">
	        <div class="row">
	            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
	                &copy; <a class="border-bottom" href="#">Your Site Name</a><br> 
	                (주)모모랩 | 대표자: 김누구 | 사업자번호: 123-45-67890 <br>
	                개인정보보호책임자: 이누구 | 이메일: info@momolearn.com
	                주소:서울시 강남구 강남대로 420 역삼빌딩 1205호
	
	                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
	                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a><br><br>
	                Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
	            </div>
	            <div class="col-md-6 text-center text-md-end">
	            </div>
	        </div>
	    </div>
	</div>
	</div>
	<!-- Footer End -->
	
	
	<!-- Back to Top -->
	<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
	
	
	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>
	
	<!-- Template Javascript -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>

</html>