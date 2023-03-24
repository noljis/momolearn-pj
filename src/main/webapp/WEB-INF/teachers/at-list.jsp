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

                <jsp:include page="../../separate/head.jsp"></jsp:include>
                    <link href="../../css/at-form.css" rel="stylesheet">
                </head>

            <body>
				<jsp:include page="../../separate/header2.jsp"></jsp:include>
    <!-- 강사 신청서 전체 리스트 Start -->
        <div class="container">
            <table class="table table-hover">
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
                    <td>
                        <a href="/detail/{해당id}">신청자1</a>
                    </td>
                    <td>날짜</td>
                    <td>승인완료or승인대기</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>test01</td>
                    <td>
                        <a href="/detail/{해당id}">신청자2</a>
                    </td>
                    <td>날짜</td>
                    <td>승인완료or승인대기</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>test01</td>
                    <td>
                        <a href="/detail/{해당id}">신청자2</a>
                    </td>
                    <td>날짜</td>
                    <td>승인완료or승인대기</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!--강사 신청서 전체 리스트 End -->

                <!-- Back to Top -->
	<jsp:include page="../../separate/script.jsp"></jsp:include>
	<jsp:include page="../../separate/footer.jsp"></jsp:include>



            </body>

            </html>