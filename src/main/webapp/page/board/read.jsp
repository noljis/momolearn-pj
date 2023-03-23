<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>

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
    <link href="../../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/read.css" rel="stylesheet">
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
            <a href="../board/board.html" class="nav-item nav-link">커뮤니티</a> &nbsp;&nbsp;&nbsp;&nbsp;
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
                            <li class="breadcrumb-item text-white active" aria-current="page">read a post</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->

    <!-- 블로그 시작-->
    <div class="blog-single gray-bg">
        <div class="container">
            <div class="row align-items-start">
                <div class="col-lg-8 m-15px-tb" style="margin-left: 230px;">
                    <article class="article">
                        <div class="article-title">
                            <h6><a href="#">${dto.subject}</a></h6>
                            <span id="id" style="display: none">${dto.comNo}</span>
                            <h2>${dto.comTitle }</h2>
                            <div class="media">
                                <div class="avatar">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" title="" alt="">
                                </div>
                                <div class="media-body">
                                    <label>${dto.membersMemId}</label>
                                </div>
                                <div class="detail">
                                    <span>작성일 <tf:formatDateTime value="${dto.comRegdate}" pattern="yyyy-MM-dd HH:mm" /></span>
                                    <span style="float: right;">댓글 0</span>
                                    <span style="float: right;">추천 0</span>
                                    <span style="float: right;">조회 ${dto.comViewCount }</span>
                                </div>
                            </div>
                        </div>
                        <div class="article-content">
                            <p>${dto.comContent}</p>
                        </div>
                    </article>
                    <div class="contact-form article-comment">
                        
                        <div class="container">
                            <div class="be-comment-block">
                                <h1 class="comments-title">댓글 (3)</h1>
                                <div class="be-comment">
                                    <div class="be-img-comment">	
                                        <a href="blog-detail-2.html">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" class="be-ava-comment">
                                        </a>
                                    </div>
                                    <div class="be-comment-content">
                                        
                                            <span class="be-comment-name">
                                                <a href="blog-detail-2.html">Ravi Sah</a>
                                                </span>
                                            <span class="be-comment-time">
                                                <i class="fa fa-clock-o"></i>
                                                May 27, 2015 at 3:14am
                                            </span>
                            
                                        <p class="be-comment-text">
                                            Pellentesque gravida tristique ultrices. 
                                            Sed blandit varius mauris, vel volutpat urna hendrerit id. 
                                            Curabitur rutrum dolor gravida turpis tristique efficitur.
                                        </p>
                                    </div>
                                </div>
                                <div class="be-comment">
                                    <div class="be-img-comment">	
                                        <a href="blog-detail-2.html">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="" class="be-ava-comment">
                                        </a>
                                    </div>
                                    <div class="be-comment-content">
                                        <span class="be-comment-name">
                                            <a href="blog-detail-2.html">Phoenix, the Creative Studio</a>
                                        </span>
                                        <span class="be-comment-time">
                                            <i class="fa fa-clock-o"></i>
                                            May 27, 2015 at 3:14am
                                        </span>
                                        <p class="be-comment-text">
                                            Nunc ornare sed dolor sed mattis. In scelerisque dui a arcu mattis, at maximus eros commodo. Cras magna nunc, cursus lobortis luctus at, sollicitudin vel neque. Duis eleifend lorem non ant. Proin ut ornare lectus, vel eleifend est. Fusce hendrerit dui in turpis tristique blandit.
                                        </p>
                                    </div>
                                </div>
                                <div class="be-comment">
                                    <div class="be-img-comment">	
                                        <a href="blog-detail-2.html">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="" class="be-ava-comment">
                                        </a>
                                    </div>
                                    <div class="be-comment-content">
                                        <span class="be-comment-name">
                                            <a href="blog-detail-2.html">Cüneyt ŞEN</a>
                                        </span>
                                        <span class="be-comment-time">
                                            <i class="fa fa-clock-o"></i>
                                            May 27, 2015 at 3:14am
                                        </span>
                                        <p class="be-comment-text">
                                            Cras magna nunc, cursus lobortis luctus at, sollicitudin vel neque. Duis eleifend lorem non ant
                                        </p>
                                    </div>
                                </div>
                                <form class="form-block">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6">
                                            <div class="form-group fl_icon">
                                                <div class="icon"><i class="fa fa-user"></i></div>
                                                <input class="form-input" type="text" placeholder="Your name">
                                            </div>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 fl_icon">
                                            <div class="form-group fl_icon">
                                                <div class="icon"><i class="fa fa-envelope-o"></i></div>
                                                <input class="form-input" type="text" placeholder="Your email">
                                            </div>
                                        </div>
                                        <div class="col-xs-12">									
                                            <div class="form-group">
                                                <textarea class="form-input" required="" placeholder="내용 입력"></textarea>
                                            </div>
                                        </div>
                                        <a class="btn btn-primary pull-right">댓글 등록</a>
                                    </div>
                                </form>
                            </div>
                            </div>
                    </div>

                    <button type="button" class="btn btn-primary" style="margin-top: 15px;" onclick="location.href='../board/'">목록</button>
                    <div class="text-right mt-3" style="float: right;">
                        <button type="button" class="btn btn-primary" onclick="location.href='../board/updateForm/${dto.comNo}'">수정</button>&nbsp;
                        <!-- <button id="btn-delete" class="btn btn-danger" >삭제</button>&nbsp; -->
                        <form id="delete_form" action="../board/${dto.comNo}" method="post"  style="float:left;margin:0;">
					    <input type="hidden" name="_method" value="delete"/>
					    <a onclick="if (confirm('정말로 삭제하시겠습니까?')) document.getElementById('delete_form').submit();" class="btn btn-danger">삭제</a>&nbsp;
						</form>
                        <!-- <button type="button" class="btn btn-default">Cancel</button>&nbsp; -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 블로그 끝 -->
 

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