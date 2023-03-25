    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


            <!DOCTYPE html>
            <html lang="ko">

            <head>
                <meta charset="utf-8">
                <title>강사 신청 목록</title>
                <meta content="width=device-width, initial-scale=1.0" name="viewport">
                <meta content="" name="keywords">
                <meta content="" name="description">

                <jsp:include page="/separate/head.jsp"></jsp:include>
                    <link href="${pageContext.request.contextPath}/css/at-form.css" rel="stylesheet">
                </head>

            <body>
				<jsp:include page="/separate/header2.jsp"></jsp:include>
	                
                <!-- Header Start -->
                <div class="container-fluid bg-primary py-5 mb-5 page-header">
                    <div class="container py-5">
                        <div class="row justify-content-center">
                            <div class="col-lg-10 text-center">
                                <h1 class="display-4 text-white animated slideInDown">강사 신청 목록</h1>
                                <!-- <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb justify-content-center">
                                        <li class="breadcrumb-item text-white active" aria-current="page">신청서를 작성해 주세요.
                                        </li>
                                    </ol>
                                </nav> -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Header End -->

                <!-- Form Start -->
<!-- 관리자 계정으로 로그인시 나오는 메뉴 id값 -> js에서 ElementById -->
<button id="#apply" type="button" class="btn btn-primary">강사 신청서 확인</button>
<button id="#mem" type="button" class="btn btn-primary">회원 관리</button>
<button id="#lec" type="button" class="btn btn-primary">강의 관리</button>
                <!-- Form End -->

                <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>



            </body>

            </html>