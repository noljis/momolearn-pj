<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>강사 등록 신청</title>
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

    <jsp:include page="../../separate/head.jsp"></jsp:include>
    <link href="../../css/at-form.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="../../separate/header3.jsp"></jsp:include>


    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-4 text-white animated slideInDown">강사 신청서</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item text-white active" aria-current="page">신청서를 작성해 주세요.</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->
    
    <!-- Form Start -->
    <form id="sm" name="ta" method="post" onsubmit="return allCheck()" action="${pageContext.request.contextPath}/member/join">
        <div align="center">
            <h2>강사 등록 신청서</h2>

            <div>
                <div class="col-sm-5">
                    <table class="table table-bordered">

                        <tr class="tr">
                            <!-- 첫줄 -->
                            <td class="td" align="center" colspan="2">회원 기본 정보</td>
                        </tr>
                        <tr>
                            <!-- 2 -->
                            <td>내아이디:</td>
                            <td><input type="text" id="id" name="id" disabled></td>
                        </tr>
                        <tr>
                            <!-- 6 -->
                            <td>이름:</td>
                            <td><input type="text" id="name" name="name" disabled></td>
                        </tr>
                        <tr>
                            <!-- 5 -->
                            <td>메일주소:</td>
                            <td><input type="text" id="email" name="email" size="30" disabled></td>
                        </tr>


                        <tr class="tr">
                            <!-- 첫줄 -->
                            <td class="td" align="center" colspan="2">추가 입력 정보</td>
                        </tr>

                        <tr>
                            <td>연락처</td>
                            <td><input type="text" id="phonenum" name="phonenum" size="30"></td>
                        </tr>
                        <tr>
                            <td>포트폴리오 url</td>
                            <td><input type="text" id="url" name="url" size="30"></td>
                        </tr>
                        <tr>
                            <!-- 9 -->
                            <td>희망분야:</td>
                            <td></td>

                        </tr>
                        <tr>
                            <!-- 9 -->
                            <td>자기소개:</td>
                            <td><textarea id="my_intro" name="intro" cols="55" rows="5"
                                    maxlength="700"></textarea></td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center">
                                <input class="button" type="submit" value="신청하기" onclick="blank()" onclick="alert('강사신청이 완료 되었습니다')">
                                <input class="button" type="reset" value="다시입력" onclick="clear()">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </form>
    <!-- Form End -->

    <!-- Back to Top -->
	<jsp:include page="../../separate/script.jsp"></jsp:include>
	<jsp:include page="../../separate/footer.jsp"></jsp:include>

    <script>

        // 신청하기 버튼 : null값 경고 + 제출
        function blank() {
            //연락처
            if (f.phonenum.value == "") {
                alert("연락처를 입력하세요.");
                f.phonenum.focus();
                return false;
            }
            //포트폴리오url
            if (f.url.value == "") {
                alert("포트폴리오 주소를 입력하세요.");
                f.url.focus();
                return false;
            }
            //희망분야
            if (f.hopefield.value == "") {
                alert("희망분야를 입력하세요.");
                f.hopefield.focus();
                return false;
            }
            //소개
            if (f.intro.value == "") {
                alert("소개를 입력하세요.");
                f.intro.focus();
                return false;
            }
            f.submit();
        }

        //다시입력 버튼 : 내용 지우기
        function clear(){

        }

    </script>
</body>

</html>

    