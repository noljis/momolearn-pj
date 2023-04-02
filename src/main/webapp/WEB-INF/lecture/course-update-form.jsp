<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌등록</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<jsp:include page="/separate/head.jsp"></jsp:include>
<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.input-form {
	max-width: 1080px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
label {
  font-weight: bold;
  font-family: "Arial", sans-serif;
}
</style>
</head>
<body>
	<jsp:include page="/separate/header3.jsp"></jsp:include>
	<!-- form Start -->
		<div class="container">
			<div>
				<div class="input-form-backgroud row">
					<div class="input-form col-md-12 mx-auto">
							<h2 class="mb-2">" ${course.title} " 수정하기</h2>
							<form name="update" action="${pageContext.request.contextPath}/lectures/update-course" method="post" >
							<input type="hidden" name="_method" value="put"/>
							<div class="row" id="form-container">
								<div class="mb-3">
									<input id="lectureid" type="hidden" name="courseId" value="${course.courseId}" />
								</div>
								<div class="row">
									<div class="col-md-4">
										<label for="title">강좌명<span class="text-muted">&nbsp;</span></label>
										<input type="text" class="form-control-plaintext form-control-sm border-bottom" name="title" value="${course.title}" placeholder="예) [1강]기초다지기">
									</div>
									<div class="col-md-5">
										<label for="time">강좌 URL<span class="text-muted">&nbsp;</span></label>
										<a href='http://embedresponsively.com/' target="_blank">반응형링크로 변환하기(click!)</a>
										<input type="text" class="form-control-plaintext form-control-sm border-bottom"	name="url" value="${course.url}" placeholder="반응형 링크로 올려주세요">
									</div>
									<div class="col-md-3">
										<label for="time">강좌 시간<span class="text-muted">&nbsp;</span></label>
										<input type="text" class="form-control-plaintext form-control-sm border-bottom"	name="time" value="${course.time}" placeholder="예) 00:12:36">
									</div>
								</div>
							</div>
							<br>
							<button class="btn btn-primary btn-lg float-end" type="submit">강좌 수정하기</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>

</html>