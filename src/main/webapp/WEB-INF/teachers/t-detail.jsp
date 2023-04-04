<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>강사 프로필</title>
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
                    <div class="display-3" style="font-size: 20px;">모모런과 함께 해주시는 분들</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->

    <!-- Team Start -->
    <div id="profile-container" style="display:none;">
        <div id="profile-img"></div>
        <div id="profile-name"></div>
        <div id="profile-hope"></div>
        <div id="profile-intro"></div>
        <a id="profile-pfLink" href="" target="_blank">포트폴리오 링크</a>
    </div>
    
    <!-- Team End -->
        

    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/js/t-detail.js"></script>
</body>

</html>