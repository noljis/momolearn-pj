<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
        <span class="sr-only">Loading...</span>
    </div>
</div>
<!-- Spinner End -->


<!-- Navbar Start navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0-->
<nav class="navbar navbar-expand-lg bg-body-tertiary shadow">

    <a href="${pageContext.request.contextPath}/" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
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
        <a href="${pageContext.request.contextPath}/lectures/categoryall" class="nav-item nav-link">강의</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/board/" class="nav-item nav-link">커뮤니티</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/notice/" class="nav-item nav-link">공지사항</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 세션ID 존재할 경우 나오는 메뉴 Start-->
       <c:if test="${not empty sessionScope.members.memId}">
        <a href="" class="nav-item nav-link"><i class="fa fa-shopping-cart" style="font-size:24px;color:#06BBCC"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <div class="nav-item dropdown">
        <a href="" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
            <img class="profile-image rounded-circle" src="${pageContext.request.contextPath}/img/profile/${sessionScope.members.profile}" style="width: 50px; height: 50px; border: 2px solid #06BBCC;">&nbsp;&nbsp;${sessionScope.members.name}</a>
	        <div class="dropdown-menu fade-down">
	            <a href="${pageContext.request.contextPath}/member/myinfo" class="dropdown-item">내 정보</a>
	            <a href="" class="dropdown-item">내 강의</a>
	            <c:choose>
	            	<c:when test="${sessionScope.members.grade == 'student'}">
                        <a href="${pageContext.request.contextPath}/teacher/applyform" class="dropdown-item">강사 신청</a>
                    </c:when>
	                <c:when test="${sessionScope.members.grade == 'admin'}">
	                    <a href="" class="dropdown-item">관리자메뉴</a>
	                </c:when>
	                <c:when test="${sessionScope.members.grade == 'teacher'}">
	                    <a href="${pageContext.request.contextPath}/lectures/uploadcheck" class="dropdown-item">강의 등록</a>
	                </c:when>
	            </c:choose>
	            <a href="${pageContext.request.contextPath}/member/sessionOut" class="dropdown-item">로그아웃</a>
	        </div>
        </div>
        </c:if>
        <!-- 세션ID 존재할 경우 나오는 메뉴 End-->
        <!-- 세션ID 존재하지 않을때 나오는 메뉴 Start -->
        <c:if test="${empty sessionScope.members.memId}">
		 <a href="${pageContext.request.contextPath}/member/loginView" class="btn btn-primary">로그인</a>&nbsp;&nbsp;&nbsp;
         <a href="${pageContext.request.contextPath}/member/joinView" class="btn btn-primary">가입하기</a>&nbsp;&nbsp;&nbsp;
         </c:if>
         <!-- 세션ID 존재하지 않을때 나오는 메뉴 End --> 
    </div>
</nav>
<!-- axios 사용을 위한 추가 설정 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select.js"></script>