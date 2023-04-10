<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

    <!-- Spinner Start -->
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
       
            </div>
            <a href="" class="nav-item nav-link"></a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="" class="nav-item nav-link"></a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="" class="nav-item nav-link"></a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/member/loginView" class="btn btn-primary">로그인</a>&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/member/joinView" class="btn btn-primary">가입하기</a>&nbsp;&nbsp;&nbsp;
       
        </div>
    </nav>
    <!-- Navbar End -->
</html>