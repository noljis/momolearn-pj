<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <script src="${pageContext.request.contextPath}/js/ckeditor5/build/ckeditor.js"></script>
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
                            <h6><a href="#">${dto.subject}</a></h6><input id="boardComNo" type="hidden" value="${dto.comNo}">
                            <span id="id" style="display: none">${dto.comNo}</span>
                            <h2>${dto.comTitle }</h2>
                            <div class="media">
                                <div>
                                    <img class="profile-image rounded-circle" src="${pageContext.request.contextPath}/img/profile/${dto.member.profile}"
                                		style="width: 50px; height: 50px; border: 2px solid #06BBCC;">
                                </div>
                                <div class="media-body">
                                    <c:choose>
                                    <c:when test="${empty members}">
                                    	<label>${dto.member.memId}</label>
                                    </c:when>
                                    <c:otherwise>
	                                    <label class="dropdown">
	                                        <a href="javascript: void(0);" class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"  style="color: #fc5356" >${dto.member.memId}</a>
	                                        <c:choose>
		                                        <c:when test="${members.memId == dto.member.memId}">
		                                        	<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
														<li><a class="dropdown-item" href="#" onclick="window.open('${pageContext.request.contextPath}/member-info?memId=${dto.member.memId}', 'memberInfo', 'width=700, height=700'); return false;">내 정보 보기</a></li>
														<li><a class="dropdown-item" href="#" onclick="window.open('${pageContext.request.contextPath}/board/searchOneMemberPosts?searchType=writer&searchText=${dto.member.memId}', 'memberInfo', 'width=1500, height=700'); return false;">내가 쓴 글 보기</a></li>
						  							</ul>
		                                        </c:when>
		                                        <c:otherwise>
			                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
														<li><a class="dropdown-item" href="#" onclick="window.open('${pageContext.request.contextPath}/member-info?memId=${dto.member.memId}', 'memberInfo', 'width=700, height=700'); return false;">회원정보 보기</a></li>
														<li><a class="dropdown-item" href="#" onclick="window.open('${pageContext.request.contextPath}/board/searchOneMemberPosts?searchType=writer&searchText=${dto.member.memId}', 'memberInfo', 'width=1500, height=700'); return false;">작성한 글 보기</a></li>
						  							</ul>
					  							</c:otherwise>
				  							</c:choose>
	                                    </label>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="detail">
                                    <span>작성일 <fmt:parseDate value="${dto.comRegdate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="lastupdate" type="both" />
												<fmt:formatDate pattern="yyyy.MM.dd / HH : mm" value="${lastupdate}" /></span>
                                    <span style="float: right;" id="likesCount">좋아요 ${likesCount}</span>
                                    <span class="border-separator"style="float: right;">조회 ${dto.comViewCount }</span>
                                </div>
                            </div>
                        </div>
                        <div class="article-content">
                            <p id="comContent">${dto.comContent}</p>
                        </div>
                        <c:choose>
                        	<c:when test="${empty members}">
                        		<div>
			                    	<button id="btn-likeLogin" class="btn btn-primary"><i class='far fa-heart' style='font-size:15px'> 좋아요</i><br>
			                    	<p style="font-size: 10px; color: blue; margin: auto;" >(클릭시 로그인창으로 이동)</p></button>
		                        </div>
                        	</c:when>
                        	<c:otherwise>
								<c:set var="isLiked" value="false" />
								<c:forEach items="${likesList}" var="like">
								    <c:if test="${like.membersMemId == members.memId}">
								        <div>
								          <button id="btn-cancel" class="btn btn-primary">
								            <i class='fas fa-heart' style='font-size:15px'> 좋아요 취소</i>
								          </button>
								        </div>
								        <c:set var="isLiked" value="true" />
								    </c:if>
								</c:forEach>
								<c:if test="${not isLiked}">
								    <div>
								        <button id="btn-like" class="btn btn-primary">
								          <i class='far fa-heart' style='font-size:15px'> 좋아요</i>
								        </button>
						      		</div>
								</c:if>
                        	</c:otherwise>
                        </c:choose>
                    </article>

                    <button type="button" class="btn btn-primary" style="margin-top: 15px;" onclick="location.href='../notice'">목록</button>
                    <div class="text-right mt-3" style="float: right;">
                    <c:if test="${members.memId == dto.member.memId}">
                        <button type="button" class="btn btn-primary" onclick="location.href='../notice/updateForm/${dto.comNo}'">수정</button>&nbsp;
                        <form id="delete_form" action="../notice/${dto.comNo}" method="post"  style="float:left;margin:0;">
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
	<script src="${pageContext.request.contextPath}/js/likes.js"></script>
	<!-- CKEditor -->
	<!-- <script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
	<script>
	ClassicEditor.create(document.querySelector('#comContent'), {
		removePlugins: [ 'Heading' ],
		ckfinder: {
			uploadUrl : '${pageContext.request.contextPath}/board/image/upload'
		},
		fontFamily: {
			options: [
				'default',
				'Arial',
				'궁서체',
				'바탕',
				'돋움'
			],
			supportAllValues: true
		}
	})
	.then(editor => {
		console.log('Editor was initialized');
		editor.isReadOnly; // `false`.
		editor.enableReadOnlyMode( '#comContent' );
		editor.isReadOnly; // `true`.
		const toolbarElement = editor.ui.view.toolbar.element;
		toolbarElement.style.display = 'none';
	})
	.catch(error => {
		console.error(error);
	});
	</script> -->
</body>

</html>