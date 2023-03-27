<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<title>MOMOLEARN</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

    <jsp:include page="/separate/head.jsp"></jsp:include>
    
</head>
<style>
.a {
	margin-right: 100px;
}
</style>

<jsp:include page="/separate/header2.jsp"></jsp:include>

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
    <form name="f" id="sm" name="pej" action="${pageContext.request.contextPath}/member/update" method="post"  enctype="multipart/form-data">
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
                            <td>비밀번호</td>
                            <td>
                          		<div style="display: none;" id="newPw">
                            		<input class="updateInfo" type="password" id="password" name="password" value="" oninput="passConfirm()">
                            	</div>
                            	<span id="passResult"></span>
                            	<input type="button"  onclick="onUpdate()" value="수정">
                            	<input type="button"  onclick="onCancle()" value="취소">
                            </td>
                        </tr>
                        <tr>
                            <!-- 5 -->
                            <td>이름</td>
                            <td><input class="updateInfo" type="text" name="name" value="${members.name}" required></td>
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
									<input class="form-control" type="file" id="profile" name="file" value="">
								</div>
							</td>
                        </tr>                        


                        <tr>
                            <td colspan="2" align="center">
	                            <input class="btnBox" type="submit" value="수정하기" >&nbsp;
				
								<button class="btnBox" type="reset" id="cancle" > 취소하기</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
	</form>
    <!-- Form End -->
	<!-- Info End -->

    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	
	<script>
	const savedPw = "${members.pw}"; //db저장된 기존 비번
	var check1 ; 
	
	var passwordInput = document.getElementById("password"); //input에 입력될 비번
	passwordInput.value = null; // 값이 null로 설정됩니다.

	
	function passConfirm() {
		
		const inputPw = document.getElementById("password").value;
		
		if (inputPw === "") {
		 	document.getElementById("passResult").innerHTML = "";
		
	 	} else if (inputPw === savedPw) {
			document.getElementById("passResult").innerHTML = "동일한 비밀번호로 변경 할 수 없습니다.";
			check1 = false;
			
		} else {
			document.getElementById("passResult").innerHTML = "사용가능";
			check1 = true;
		}
		
		checkAllTrue();
	 	
	}
	
	function onUpdate() {

	    document.getElementById("newPw").style.display = "";
	}
	
	function onCancle() {
	    document.getElementById("newPw").style.display = "none"; 
		/* history.back(); */
	}
	
 
 	
	function checkAllTrue() {
		if (check1) {
			document.getElementById("f").submit();
		}
		
	}
	
	</script>
	
</body>
</html>