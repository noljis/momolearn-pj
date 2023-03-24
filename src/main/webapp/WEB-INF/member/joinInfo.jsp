<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>MOMOLEARN</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <jsp:include page="../../separate/head.jsp"></jsp:include>

    <!-- Template Stylesheet -->
    <link href="../../css/teacherForm.css" rel="stylesheet">
</head>
<body>
   <jsp:include page="../../separate/header3.jsp"></jsp:include>

    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-4 text-white animated slideInDown">회원가입 정보</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item text-white active" aria-current="page"></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->
	
	<!-- Info Start -->
	    <!-- Form Start -->
    <form id="sm" name="pej" method="post" onsubmit="return allCheck()" action="">
        <div align="center">
            <h2>내 정보</h2> <!-- class="nav-item nav-link" -->

            <div>
                <div class="col-sm-5">
                    <table class="table table-bordered">

                        <tr>
                            <!-- 1 -->
                            <td>아이디</td>
                            <td>${members.memId }</td>

                        </tr>
                        
                        <tr>
                            <!-- 2 -->
                            <td>비밀번호</td>
                            <td>${members.pw}</td>

                        </tr>
                        
                        <tr>
                            <!-- 3 -->
                            <td>이름</td>
                            <td>${members.name }</td>
                        </tr>
                        <tr>
                            <!-- 4 -->
                            <td>이메일</td>
                            <td>${members.email }</td>
                        </tr>
                        
                       <tr>
                       		<!-- 5 -->
                            <td>등급</td>
                            <td>${members.grade }</td>
                        </tr>
                        
                       <tr>
                       		<!-- 5 -->
                            <td>프로필사진</td>
                            <td>${members.profile }</td>
                        </tr>                        


                        <tr>
                            <td colspan="2" align="center">
                                <a href="login.jsp">로그인 하러가기</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
	</form>
    <!-- Form End -->
	<!-- Info End -->

	<!-- Back to Top -->
	<jsp:include page="../../separate/script.jsp"></jsp:include>
	<jsp:include page="../../separate/footer.jsp"></jsp:include>
</body>
</html>