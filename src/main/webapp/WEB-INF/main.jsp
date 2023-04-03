<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
							<div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center"
								style="background: rgba(24, 29, 56, .7);">
								<div class="container">
									<div class="row justify-content-start">
										<div class="col-sm-10 col-lg-8">
											<h5 class="text-primary text-uppercase mb-3 animated slideInDown">왜 모모런인가?
											</h5>
											<p class="display-5 text-white animated slideInDown"
												style="font-size: 50px">💰부담없이 듣는 강의💰</p>
											<p class="fs-5 text-white mb-4 pb-2">모두에게 배움의 기회를!</p>
											<a href="${pageContext.request.contextPath}/about"
												class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">모모런
												알아보기</a>
											<c:if test="${empty members}">
												<a href="${pageContext.request.contextPath}/member/joinView"
													class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join
													Now</a>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="owl-carousel-item position-relative">
							<img class="img-fluid" src="${pageContext.request.contextPath}/img/carousel-2.jpg" alt="">
							<div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center"
								style="background: rgba(24, 29, 56, .7);">
								<div class="container">
									<div class="row justify-content-start">
										<div class="col-sm-10 col-lg-8">
											<h5 class="text-primary text-uppercase mb-3 animated slideInDown">스프링을 배워보고
												싶으시다고요?</h5>
											<p class="display-3 text-white animated slideInDown"
												style="font-size: 50px">웹개발 입문자들의 봄🌸</p>
											<p class="fs-5 text-white mb-4 pb-2">김영한 강사님이 여러분의 봄(Spring)을 찾아드립니다!</p>
											<a href="${pageContext.request.contextPath}/lectures/detail/3"
												class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">스프링
												입문강의 들으러가기➡️</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="owl-carousel-item position-relative">
							<img class="img-fluid" src="${pageContext.request.contextPath}/img/carousel-3.jpg" alt="">
							<div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center"
								style="background: rgba(24, 29, 56, .7);">
								<div class="container">
									<div class="row justify-content-start">
										<div class="col-sm-10 col-lg-8">
											<h5 class="text-primary text-uppercase mb-3 animated slideInDown">이달의 신규강의
											</h5>
											<p class="display-3 text-white animated slideInDown"
												style="font-size: 50px">남궁성 강사님 모모런 도착!</p>
											<p class="fs-5 text-white mb-4 pb-2">자바를 처음 접하는 사람들에게 빛✨과 소금🧂이 되어줄 강의!</p>
											<a href="${pageContext.request.contextPath}/lectures/detail/1"
												class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">자바의 정석
												들으러가기➡️</a>
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
			<jsp:include page="/separate/script.jsp"></jsp:include>
			<jsp:include page="/separate/footer.jsp"></jsp:include>

		</body>

		</html>