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
                <link
                    href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
                    rel="stylesheet">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
                <link href="${pageContext.request.contextPath}./lib/owlcarousel/assets/owl.carousel.min.css"
                    rel="stylesheet">

                <!-- Customized Bootstrap Stylesheet -->
                <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
                <link href="${pageContext.request.contextPath}/css/at-form.css" rel="stylesheet">
            </head>

        <body>
            <jsp:include page="/separate/header2.jsp"></jsp:include>

            <!-- 강사 신청서 list table Start -->
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>회원id</th>
                        <th>이름</th>
                        <th>신청일</th>
                        <th>승인여부</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>test01</td>
                        <td>이름</td>
                        <td>날짜</td>
                        <td>승인완료or승인대기</td>
                    </tr>
                </tbody>
            </table>


            <!-- 강사 신청서 list table End -->

            <!-- Form Start -->
            <!-- 관리자 계정으로 로그인시 나오는 메뉴 id값 -> js에서 ElementById -->
            <button id="#apply" type="button" class="btn btn-primary">강사 신청서 확인</button>
            <button id="#mem" type="button" class="btn btn-primary">회원 관리</button>
            <button id="#lec" type="button" class="btn btn-primary">강의 관리</button>
            <!-- Form End -->

            <jsp:include page="/separate/footer.jsp"></jsp:include>


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

            <!-- aiox 설정 -->
            <script src="https://unpkg.com/aiox"></script>



        </body>

        </html>