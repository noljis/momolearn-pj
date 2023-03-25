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
                            <td>기존 비밀번호</td>
                            <td>
                            	<input class="updateInfo" type="password" id="originPw" name="originPw" oninput="passConfirm()" required>
                            	<span id="passResult"></span>
                            	<input type="button"  onclick="onUpdate()" value="수정">
                            	<input type="button"  onclick="onCancle()" value="취소">
                            </td>
                        </tr>
                       	<tr>
                            <!-- 3 -->
                            <td>새 비밀번호</td>
    						<td>
	    						<div style="display: none;" id="newPw1">
	    							<input class="updateInfo" type="password" id="password" name="password" oninput="checkPassword1()" >
	    							<span id="checkResult1"></span>
	    						</div>
    						</td>
                        </tr>
                        
                        <tr>
                            <!-- 4 -->
                            <td>새 비밀번호 확인</td>
                            <td>
	                            <div style="display: none;" id="newPw2">
	                            	<input class="updateInfo" type="password" id="password2" name="password2" oninput="checkPassword2()" >
	                            	<span id="checkResult2"></span>
	                            </div>
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
									<input class="form-control" type="file" id="profile" name="file">
								</div>
							</td>
                        </tr>                        


                        <tr>
                            <td colspan="2" align="center">
	                            <input class="btnBox" type="submit" value="수정하기" >&nbsp;
				
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

    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	
	<script>
	const savedPw = "${members.pw}";
	var check1 = false; 
	var check2 = false;
	var check3 = false;
	
	function passConfirm() {
		
		const inputPw = document.getElementById("originPw").value;
		
		if (inputPw === "") {
		 	document.getElementById("passResult").innerHTML = "";
		
	 	} else if (inputPw === savedPw) {
			document.getElementById("passResult").innerHTML = "비밀번호가 일치합니다.";
			check1 = true;
			
		} else {
			document.getElementById("passResult").innerHTML = "비밀번호가 일치하지 않습니다.";
		}
		
		checkAllTrue();
	 	
	}
	
	function onUpdate() {
		
	    document.getElementById("newPw1").style.display = "";
	    document.getElementById("newPw2").style.display = "";
		
	}
	
	function onCancle() {
		
	    document.getElementById("newPw1").style.display = "none";
	    document.getElementById("newPw2").style.display = "none";
		
	}
	
 	function checkPassword1() {
 		
		const password = document.getElementById("password").value;
		const password2 = document.getElementById("password2").value;
		
		if (password === savedPw) {
			document.getElementById("checkResult1").innerHTML = "기존 비밀번호와 일치합니다. 다시 입력해주세요.";
			
		}else {
			document.getElementById("checkResult1").innerHTML = "사용가능한 비밀번호입니다.";
			check2 = true;
		}
		checkAllTrue();
	}
	
 	function checkPassword2() {
	
		const password = document.getElementById("password").value;
		const password2 = document.getElementById("password2").value;
		
		if (password !== password2){
			document.getElementById("checkResult2").innerHTML = "비밀번호가 일치하지 않습니다.";
			
		} else {
			document.getElementById("checkResult2").innerHTML = "비밀번호가 일치합니다.";
			check3 = true;
		}
		checkAllTrue();
	}	 
 	
	function checkAllTrue() {
		if (check1) {
			document.getElementById("f").submit();
		}
		
		else if (check1 && check2 && check3) {
			document.getElementById("f").submit();
		}
	}
	
	</script>
	
</body>
</html>