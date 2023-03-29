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
	
		<!-- Header Start -->
	    <div class="container-fluid bg-primary py-5 mb-5 page-header">
	        <div class="container py-5">
	            <div class="row justify-content-center">
	                <div class="col-lg-10 text-center">
	                    <h1 class="display-4 text-white animated slideInDown">강의 상세보기</h1>
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

		<!-- 강의정보 단락 -->
		<div class="container-fluid bg-secondary py-5 d-flex justify-content-center align-items-center">
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
						<h3 class="mb-4">강좌 리스트
						<c:if test="${members.memId eq lecture.teachersApplyTeacherMembers.memId}">
							<button class="btn btn-secondary mr-2" onclick="location.href='${pageContext.request.contextPath}/lectures/courses-form/${lecture.title}/${lecture.id}'">강의 추가하기</button>
						</c:if>
						</h3>
						
						<div style="max-width: 800px; margin: 0;">
							<table class="table table-striped table-hover"
								style="background-color: #dcdcdc;">
								<tbody>
									<c:forEach var="course" items="${lecture.courses}">
										<tr onclick="location.href='${pageContext.request.contextPath}/lectures/check-mylecture/${course.courseId}'">
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
			<div class="card mb-3 text-center" style="width: 300px; height: 100%; border-radius: 20px; right: 50px;">
				<div class="card-body mb-3">
					<h4 class="card-title spoqa-han-sans">${lecture.title}</h4>
					<p class="card-text mb-3">${lecture.info}</p>
					<br>
					<c:choose>
						<c:when test="${empty myLecture}">
							<h4 class="card-title text-primary">${lecture.price}￦</h4>
							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/cart/pay-lecture/${lecture.id}'">결제하기💳</button>
								<button type="submit" class="btn btn-secondary mr-2" onclick="location.href='${pageContext.request.contextPath}/cart/check-cart/${lecture.id}'">수강바구니🧺</button>
								<input type="hidden" name="lecId" value="${lecture.id}"/>
							</div>
						</c:when>
						<c:otherwise>
							<h3 class="card-title text-primary">수강중😊</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- 결제 박스 -->
	</div>

	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</html>