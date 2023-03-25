<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 상세보기 뷰</title>
<meta content="" name="keywords">
<meta content="" name="description">
<style>
table {
	width: 100%;
	max-width: 800px;
	margin: 0 auto;
	border-collapse: collapse;
	border: 1px solid #dee2e6;
}

th, td {
	padding: 0.75rem;
	border: 1px solid #dee2e6;
}

th {
	background-color: #f8f9fa;
	font-weight: bold;
}

tr:nth-child(odd) {
	background-color: #f2f2f2;
}

.card-body {
	display: flex;
	flex-direction: column;
	justify-content: flex-end;
}
</style>
<jsp:include page="/separate/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/separate/header.jsp"></jsp:include>
	<!-- searchList: 스터디 검색시 비동기로 출력-->
	<div id="searchList">

		<!-- 강의정보 단락 -->
		<div
			class="container-fluid bg-secondary py-5 d-flex justify-content-center align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-md-5">
						<div class="card text-center">
							<img src="${pageContext.request.contextPath}/img/lecture/${lecture.image}" class="card-img-top"
								alt="강의 이미지" style="width: 100%; height: 300px;">
						</div>
					</div>
					<div class="col-md-6">
						<h2 class="spoqa-han-sans text-white">🖥️ ${lecture.title}</h2><br>
						<h4 class="spoqa-han-sans text-warning"> ${lecture.info}</h4>
						<p class="spoqa-han-sans text-light"> ${lecture.description}</p>
					</div>
				</div>
			</div>
		</div>

		<!-- 강좌 리스트 단락 수강여부 조회해서 활성화 / 비활성화 -->
		<div class="container-fluid bg-white py-5">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h3 class="mb-4">강좌 리스트</h3>
						<div style="max-width: 800px; margin: 0;">
							<table class="table table-striped table-hover"
								style="background-color: #dcdcdc;">
								<tbody>
									<c:forEach var="course" items="${lecture.courses}">
										<tr onclick="location.href='강의번호';">
											<td style="background-color: #dcdcdc;">${course.title}</td>
											<td style="background-color: #dcdcdc; text-align: right;">${course.time}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 강좌 단락 끝 -->


		<!-- 결제 박스 / 이미 수강중인 경우 : 수강중인 강좌입니다.(이전 컨트롤러에서 mylecture 조회)-->
		<div class="position-fixed top-50 end-0">
			<div class="card mb-3 text-center"
				style="width: 300px; height: 250px; border-radius: 20px; right: 50px;">
				<form method="POST" action="/payment">
					<div class="card-body mb-3">
						<h4 class="card-title spoqa-han-sans">${lecture.title}</h4>
						<p class="card-text mb-3">${lecture.info}</p>
						<br>
						<h4 class="card-title text-primary">${lecture.price}￦</h4>
						<div class="d-grid gap-2">
							<button type="submit" name="pay" class="btn btn-primary">결제하기</button>
							<button type="submit" name="cart" class="btn btn-secondary mr-2">장바구니</button>
							<%-- <input type="hidden" name="memId" value="${members.memId}"/> --%>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 결제 박스 -->
	</div>




	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</html>