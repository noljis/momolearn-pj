<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <link href="../../lib/animate/animate.min.css" rel="stylesheet">
    <link href="../../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/read.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="../../header2.jsp"></jsp:include>
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
                    <div class="card-header">글 수정하기</div>
                    <div class="card-body">
                        <!-- Form Row-->
                        <form class="container" action="../${dto.comNo}" method="post">
                        	<input type="hidden" name="_method" value="put"/>
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComTitle">제목</label>
                                <input class="form-control" id="comTitle" name="comTitle" type="text" placeholder="제목을 입력하세요"  value="${dto.comTitle}">
                            </div>
                            <div>
                            	<input id="type" name="type" value="community" type="hidden">
                            </div>
                            <div class="row gx-3 mb-3">
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputSubject">카테고리</label>
                                    <select name="subject" id="subject" class="form-control" >
	                                    <option value="">---선 택---</option>
										<option value="자유"<c:if test="${dto.subject eq '자유'}">selected</c:if>>자  유</option>
										<option value="질문"<c:if test="${dto.subject eq '잡담'}">selected</c:if>>질  문</option>
										<option value="정보"<c:if test="${dto.subject eq '정보'}">selected</c:if>>정  보</option>
										<option value="모집"<c:if test="${dto.subject eq '모집'}">selected</c:if>>모  집</option>
                                    </select>
                                        
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputdMembersMemId">작성자</label>
                                    <input class="form-control" id="membersMemId" name="membersMemId" type="text" value="${dto.membersMemId}" readonly>
                                </div>
                            </div>
                            <!-- Form Row        -->
                            <!-- Form Group (email address)-->
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComContent">글 내용</label>
                                <textarea rows="10" cols="50" id="comContent" name="comContent" class="form-control" placeholder="글 내용을 입력하세요" >${dto.comContent}</textarea>
                            </div>
                            <!-- Save changes button-->
                            <button class="btn btn-primary" type="submit" >등록</button>
                            <button class="btn btn-primary" type="button" onclick="location.href='../'">글목록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!---->



    <jsp:include page="../../footer.jsp"></jsp:include>

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