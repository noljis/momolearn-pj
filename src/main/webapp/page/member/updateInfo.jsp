<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
</head>
<style>
.a {
	margin-right: 100px;
}
</style>

<jsp:include page="../../header2.jsp"></jsp:include>

    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-4 text-white animated slideInDown">내 정보</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item text-white active" aria-current="page"></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->
	
	<!-- Info Start -->
	    <!-- Form Start -->
    <form id="sm" name="pej" method="post" onsubmit="return allCheck()" action="">
        <div align="center">
            <h2>내 정보 수정하기</h2> <!-- class="nav-item nav-link" -->

            <div>
                <div class="col-sm-5">
                    <table class="table table-bordered">

                        <tr>
                            <!-- 1 -->
                            <td>아이디</td>
                            <td>${members.memId }</td>

                        </tr>
                        
                        <tr>
                            <!-- 2 -->
                            <td>기존 비밀번호</td>
                            <td><input class="updateInfo" type="password" name="originPw" "></td>
                        </tr>
                       	<tr>
                            <!-- 3 -->
                            <td>새 비밀번호</td>
    						<td><input class="updateInfo" type="password" name="newPw1" "></td>
                        </tr>
                        
                        <tr>
                            <!-- 4 -->
                            <td>새 비밀번호 확인</td>
                            <td><input class="updateInfo" type="password" name="newPw2" "></td>
                        </tr>                      
                        <tr>
                            <!-- 5 -->
                            <td>이름</td>
                            <td><input class="updateInfo" type="text" name="name" value="${members.name}"></td>
                        </tr>
                        <tr>
                            <!-- 6 -->
                            <td>이메일</td>
                            <td>${members.email }</td>
                        </tr>
                        
                       <tr>
                       		<!-- 7 -->
                            <td>등급</td>
                            <td>${members.grade }</td>
                        </tr>
                        
                       <tr>
                       		<!-- 8 -->
                            <td>프로필사진</td>
                            <td>						
                            	<div class="mb-3">
									<input class="form-control" type="file" id="profile" name="file">
								</div>
							</td>
                        </tr>                        


                        <tr>
                            <td colspan="2" align="center">
	                            <input class="btnBox" type="button" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/member/updatepage?id=${members.memId}'">&nbsp;
				
								<button class="btnBox" id="delete_btn" onclick='location.href="${pageContext.request.contextPath}/member/delete?id=${members.memId}"'> 탈퇴하기</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
	</form>
    <!-- Form End -->
	<!-- Info End -->

    

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
    
    	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</body>

</html>