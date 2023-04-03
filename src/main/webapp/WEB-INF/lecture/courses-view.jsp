<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 시청 뷰</title>
<meta content="" name="keywords">
<meta content="" name="description">
<style>
.container-fluid {
	max-width: 1600px;
}
</style>

<jsp:include page="/separate/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/separate/header.jsp"></jsp:include>
	<!-- searchList: 스터디 검색시 비동기로 출력-->
	<div id="searchList">
		<div class="container-fluid">
		<br>
			<div class="row">
				<div class="col-12">
					<div class="display-3" style="font-size: 20px;">강의 > 강의 상세 > 강의 시청</div>
					<br>
					<h3 class="">🖥️ ${lecture.title} 🪄 ${course.title}</h3>
				</div>
			</div>
			<div class="row d-flex">
				<!-- 왼쪽 동영상 -->
				<div class="col-12 col-md-9 flex-grow-1">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe class="embed-responsive-item"
							src="${course.url}" allowfullscreen
							style="width: 1015px; height: 600px;"></iframe>
					</div>
				</div>
				<!-- 강의 목록 -->
				<div class="col-12 col-md-3">
					<div id="accordion" style="height: 800px;">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">
									<button class="btn btn-link" data-toggle="collapse"
										data-target="#collapseOne" aria-expanded="true"
										aria-controls="collapseOne">강의 목록</button>
								</h5>
							</div>
							<div id="collapseOne" class="collapse show"
								aria-labelledby="headingOne" data-parent="#accordion">
								<div class="card-body">
									<ul class="list-group list-group-flush">
										<c:forEach var="courses" items="${lecture.courses}">
											<c:choose>
												<c:when test="${courses.courseId eq course.courseId}">
													<li class="list-group-item" style="font-weight: bold; background-color: #f2f2f2;">${courses.title}</li>
												</c:when>
												<c:otherwise>
													<li class="list-group-item"><a href="${pageContext.request.contextPath}/lectures/check-mylecture/${courses.courseId}">${courses.title}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	</div>





	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</html>