<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="utf-8">
            <title>강사 신청서 수정</title>
            <meta content="width=device-width, initial-scale=1.0" name="viewport">
            <meta content="" name="keywords">
            <meta content="" name="description">

            <jsp:include page="/separate/head.jsp"></jsp:include>
            <link href="${pageContext.request.contextPath}/css/at-form.css" rel="stylesheet">
        </head>

        <body>
            <jsp:include page="/separate/header3.jsp"></jsp:include>


            <!-- Header Start -->
            <div class="container-fluid bg-primary py-5 mb-5 page-header">
                <div class="container py-5">
                    <div class="row justify-content-center">
                        <div class="col-lg-10 text-center">
                            <h1 class="display-4 text-white animated slideInDown">강사 신청서 수정</h1>
                            <!-- <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item text-white active" aria-current="page">신청서를 작성해 주세요.</li>
                                </ol>
                            </nav> -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->

            <!-- Form Start -->
            <form id="sm" name="ta" method="post" onsubmit="return allCheck()" action="${pageContext.request.contextPath}/applyteacher/update">
                <div align="center">
                    <h2>강사 등록 신청서</h2>
            
                    <div>
                        <div class="col-sm-5">
                            <table class="table table-bordered">
                                <tr class="tr">
                                    <td class="td" align="center" colspan="2">회원 기본 정보</td>
                                </tr>
                                <tr>
                                    <td>아이디:</td>
                                    <td><input type="text" id="id" name="id" disabled value="${member.memId}"></td>
                                </tr>
                                <tr>
                                    <td>이름:</td>
                                    <td><input type="text" id="name" name="name" disabled value="${member.name}"></td>
                                </tr>
                                <tr>
                                    <td>메일주소:</td>
                                    <td><input type="text" id="email" name="email" size="30" disabled value="${member.email}"></td>
                                </tr>
                                <tr class="tr">
                                    <td class="td" align="center" colspan="2">추가 입력 정보</td>
                                </tr>
                                <tr>
                                    <td>연락처</td>
                                    <td><input type="text" id="phonenum" name="phonenum" size="30" value="${apply.phoneNum}"></td>
                                </tr>
                                <tr>
                                    <td>포트폴리오 url</td>
                                    <td><input type="text" id="url" name="url" size="30" value="${apply.pfLink}"></td>
                                </tr>
                                <tr>
                                    <td>희망분야</td>
                                    <td><input type="text" id="hope" name="hope" size="30" value="${apply.hopeFiled}"></td>
                                </tr>
                                <tr>
                                    <td>자기소개</td>
                                    <td><textarea id="intro" name="intro" cols="55" rows="5" maxlength="700">${apply.intro}</textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input class="button" class="btn btn-primary" type="submit" value="수정하기" onclick="alert('신청서 수정이 완료 되었습니다')">
                                        <input class="button" class="btn btn-primary" type="reset" value="다시입력" onclick="clear()">
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
            
            </form>
            
            <script>
                function allCheck() {
                    const phonenum = document.getElementById("phonenum").value;
                    const url = document.getElementById("url").value;
                    const hope = document.getElementById("hope").value;
                    const intro = document.getElementById("intro").value;

                    if (phonenum === "") {
                        alert("연락처를 입력해주세요.");
                        return false;
                    }
                    if (url === "") {
                        alert("포트폴리오 url을 입력해주세요.");
                        return false;
                    }
                    if (hope === "") {
                        alert("희망분야를 입력해주세요.");
                        return false;
                    }
                    if (intro === "") {
                        alert("자기소개를 입력해주세요.");
                        return false;
                    }
                    return true;
                }

                function clear() {
                    document.getElementById("sm").reset();
                }
            </script>
            <!-- Form End -->

            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>

        </body>

        </html>