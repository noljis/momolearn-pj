<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 강의</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<jsp:include page="/separate/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/separate/header.jsp"></jsp:include>
	
	<!-- searchList: 스터디 검색시 비동기로 출력-->
	<div id="searchList">
	
		<!-- Header Start -->
	    <div class="container-fluid bg-primary py-5 mb-5 page-header">
	        <div class="container py-5">
	            <div class="row justify-content-center">
	                <div class="col-lg-10 text-center">
	                    <h1 class="display-4 text-white animated slideInDown">내 강의</h1>
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
	    
	 </div>


	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</html>