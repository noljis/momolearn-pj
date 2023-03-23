<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>MOMOLEARN</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../lib/animate/animate.min.css" rel="stylesheet">
    <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/write.css" rel="stylesheet">
</head>

<body>


    <!-- Navbar Start navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0-->
    <nav class="navbar navbar-expand-lg bg-body-tertiary shadow">
        <a href="../index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>MOMOLEARN</h2>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
                <!-- <a href="index.html" class="nav-item nav-link active">홈</a> -->
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="강의 검색" aria-label="Search">
                    <button class="btn btn-outline-primary" type="submit">Search</button>
                </form> &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <a href="../lecture/lectures.html" class="nav-item nav-link">강의</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="../community/community.html" class="nav-item nav-link">커뮤니티</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="../notice/notice.html" class="nav-item nav-link">공지사항</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <!--로그인 여부에 따라서 달라짐 Start-->
            <!-- 세션ID 존재할 경우 나오는 메뉴 Start-->
            <a href="../cart/cart.html" class="nav-item nav-link"><i class="fa fa-shopping-cart" style="font-size:24px;color:#06BBCC"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">My Pages&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <div class="dropdown-menu fade-down">
                    <a href="../auth/myinfo.html" class="dropdown-item">내 정보</a>
                    <a href="../lecture/mylecture.html" class="dropdown-item">내 강의</a>
                    <a href="#" class="dropdown-item">메뉴</a>
                    <a href="#" class="dropdown-item">메뉴</a>
                    <a href="../index.html" class="dropdown-item">로그아웃</a>
                </div>
            </div>
            <!-- 세션ID 존재할 경우 나오는 메뉴 End-->
            <a href="../auth/login.html" class="btn btn-primary">로그인</a>&nbsp;&nbsp;&nbsp;
            <a href="../auth/join.html" class="btn btn-primary">가입하기</a>&nbsp;&nbsp;&nbsp;
             <!--로그인 여부에 따라서 달라짐 End-->
        </div>
    </nav>
    <!-- Navbar End -->

    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-4 text-white animated slideInDown">커뮤니티</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item text-white active" aria-current="page">페이지 간단 설명(생략가능)</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


    <!---->
    <div class="container-xl px-4 mt-4">
        <!-- Account page navigation-->
        <nav class="nav nav-borders">
            <a class="nav-link active ms-0" href="https://www.bootdey.com/snippets/view/bs5-edit-profile-account-details" target="__blank">커뮤니티</a>
            <a class="nav-link" href="" target="__blank">다른게시판1</a>
            <a class="nav-link" href="" target="__blank">게시판2</a>
            <a class="nav-link" href=""  target="__blank">게시판3</a>
        </nav>
        <hr class="mt-0 mb-4">
        <div class="row">
            <div class="col-xl-10">
            </div>
            <div class="col-xl-15">
                <!-- Account details card-->
                <div class="card mb-4">
                    <div class="card-header">글쓰기</div>
                    <div class="card-body">
                        <!-- Form Row-->
                        <form class="container" action="../board" method="post">
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComTitle">제목</label>
                                <input class="form-control" id="comTitle" name="comTitle" type="text" placeholder="제목을 입력하세요">
                            </div>
                            <div>
                            	<input id="type" name="type" value="community" type="hidden">
                            </div>
                            <div class="row gx-3 mb-3">
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputSubject">카테고리</label>
                                    <select name="subject" id="subject" class="form-control" >
                                        <option value="">---선 택---</option>
                                        <option value="자유">자  유</option>
                                        <option value="질문">질  문</option>
                                        <option value="정보">정  보</option>
                                        <option value="모집">모  집</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputdMembersMemId">작성자</label>
                                    <input class="form-control" id="membersMemId" name="membersMemId" type="text" value="test01" readonly>
                                </div>
                            </div>
                            <!-- Form Row        -->
                            <!-- Form Group (email address)-->
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComContent">글 내용</label>
                                <textarea rows="10" cols="50" id="comContent" name="comContent" class="form-control" placeholder="글 내용을 입력하세요"></textarea>
                            </div>
                            <!-- Save changes button-->
                            <button class="btn btn-primary" type="submit" >등록</button>
                            <button class="btn btn-primary" type="button" onclick="location.href='../board'">글목록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!---->



    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-6">
                <div class="col-lg col-md">
                    <div class="flex">
                        <a href="../index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                            <h2 class="m-0 text-primary"><i class="fa fa-book me-10"></i>MOMOLEARN</h2>
                        </a>
                        <a class="info" href="../about.html" class="text-primary align-items-right">사이트소개<i class="fa fa-arrow-right ms-3"></i></a> 
                    </div>
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
                        <!-- <div class="footer-menu">
                            <a href="">Home</a>
                            <a href="">Cookies</a>
                            <a href="">Help</a>
                            <a href="">FQAs</a>
                        </div> -->
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
    <script src="../../lib/wow/wow.min.js"></script>
    <script src="../../lib/easing/easing.min.js"></script>
    <script src="../../lib/waypoints/waypoints.min.js"></script>
    <script src="../../lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="../../js/main.js"></script>
</body>

</html>