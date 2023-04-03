 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>MOMOLEARN</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <jsp:include page="/separate/head.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath}/css/board.css" rel="stylesheet">
</head>

<body>
<div id="searchList">
    <!-- Header Start -->
	    <div class="container-fluid py-4">
	        <div class="container">
	            <div class="row justify-content">
	                <div class="col-lg-10">
	                    <div class="display-3" style="font-size: 20px;">작성글 보기</div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Header End -->

    <!-- 게시판 시작 -->
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive project-list">
                            <table class="table project-table table-centered table-nowrap">
                                <thead>
                                    <tr>
                                        <th scope="col">번호</th>
                                        <th scope="col">카테고리</th>
                                        <th scope="col">제목</th>
                                        <th scope="col">작성일</th>
                                        <th scope="col">글쓴이</th>
                                        <th scope="col">조회</th>
                                        <th scope="col">좋아요</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<!-- 게시글 시작 -->
                                <c:forEach items="${list}" var="c">
                                    <tr>
                                        <th scope="row">${c.comNo}</th>
                                        <td>
                                            <span class="text-success font-12"><i class="mdi mdi-checkbox-blank-circle mr-1"></i>${c.subject}</span>
                                        </td>
                                        <td><a href="${pageContext.request.contextPath}/board/${c.comNo}" onclick="window.opener.location.href='${pageContext.request.contextPath}/board/${c.comNo}'; window.close(); return false;" id="title">${c.comTitle}&nbsp;[${fn:length(c.comments)}]
                                        <c:set var="str" value="${c.comContent}"/>
                                        <c:if test="${fn:contains(str, '<figure class=\"image\">')}">
                                        <i class='fas fa-file-image' style="color: #ffc107"></i></c:if></a></td>
                                        <td><tf:formatDateTime value="${c.comRegdate}" pattern="yyyy.MM.dd / HH:mm" /></td>
                                        <td>
                                            <div class="dropdown">
                                                <a href="javascript: void(0);" class="team-member dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" >
                                                    <img src="${pageContext.request.contextPath}/img/profile/${c.member.profile}" class="profile-image rounded-circle" style="width: 30px; height: 30px; border: 1px solid #06BBCC;" />
                                                    &nbsp;${c.member.memId}
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
												    <li><a class="dropdown-item" href="#" onclick="window.open('${pageContext.request.contextPath}/member-info?memId=${c.member.memId}', 'memberInfo', 'width=700, height=700'); return false;">회원정보 보기</a></li>
												    <li><a class="dropdown-item" href="#" onclick="window.open('${pageContext.request.contextPath}/board/searchOneMemberPosts?searchType=writer&searchText=${c.member.memId}', 'memberInfo', 'width=1500, height=700'); return false;">작성한 글 보기</a></li>
			  									</ul>
                                            </div>
                                        </td>
                                        <td>
                                            <p class="mb-0">${c.comViewCount}</p>
                                        </td>

                                        <td>
                                            <p class="mb-0">${fn:length(c.likes)}</p>
                                        </td>
                                    </tr>
                                </c:forEach>
                                    <!-- 게시글 끝-->
                                </tbody>
                            </table>
                        </div>
                        <!-- end project-list -->
                        <div class="pt-3">
                            <ul class="pagination justify-content-center mb-0">
                            
                                <li class="page-item ${startPage == nowPage? 'disabled':''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/board?page=${nowPage-2}" tabindex="-1" >Previous</a>
                                </li>
                                
                                <c:forEach var="page" begin="${startPage}" end="${endPage}">
                                
	                                <li class="page-item ${page == nowPage?'active':''}"><a class="page-link" href="${pageContext.request.contextPath}/board?page=${page-1}"><c:out value="${page}"/></a></li>
                                
                                </c:forEach>
                                
	                                <li class="page-item ${endPage == nowPage? 'disabled':''}">
	                                    <a class="page-link" href="${pageContext.request.contextPath}/board?page=${nowPage}" tabindex="+1" >Next</a>
	                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end row -->
    </div>
        
</div>
<!-- Back to Top -->
<jsp:include page="/separate/script.jsp"></jsp:include>
</body>

</html>