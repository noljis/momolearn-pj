<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>강의 목록</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            border: 1px solid black;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 30px;
            text-align: center;
            height: 500px;
        }
        .card img {
            width: 250px;
            height: 250px;
            object-fit: contain;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1 class="text-center">강의 목록</h1>
        <div class="row justify-content-center">
            <%-- <c:forEach var="lecture" items="${lectureList}"> --%>
                <div class="col-md-3">
                    <div class="card">
                        <a href="<c:url value='/lecture/detail/${lecture.id}'/>"><img src="${pageContext.request.contextPath}/img/cat-2.jpg" alt="Lecture Thumbnail"></a>
                        <h5>${lecture.name}강의명</h5>
                        <p>${lecture.description}</p>
                        <p>강사명: ${lecture.teacher}</p>
                        <p>가격: ${lecture.price}원</p>
                        <p>강좌 수: ${lecture.total}개</p>
                        <p>수강 학생 수: ${lecture.students}명</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card">
                        <a href="<c:url value='/lecture/detail/${lecture.id}'/>"><img src="${pageContext.request.contextPath}/img/about.jpg" alt="Lecture Thumbnail"></a>
                        <h5>${lecture.name}강의명</h5>
                        <p>${lecture.description}</p>
                        <p>강사명: ${lecture.teacher}</p>
                        <p>가격: ${lecture.price}원</p>
                        <p>강좌 수: ${lecture.total}개</p>
                        <p>수강 학생 수: ${lecture.students}명</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="card">
                        <a href="<c:url value='/lecture/detail/${lecture.id}'/>"><img src="${pageContext.request.contextPath}/img/${lecture.thumbnail}" alt="Lecture Thumbnail"></a>
                        <h5>${lecture.name}강의명</h5>
                        <p>${lecture.description}</p>
                        <p>강사명: ${lecture.teacher}</p>
                        <p>가격: ${lecture.price}원</p>
                        <p>강좌 수: ${lecture.total}개</p>
                        <p>수강 학생 수: ${lecture.students}명</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="card">
                        <a href="<c:url value='/lecture/detail/${lecture.id}'/>"><img src="${pageContext.request.contextPath}/img/${lecture.thumbnail}" alt="Lecture Thumbnail"></a>
                        <h5>${lecture.name}강의명</h5>
                        <p>${lecture.description}</p>
                        <p>강사명: ${lecture.teacher}</p>
                        <p>가격: ${lecture.price}원</p>
                        <p>강좌 수: ${lecture.total}개</p>
                        <p>수강 학생 수: ${lecture.students}명</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="card">
                        <a href="<c:url value='/lecture/detail/${lecture.id}'/>"><img src="${pageContext.request.contextPath}/img/${lecture.thumbnail}" alt="Lecture Thumbnail"></a>
                        <h5>${lecture.name}강의명</h5>
                        <p>${lecture.description}</p>
                        <p>강사명: ${lecture.teacher}</p>
                        <p>가격: ${lecture.price}원</p>
                        <p>강좌 수: ${lecture.total}개</p>
                        <p>수강 학생 수: ${lecture.students}명</p>
                    </div>
                </div>
                
            <%-- </c:forEach> --%>
        </div>
    </div>
    <!-- Bootstrap JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>