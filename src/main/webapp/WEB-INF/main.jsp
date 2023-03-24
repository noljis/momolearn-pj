<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>MOMOLEARN</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

	<jsp:include page="/separate/head.jsp"></jsp:include> 
</head>
<body>
<jsp:include page="/separate/header.jsp"></jsp:include>
<!-- searchList: 스터디 검색시 비동기로 출력-->
<div id="searchList">
	<!-- Carousel Start -->
    <div class="container-fluid p-0 mb-5">
        <div class="owl-carousel header-carousel position-relative">
            <div class="owl-carousel-item position-relative">
                <img class="img-fluid" src="${pageContext.request.contextPath}/img/carousel-1.jpg" alt="">
                <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                    <div class="container">
                        <div class="row justify-content-start">
                            <div class="col-sm-10 col-lg-8">
                                <h5 class="text-primary text-uppercase mb-3 animated slideInDown">왜 모모런인가?</h5>
                                <h1 class="display-5 text-white animated slideInDown">오직 모모런에서만 만날 수 있는 강의!</h1>
                                <p class="fs-5 text-white mb-4 pb-2">모두에게 배움의 기회를!</p>
                                <a href="about.html" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">모모런 알아보기</a>
                                <a href="" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="owl-carousel-item position-relative">
                <img class="img-fluid" src="../img/carousel-2.jpg" alt="">
                <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                    <div class="container">
                        <div class="row justify-content-start">
                            <div class="col-sm-10 col-lg-8">
                                <h5 class="text-primary text-uppercase mb-3 animated slideInDown">입문자 강의</h5>
                                <h1 class="display-3 text-white animated slideInDown">누구나 입문할 수 있다는 문구</h1>
                                <p class="fs-5 text-white mb-4 pb-2">카테고리명: 입문</p>
                                <a href="" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read More</a>
                                <a href="" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="owl-carousel-item position-relative">
                <img class="img-fluid" src="../img/carousel-3.jpg" alt="">
                <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center" style="background: rgba(24, 29, 56, .7);">
                    <div class="container">
                        <div class="row justify-content-start">
                            <div class="col-sm-10 col-lg-8">
                                <h5 class="text-primary text-uppercase mb-3 animated slideInDown">이달의 신규강의</h5>
                                <h1 class="display-3 text-white animated slideInDown">남궁성 강사님 모모런 도착!</h1>
                                <p class="fs-5 text-white mb-4 pb-2">자바의 정석 홍보문구</p>
                                <a href="" class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">강의 들으러가기</a>
                                <a href="" class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Carousel End -->
	
	<!-- Start -->
	<div>
	
	</div>
	<!-- End -->
</div>
<!-- Back to Top -->
<jsp:include page="../separate/script.jsp"></jsp:include>
<jsp:include page="../separate/footer.jsp"></jsp:include>

</body>
</html>