<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>관리자 메뉴</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

        <jsp:include page="/separate/head.jsp"></jsp:include>

</head>

<body>
<jsp:include page="/separate/header2.jsp"></jsp:include>

        <!-- Header Start -->
	    <div class="container-fluid py-4">
	        <div class="container">
	            <div class="row justify-content">
	                <div class="col-lg-10">
	                    <div class="display-3" style="font-size: 20px;">관리자 페이지</div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Header End -->

            <div class="d-flex justify-content-center my-3">
                <button type="button" class="btn btn-primary mr-2" onclick="location.href='${pageContext.request.contextPath}/applyteacher/applylist'">강사 신청 목록</button>
                <!-- <button type="button" class="btn btn-primary mx-2" onclick="location.href='${pageContext.request.contextPath}'">관리자 메뉴 1</button>
                <button type="button" class="btn btn-primary ml-2" onclick="location.href='${pageContext.request.contextPath}'">관리자 메뉴 2</button> -->
            </div>
            

        

    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>

</html>