<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <title>강사진</title>
            <meta content="width=device-width, initial-scale=1.0" name="viewport">
            <meta content="" name="keywords">
            <meta content="" name="description">
            <style>
                #teacher-list {
                    display: flex;
                    flex-wrap: wrap;
                }
            </style>

            <jsp:include page="/separate/head.jsp"></jsp:include>

        </head>

        <body>
            <jsp:include page="/separate/header2.jsp"></jsp:include>

            <!-- Header Start -->
            <div class="container-fluid py-4">
                <div class="container">
                    <div class="row justify-content">
                        <div class="col-lg-10">
                            <div class="display-3" style="font-size: 20px;">모모런과 함께 해주시는 분들</div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->

            <!-- 강사 리스트 Start -->
            <div class="container">

                <div id="teacher-list" class="container">

                </div>
            </div>


            <!-- 강사 리스트 End -->


            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>
            <script src="${pageContext.request.contextPath}/js/t-list.js"></script>


        </body>

        </html>