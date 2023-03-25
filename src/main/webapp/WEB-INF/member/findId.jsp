<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="" name="keywords">
<meta content="" name="description">
<title>아이디 찾기</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/find.css">

	<script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>
	
	<jsp:include page="/separate/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../../separate/header4.jsp"></jsp:include>

	<div class="wrap">
		<div class="find">
			<form name="f"
				action="${pageContext.request.contextPath}/member/findId"
				method="post">
				<div class="w3-center w3-large w3-margin-top">
					<h3>아이디 찾기</h3>
				</div>
				<div>
					<p>
						<input type="text" id="email" name="email" placeholder="이메일을 입력해주세요."  required >
					</p>
					<p class="w3-center">
						<input type="submit" value="아이디찾기" id=findIdBtn onclick="blank()">
						<input type="button" value="뒤로가기" onclick="history.back()">
						<input type="button" value="메인화면"
							onclick="location.href='${pageContext.request.contextPath}/page/index.html'">
					</p>
				</div>
			</form>
		</div>
	</div>

	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	
<script>
	function blank() {
		//빈칸입력시 경고
		if (f.email.value == "") {
			alert("이메일을 입력하세요.");
			f.email.focus();
			return false;
		}
		f.submit();
	}
</script>
</body>
</html>