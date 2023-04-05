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
    <link href="${pageContext.request.contextPath}/css/notice.css" rel="stylesheet">
    
	<style>
	    body {
		  margin-top: 0;
		  padding-top: 0;
		}
    </style>
</head>

<body>
<jsp:include page="/separate/header.jsp"></jsp:include>
<div id="searchList">

	
    <!-- Header Start -->
	    <div class="container-fluid py-4">
	        <div class="container">
	            <div class="row justify-content">
	                <div class="col-lg-10">
	                    <div class="display-3" style="font-size: 20px;">공지사항</div>
	                </div>
	            </div>
	        </div>
	    </div>
	<!-- Header End -->


	<div id="main-content" class="file_manager">
        <div class="container">
            <div class="row clearfix">
            
            
            <c:forEach items="${list}" var="c">
                <div class="col-lg-3 col-md-4 col-sm-12">
                    <div class="card">
                        <div class="file">
                            <a href="${pageContext.request.contextPath}/notice/${c.comNo}">
                            <div class="image">
	                            <c:set var="str" value="${c.comContent}"/>
	                            <c:choose>
		                            <c:when test="${fn:contains(str, '<figure class=\"image\">')}">
		                            	<c:set var="start" value="${fn:indexOf(str, '<figure class=\"image\">')}" />
										<c:set var="end" value="${fn:indexOf(str,  '</figure>')}" />
										<c:set var="imgSrc" value="${fn:substring(str, start+32, end-2)}"/>
										<div class="image-wrapper" >
											<img src="${imgSrc}">
										</div>
		                            </c:when>
		                            <c:otherwise>
			                            <div class="image-wrapper">
										  <img class="img-fluid" src="${pageContext.request.contextPath}/img/profile/admin.jpg" alt="img">
										</div>
		                            </c:otherwise>
	                            </c:choose>
                            </div>
                                <div class="file-name">
                                    <p class="m-b-5 text-muted">[${c.subject}]&nbsp;&nbsp;${c.comTitle}</p>
                                    <small>${c.member.name}<span class="date text-muted"><tf:formatDateTime value="${c.comRegdate}" pattern="yyyy.MM.dd" />&nbsp;&nbsp;
                                    <i class='fas fa-heart' style='font-size:15px;color: #ff93c5'>&nbsp;${fn:length(c.likes)}</i>
                                    <i class='far fa-eye' style='font-size:15px'>&nbsp;${c.comViewCount}</i>
                                    </span></small>
                                </div>
                            </a>
                        </div>
                    </div>
                </div> 
             </c:forEach>   
                
                <span>
                   <c:if test="${members.grade == 'admin'}">
                    <button class="btn btn-outline-danger" style="float: right;" type="submit" onclick="location.href='${pageContext.request.contextPath}/notice/writeform'">공지쓰기</button>
                   </c:if>
               </span>                            
            </div>
            
        </div>
    </div>


    <div class="pt-3">
        <ul class="pagination justify-content-center mb-0">
        
            <li class="page-item ${startPage == nowPage? 'disabled':''}">
                <a class="page-link" href="${pageContext.request.contextPath}/notice?page=${nowPage-2}" tabindex="-1" >Previous</a>
            </li>
            
            <c:forEach var="page" begin="${startPage}" end="${endPage}">
            
             <li class="page-item ${page == nowPage?'active':''}"><a class="page-link" href="${pageContext.request.contextPath}/notice?page=${page-1}"><c:out value="${page}"/></a></li>
            
            </c:forEach>
            
             <li class="page-item ${endPage == nowPage? 'disabled':''}">
                 <a class="page-link" href="${pageContext.request.contextPath}/notice?page=${nowPage}" tabindex="+1" >Next</a>
             </li>
        </ul>
    </div>
        				
</div>
<!-- Back to Top -->
<jsp:include page="/separate/script.jsp"></jsp:include>
<jsp:include page="/separate/footer.jsp"></jsp:include>

</body>

</html>