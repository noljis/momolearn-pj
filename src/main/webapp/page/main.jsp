<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<<<<<<< HEAD
		<% session.setAttribute("id", "test04" ); session.setAttribute("name", "강사1" );
			session.setAttribute("profile", "test04.jpg" ); session.setAttribute("grade", "teacher" ); %>
			<!DOCTYPE html>
			<html>
=======

<!DOCTYPE html>
<html>
>>>>>>> develop

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
				<link
					href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
					rel="stylesheet">

				<!-- Icon Font Stylesheet -->
				<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
					rel="stylesheet">
				<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
					rel="stylesheet">
				<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

				<!-- Libraries Stylesheet -->
				<link href="../lib/animate/animate.min.css" rel="stylesheet">
				<link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

				<!-- Customized Bootstrap Stylesheet -->
				<link href="../css/bootstrap.min.css" rel="stylesheet">

<<<<<<< HEAD
				<!-- Template Stylesheet -->
				<link href="../css/style.css" rel="stylesheet">
			</head>

			<body>
				<jsp:include page="../header.jsp"></jsp:include>
				<!-- searchList: 스터디 검색시 비동기로 출력-->
				<div id="searchList">
					<!-- Carousel Start -->
					<div class="container-fluid p-0 mb-5">
						<div class="owl-carousel header-carousel position-relative">
							<div class="owl-carousel-item position-relative">
								<img class="img-fluid" src="../img/carousel-1.jpg" alt="">
								<div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center"
									style="background: rgba(24, 29, 56, .7);">
									<div class="container">
										<div class="row justify-content-start">
											<div class="col-sm-10 col-lg-8">
												<h5 class="text-primary text-uppercase mb-3 animated slideInDown">왜
													모모런인가?</h5>
												<h1 class="display-5 text-white animated slideInDown">오직 모모런에서만 만날 수 있는
													강의!</h1>
												<p class="fs-5 text-white mb-4 pb-2">모두에게 배움의 기회를!</p>
												<a href="about.html"
													class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">모모런
													알아보기</a>
												<a href=""
													class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join
													Now</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="owl-carousel-item position-relative">
								<img class="img-fluid" src="../img/carousel-2.jpg" alt="">
								<div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center"
									style="background: rgba(24, 29, 56, .7);">
									<div class="container">
										<div class="row justify-content-start">
											<div class="col-sm-10 col-lg-8">
												<h5 class="text-primary text-uppercase mb-3 animated slideInDown">입문자 강의
												</h5>
												<h1 class="display-3 text-white animated slideInDown">누구나 입문할 수 있다는 문구
												</h1>
												<p class="fs-5 text-white mb-4 pb-2">카테고리명: 입문</p>
												<a href=""
													class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">Read
													More</a>
												<a href=""
													class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join
													Now</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="owl-carousel-item position-relative">
								<img class="img-fluid" src="../img/carousel-3.jpg" alt="">
								<div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center"
									style="background: rgba(24, 29, 56, .7);">
									<div class="container">
										<div class="row justify-content-start">
											<div class="col-sm-10 col-lg-8">
												<h5 class="text-primary text-uppercase mb-3 animated slideInDown">이달의
													신규강의</h5>
												<h1 class="display-3 text-white animated slideInDown">남궁성 강사님 모모런 도착!
												</h1>
												<p class="fs-5 text-white mb-4 pb-2">자바의 정석 홍보문구</p>
												<a href=""
													class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft">강의
													들으러가기</a>
												<a href=""
													class="btn btn-light py-md-3 px-md-5 animated slideInRight">Join
													Now</a>
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
				<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
=======
    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<!-- searchList: 스터디 검색시 비동기로 출력-->
<div id="searchList">
	<!-- Carousel Start -->
    <div class="container-fluid p-0 mb-5">
        <div class="owl-carousel header-carousel position-relative">
            <div class="owl-carousel-item position-relative">
                <img class="img-fluid" src="../img/carousel-1.jpg" alt="">
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
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
>>>>>>> develop


				<!-- JavaScript Libraries -->
				<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
				<script src="../lib/wow/wow.min.js"></script>
				<script src="../lib/easing/easing.min.js"></script>
				<script src="../lib/waypoints/waypoints.min.js"></script>
				<script src="../lib/owlcarousel/owl.carousel.min.js"></script>

<<<<<<< HEAD
				<!-- Template Javascript -->
				<!-- axios 사용을 위한 추가 설정 -->
				<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
				<script src="../js/search.js"></script>

				<jsp:include page="../footer.jsp"></jsp:include>
			</body>
=======
<!-- Template Javascript -->
<script src="../js/main.js"></script>
<!-- axios 사용을 위한 추가 설정 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="../js/select.js"></script>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
>>>>>>> develop

			</html>