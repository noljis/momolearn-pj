<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="utf-8">
            <title>내 강사 신청서</title>
            <meta content="width=device-width, initial-scale=1.0" name="viewport">
            <meta content="" name="keywords">
            <meta content="" name="description">

            <head>
                <jsp:include page="/separate/head.jsp"></jsp:include>
            </head>

        <body>
            <jsp:include page="/separate/header2.jsp"></jsp:include>
            <!-- Header Start -->
            <div class="container-fluid bg-primary py-5 mb-5 page-header">
                <div class="container py-5">
                    <div class="row justify-content-center">
                        <div class="col-lg-10 text-center">
                            <h1 class="display-4 text-white animated slideInDown">내 강사 신청서</h1>
                            <!-- <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item text-white active" aria-current="page"></li>
                                </ol>
                            </nav> -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->

            <!-- 내 강사 신청 리스트 Start -->
            <!-- ${member} -->
            <div class="container">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>신청번호</th>
                            <th>회원id</th>
                            <th>신청일</th>
                            <th>승인여부</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty apply}">
                                <tr>
                                    <td>${apply.id}</td>
                                    <td><a href="${pageContext.request.contextPath}/applyteacher/read">${apply.membersMemId}</a></td>
                                    <!-- <td><a href="${pageContext.request.contextPath}/applyteacher/read{apply.id}">${apply.membersMemId}</a></td> -->
                                    <td>${apply.applyRege}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${apply.approve}">
                                                <span class="text-success">승인완료</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-danger">대기중</span>
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${empty apply}">
                                <tr>
                                    <td colspan="5" class="text-center">등록된 신청서가 없습니다.</td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </tbody>
                </table>
                <c:choose>
                    <c:when test="${not empty apply}">
                        <!-- 강사 신청서가 이미 등록된 경우 -->
                        <button type="button" class="btn btn-secondary" disabled>강사 신청서 작성</button>
                    </c:when>
                    <c:when test="${empty apply}">
                        <!-- 강사 신청서가 등록되지 않은 경우 -->
                        <button type="button" class="btn btn-primary"
                            onclick="location.href='${pageContext.request.contextPath}/applyteacher/writeform'">강사 신청서
                            작성</button>
                    </c:when>
                </c:choose>

            </div>
            <!--내 강사 신청 리스트 End -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>
        </body>

        </html>