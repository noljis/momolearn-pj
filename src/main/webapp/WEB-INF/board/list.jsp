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
<jsp:include page="/separate/header.jsp"></jsp:include>
<div id="searchList">
    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-4 text-white animated slideInDown">커뮤니티</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item text-white active" aria-current="page">페이지 간단 설명(생략가능)</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


    <!-- Categories Start -->
    <div class="container-xxl py-5 category">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h6 class="section-title bg-white text-center text-primary px-3">Community</h6>
                <h1 class="mb-5">Community Community</h1>
            </div>
        </div>
    </div>


    <!-- 게시판 시작 -->
    <div class="container">
        <div class="row">
            <div class="col-xl-3 col-md-6">
                <div class="card bg-pattern">
                    <div class="card-body">
                        <div class="float-right">
                            <i class="fa fa-archive text-primary h4 ml-3"></i>
                        </div>
                        <h5 class="font-size-20 mt-0 pt-1">24</h5>
                        <p class="text-muted mb-0">Total Projects</p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-md-6">
                <div class="card bg-pattern">
                    <div class="card-body">
                        <div class="float-right">
                            <i class="fa fa-th text-primary h4 ml-3"></i>
                        </div>
                        <h5 class="font-size-20 mt-0 pt-1">18</h5>
                        <p class="text-muted mb-0">Completed Projects</p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-md-6">
                <div class="card bg-pattern">
                    <div class="card-body">
                        <div class="float-right">
                            <i class="fa fa-file text-primary h4 ml-3"></i>
                        </div>
                        <h5 class="font-size-20 mt-0 pt-1">06</h5>
                        <p class="text-muted mb-0">Pending Projects</p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-md-6">
                <div class="card">
                    <div class="card-body">
                    <!-- 검색 폼 -->
                        <form method="get" name="search" action="${pageContext.request.contextPath}/board/search">
                            <div class="form-group mb-0">
                                <label>Search</label>
                                <div class="input-group mb-0">
	                                	<select name="searchType" style="border-color: #ced4da; color: #52565b">
											<option value="title" selected="selected">제목</option>
											<option value="content">내용</option>
											<option value="writer">글쓴이</option>
										</select>
                                    <input name="searchText" type="text" class="form-control" placeholder="Search..." aria-describedby="project-search-addon" />
                                    <div class="input-group-append">
                                        <button class="btn btn-danger" type="submit" id="project-search-addon"><i class="fa fa-search search-icon font-12"></i></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                      <!-- 검색 폼 끝 -->
                    </div>
                </div>
            </div>
        </div>
        <!-- end row -->

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
                                        <td><a href="${pageContext.request.contextPath}/board/${c.comNo}" id="title">${c.comTitle}&nbsp;[${fn:length(c.comments)}]</a></td>
                                        <td><tf:formatDateTime value="${c.comRegdate}" pattern="yyyy.MM.dd / HH:mm" /></td>
                                        <td>
                                            <div class="team">
                                                <a href="javascript: void(0);" class="team-member" data-toggle="tooltip" data-placement="top">
                                                    <img src="${pageContext.request.contextPath}/img/profile/${c.member.profile}" class="profile-image rounded-circle" style="width: 30px; height: 30px; border: 1px solid #06BBCC;" />&nbsp;${c.member.memId}
                                                </a>
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
                        
                        <button class="btn btn-outline-primary" style="float: right;" type="submit" onclick="location.href='${pageContext.request.contextPath}/board/writeForm'">글쓰기</button>
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
<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>

</html>