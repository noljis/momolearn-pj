<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 강의</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<style>
.custom-table {
  background-color: #EBFBFF;
  border-radius: 10px;
}
.custom-table tr {
  padding-top: 20px;
  padding-bottom: 20px;
}
.custom-table th {
  padding-top: 20px;
  padding-bottom: 20px;
  font-size: 20px;
}
.custom-table td {
  padding-top: 20px;
  padding-bottom: 20px;
  font-size: 16px;
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
	                    <h1 class="display-4 text-white animated slideInDown">내 강의</h1>
	                    <nav aria-label="breadcrumb">
	                        <ol class="breadcrumb justify-content-center">
	                            <li class="breadcrumb-item text-primary active" aria-current="page"></li>
	                        </ol>
	                    </nav>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Header End -->
	    <!-- 수강중인 강의 Start -->
		<div class="container">
			<h1 class="text-center mb-5">수강중인 강의</h1>
			<table class="table custom-table">
				<thead>
					<tr>
						<th scope="col" colspan="1" class="text-primary">강의명</th>
						<th scope="col" colspan="1" class="text-primary">강사명</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lecture" items="${lecture}">
						<tr onclick="location.href='${pageContext.request.contextPath}/lectures/detail/${lecture.lecture.id}'" style="cursor: pointer;">
							<td><img src="${pageContext.request.contextPath}/img/lecture/${lecture.lecture.image}" class="card-img-top"
								alt="강의 이미지" style="width: 50px; height: 25px;">&nbsp;&nbsp;&nbsp; ${lecture.lecture.title}</td>
							<td>${lecture.lectureTeachersApplyTeacherMembers.name}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty lecture}">
						<tr><td class="text-center"><h4 class="text-secondary" style="font-style: italic;">현재 수강중인 강의가 없습니다.</h4></td></tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<br><br>
		<!-- 수강중인 강의 End -->	  
		<!-- 강사일 경우 강사의 강의 Start -->
		<c:if test="${members.grade == 'teacher'}">
			<div class="container">
				<h1 class="text-center mb-5">내가 올린 강의</h1>
				<table class="table custom-table table-bordered">
					<thead>
						<tr>
							<th scope="col" class="text-primary">강의명</th>
							<th scope="col" class="text-primary">강사명</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="teacher" items="${teacherLec}">
							<tr onclick="location.href='${pageContext.request.contextPath}/lectures/detail/${teacher.id}'" style="cursor: pointer;">
								<td><img src="${pageContext.request.contextPath}/img/lecture/${teacher.image}" class="card-img-top"
									alt="강의 이미지" style="width: 50px; height: 25px;">&nbsp;&nbsp;&nbsp; ${teacher.title}</td>
								<td>${teacher.teachersApplyTeacherMembers.name}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty lecture}">
							<tr><td class="text-center"><h4 class="text-secondary" style="font-style: italic;">현재 등록한 강의가 없습니다.</h4></td></tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- 강사일 경우 강사의 강의 End -->	      
	 </div>


	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</html>