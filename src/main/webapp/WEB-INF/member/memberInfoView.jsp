<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<title>프로필 정보</title>
	
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

	<!-- Header Start -->
	<div class="container-fluid bg-primary py-5 mb-5 page-header">
		<div class="container py-5">
			<div class="row justify-content-center">
				<div class="col-lg-10 text-center">
					<c:choose>
						<c:when test="${members.memId == member.memId}">
							<h1 class="display-4 text-white animated slideInDown">내 정보</h1>
						</c:when>
						<c:otherwise>
							<h1 class="display-4 text-white animated slideInDown">회원 정보</h1>
						</c:otherwise>
					</c:choose>
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
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header bg-primary">
						<h5 class="text-white font-weight-bold">${member.memId}님의 정보</h5>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<tbody>
									<tr>
										<th>아이디</th>
										<td>
											${member.memId}
										</td>
									</tr>
									<tr>
										<th>이름</th>
										<td>
										<c:choose>
											<c:when test="${members.memId == member.memId}">
												${member.name}
											</c:when>
											<c:otherwise>
												${fn:substring(member.name,0,1) }
												<c:forEach begin="2" end="${fn:length(member.name)}" step="1">*</c:forEach>
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
									<tr>
										<th>이메일</th>
										<td>
										<c:choose>
											<c:when test="${members.memId == member.memId}">
												${member.email}
											</c:when>
											<c:otherwise>
												${fn:substring(member.email,0,1)}
												<c:forEach begin="2" end="${fn:length(fn:substringBefore(member.email,'@'))}" step="1">*</c:forEach>
												@${fn:substringAfter(member.email,'@') }
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
									<tr>
										<th>등급</th>
										<td>${member.grade}</td>
									</tr>
									<tr>
										<th>가입일</th>
										<td>
											<fmt:parseDate value="${member.regdate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="lastupdate" type="both" />
											<fmt:formatDate pattern="yyyy.MM.dd" value="${lastupdate}" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<c:if test="${members.memId == member.memId}">
							<div class="form-group row mb-0">
								<div class="col-md-6 offset-md-4">
									<button class="btn btn-primary"
										onclick="location.href='${pageContext.request.contextPath}/member/updatepage?memId=${members.memId}'">
										수정하러 가기</button>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Info End -->
	

</body>

</html>
