<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<title>프로필 수정</title>
	
	<jsp:include page="/separate/head.jsp"></jsp:include>
</head>

<style>
.a {
	margin-right: 100px;
}
.table-hover tbody tr:hover {
    background-color: #EBFBFF;
  }
</style>

<body>
	<jsp:include page="/separate/header2.jsp"></jsp:include>

	<!-- Header Start -->
    <div class="container-fluid py-4">
        <div class="container">
            <div class="row justify-content">
                <div class="col-lg-10">
                    <div class="display-3" style="font-size: 20px;">내 정보 > 수정</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->
	
	<!-- Info Start -->
	<!-- Form Start -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<form name="f" id="sm" name="pej" action="${pageContext.request.contextPath}/member/update" method="post" enctype="multipart/form-data">
						<div align="center">
							<div class="card-header bg-primary"><h5 class="text-white font-weight-bold">${members.name}님의 정보 수정</h5></div>
							<!-- class="nav-item nav-link" -->
							<div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-hover">
											<tr>
												<td>아이디</td>
												<td>${members.memId}</td>
											</tr>
											
					                        <tr>
					                            <td>비밀번호</td>
					                            <td>
					                            	<input class="updateInfo" type="password" id="password" name="password" oninput="passConfirm1()" required>
					                            	<span id="passResult1"></span>
					                            	<input type="button"  onclick="onUpdate()" value="수정">
					                            	<input type="button"  onclick="onCancle()" value="취소">
					                            </td>
					                        </tr>
					                        
					                        <tr>
					                            <td>새비밀번호</td>
					                            <td>
					                          		<div style="display: none;" id="newPw">
					                            		<input class="updateInfo" type="password" id="newpw" name="newpw" value="" oninput="passConfirm2()">
					                            		<span id="passResult2"></span>
					                            	</div>
					                            </td>
					                        </tr>
					                        
											<tr>
												<td>이름</td>
												<td><input class="updateInfo" type="text" name="name" value="${members.name}" required></td>
											</tr>
											
											<tr>
												<td>이메일</td>
												<td>${members.email }</td>
											</tr>
	
											<tr>
												<td>등급</td>
												<td>${members.grade }</td>
											</tr>
	
											<tr>
												<td>프로필사진</td>
												<td>
													<div class="mb-3">
														<input class="form-control" type="file" id="profile" name="file" value="">
													</div>
												</td>
											</tr>
	
											<tr>
												<td colspan="2" align="center">
												<input class="btn btn-primary" id="submitBtn" type="submit" value="수정하기">&nbsp;
												<button class="btn btn-danger" type="reset" id="cancle">취소하기</button></td>
											</tr>
											
										</table>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	
	<script>
	
	const savedPw = "${members.pw}"; 
	
	var check1 = false; 
	var check2 = false; 
	
	var passwordInput = document.getElementById("newpw"); 
	
	passwordInput.value = null; 
	
	function passConfirm1() {
		
		const inputPw = document.getElementById("password").value;
		
		if (inputPw === "") {
			
		 	document.getElementById("passResult1").innerHTML = "";
		
	 	} else if (inputPw === savedPw) {
	 		
			document.getElementById("passResult1").innerHTML = "비밀번호 일치";
			check1 = true;
			document.getElementById("submitBtn").disabled = false;
			
		} else {
			
			document.getElementById("passResult1").innerHTML = "비밀번호 불일치";
			check1 = false;
			document.getElementById("submitBtn").disabled = true; 
		}
		
		checkAllTrue();
	}
	
	function passConfirm2() {
		
		const inputPw = document.getElementById("newpw").value;
		
		if (inputPw === "") {
			
		 	document.getElementById("passResult2").innerHTML = "";
		
	 	} else if (inputPw === savedPw) {
	 		
			document.getElementById("passResult2").innerHTML = "기존 비밀번호와 일치하여 변경 불가";
			check2 = false;
			document.getElementById("submitBtn").disabled = true;
			
		} else {
			
			document.getElementById("passResult2").innerHTML = "변경 가능";
			check2 = true;
			document.getElementById("submitBtn").disabled = false;
		}
		
		checkAllTrue();
	}
	
	function onUpdate() {

	    document.getElementById("newPw").style.display = "";
	}
	
	function onCancle() {
		
	    document.getElementById("newPw").style.display = "none"; 
	}
 
	function checkAllTrue() {
		
		if (check1 === true) {
			
			document.getElementById("f").submit();
		}
		
		else if ( check1 == true && check2 == true ) {
			
			document.getElementById("f").submit();
		}
		
	}
	
	</script>
	
</body>
</html>