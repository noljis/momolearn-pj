<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="${pageContext.request.contextPath}/css/read.css" rel="stylesheet">
</head>

<body>

<jsp:include page="/separate/header2.jsp"></jsp:include>

    <!-- 블로그 시작-->
    <div class="blog-single gray-bg">
        <div class="container">
            <div class="row align-items-start">
                <div class="col-lg-8 m-15px-tb" style="margin-left: 230px;">
                    <article class="article">
                        <div class="article-title">
                            <h6><a href="#">${dto.subject}</a></h6>
                            <span id="id" style="display: none">${dto.comNo}</span>
                            <h2>${dto.comTitle }</h2>
                            <div class="media">
                                <div class="avatar">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" title="" alt="">
                                </div>
                                <div class="media-body">
                                    <label>${dto.membersMemId}</label>
                                </div>
                                <div class="detail">
                                    <span>작성일 <tf:formatDateTime value="${dto.comRegdate}" pattern="yyyy-MM-dd HH:mm" /></span>
                                    <span style="float: right;">댓글 ${fn:length(cmtList)}</span>
                                    <span style="float: right;">추천 0</span>
                                    <span style="float: right;">조회 ${dto.comViewCount }</span>
                                </div>
                            </div>
                        </div>
                        <div class="article-content">
                            <p>${dto.comContent}</p>
                        </div>
                        
                        <c:choose >
                        	<c:when test="${check}">
		                        <div>
			                    	<button id="btn-like" class="btn btn-primary"><i class='fas fa-heart' style='font-size:15px'> 좋아요 취소</i></button>
		                        </div>
                        	</c:when>
                        	<c:otherwise>
		                        <div>
			                    	<button id="btn-like" class="btn btn-primary"><i class='far fa-heart' style='font-size:15px'> 좋아요</i></button>
		                        </div>
                        	</c:otherwise>
                        </c:choose>
                        
                        
                    </article>
                    
                    <div class="contact-form article-comment">
                        
                        <div class="container">
                            <div class="be-comment-block" >
                                <h1 class="comments-title">댓글 ${fn:length(cmtList)}개</h1>
                                <c:choose>
	                                <c:when test="${empty cmtList}">
		                                <p class="be-comment-text">
			                                 등록된 댓글이 없습니다.
			                            </p>
	                                </c:when>
	                                <c:otherwise>
		                                <c:forEach items="${cmtList}" var="c">
			                                <div class="be-comment" id="comment-block">
			                                    <div class="be-img-comment">
			                                        <a href="blog-detail-2.html">
			                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" class="be-ava-comment">
			                                        </a>
			                                    </div>
			                                    <div class="be-comment-content">
			                                        
			                                            <span class="be-comment-name">
			                                                <a href="blog-detail-2.html">${c.membersMemId}</a>
			                                                </span>
			                                            <span class="be-comment-time">
			                                                <i class="fa fa-clock-o"></i>
			                                                <tf:formatDateTime value="${c.cmtRegdate}" pattern="yyyy-MM-dd HH:mm" />
			                                            </span>
			                            
			                                        <p class="be-comment-text">
			                                            ${c.cmtContent}
			                                        </p>
			                                        <form class="collapse" id="text-updateCmt-${c.cmtNo}" name="updateForm" >
														<input type="hidden" id="cmtNo" value="${c.cmtNo}" name="cmtNo">
														<input type="hidden" id="comNo" value="${dto.comNo}" name="comNo">
														<div class="form-group">
														  <textarea class="form-input2" name="cmtContent" required>${c.cmtContent}</textarea> 
														</div>
														<button id="btn-updateCmt" class="btn btn-primary">수정하기</button>
													</form>
			                                    </div>
			                                    <c:if test="${sessionScope.members.memId == c.membersMemId }">
				                                    <div id="btn-putdelete" class="list-unstyled list-inline media-detail pull-right">
							                            <a data-bs-toggle="collapse" href="#text-updateCmt-${c.cmtNo}" role="button" aria-expanded="false" aria-controls="text-updateCmt-${c.cmtNo}">수정</a>
							                            <a id="btn-deleteCmt" type="button" style="color: red" onclick="deleteComment(${dto.comNo},${c.cmtNo})">삭제</a>
							                        </div>
						                        </c:if>
			                                </div>
			                                <br><hr>
			                            </c:forEach>    
	                                </c:otherwise>
                                </c:choose>
                                <c:choose>
                                	<c:when test="${empty members}">
                                		<form class="form-block">
		                                    <div class="row">
		                                        <div class="col-xs-12">									
		                                            <div class="form-group">
		                                                <textarea id="loginPlz" class="form-input" style="color: blue" readonly>댓글을 작성하려면 로그인 해주세요.(클릭시 로그인창으로 이동)</textarea>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </form>
                                	</c:when>
                                	<c:otherwise>
		                                <form class="form-block" >
                                            <input id="boardComNo" type="hidden" value="${dto.comNo}">
                                            <h1 class="comments-title">댓글 작성</h1>
		                                    <div class="row">
		                                        <div class="col-xs-12 col-sm-6">
		                                            <div class="form-group fl_icon">
		                                                <input class="form-input" id="membersMemId" type="text" value="${members.memId}" readonly>
		                                            </div>
		                                        </div>
		                                        <div class="col-xs-12">									
		                                            <div class="form-group">
		                                                <textarea class="form-input" id="cmtContent" placeholder="내용 입력" required></textarea>
		                                            </div>
		                                        </div>
		                                        <div>
		                                        	<button id="btn-comment" type="button" class="btn btn-primary pull-right" >댓글 등록</button>
		                                        	<!-- <button id="btn-refresh" type="button" style="float: right" class="btn btn-primary pull-right" ><i class='fas fa-sync'></i> 댓글</button> -->
		                                        	
		                                        </div>
		                                    </div>
		                                </form>
	                                </c:otherwise>
                                </c:choose>
                            </div>
                            </div>
                    </div>

                    <button type="button" class="btn btn-primary" style="margin-top: 15px;" onclick="location.href='../board'">목록</button>
                    <div class="text-right mt-3" style="float: right;">
                    <c:if test="${members.memId == dto.membersMemId}">
                        <button type="button" class="btn btn-primary" onclick="location.href='../board/updateForm/${dto.comNo}'">수정</button>&nbsp;
                        <form id="delete_form" action="../board/${dto.comNo}" method="post"  style="float:left;margin:0;">
					    <input type="hidden" name="_method" value="delete"/>
					    <a onclick="if (confirm('정말로 삭제하시겠습니까?')) document.getElementById('delete_form').submit();" class="btn btn-danger">삭제</a>&nbsp;
						</form>
					</c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 블로그 끝 -->
 

    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/comment.js"></script>
	<script src="${pageContext.request.contextPath}/js/likes.js"></script>
</body>

</html>