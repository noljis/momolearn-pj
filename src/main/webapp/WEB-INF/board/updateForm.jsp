<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>MOMOLEARN</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <jsp:include page="/separate/head.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath}/css/write.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/ckeditor5/build/ckeditor.js"></script>
</head>
<body>
	<jsp:include page="/separate/header2.jsp"></jsp:include>
    <!-- Header Start -->
	    <div class="container-fluid py-4">
	        <div class="container">
	            <div class="row justify-content">
	                <div class="col-lg-10">
	                    <div class="display-3" style="font-size: 20px;">커뮤니티 > 수정</div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Header End -->


    <!---->
    <div class="container-xl px-4 mt-4">
        <!-- Account page navigation-->
        <div class="row">
            <div class="col-xl-8">
            </div>
            <div class="col-xl-8">
                <!-- Account details card-->
                <div class="card mb-4">
                    <div class="card-header">글 수정하기</div>
                    <div class="card-body">
                        <!-- Form Row-->
                        <form class="container" action="../${dto.comNo}" method="post">
                        	<input type="hidden" name="_method" value="put"/>
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComTitle">제목</label>
                                <input class="form-control" id="comTitle" name="comTitle" type="text" placeholder="제목을 입력하세요" value="${dto.comTitle}"/>
                            	<spring:hasBindErrors name="boardSaveDTO">
						            <c:if test="${errors.hasFieldErrors('comTitle') }">                                     
						              <span style="color: red;font:bold">${errors.getFieldError( 'comTitle' ).defaultMessage }</span>
									</c:if>
								</spring:hasBindErrors>
                            </div>
                            <div>
                            	<input id="type" name="type" value="community" type="hidden">
                            </div>
                            <div class="row gx-3 mb-3">
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputSubject">카테고리</label>
                                    <select name="subject" id="subject" class="form-control" >
	                                    <option value="">---선 택---</option>
										<option value="자유"<c:if test="${dto.subject eq '자유'}">selected</c:if>>자  유</option>
										<option value="질문"<c:if test="${dto.subject eq '잡담'}">selected</c:if>>질  문</option>
										<option value="정보"<c:if test="${dto.subject eq '정보'}">selected</c:if>>정  보</option>
										<option value="모집"<c:if test="${dto.subject eq '모집'}">selected</c:if>>모  집</option>
                                    </select>
                                    <spring:hasBindErrors name="boardSaveDTO">
							            <c:if test="${errors.hasFieldErrors('subject') }">                                     
							              <span style="color: red;font:bold">${errors.getFieldError( 'subject' ).defaultMessage }</span>
										</c:if>
									</spring:hasBindErrors>   
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputdMembersMemId">작성자</label>
                                    <input class="form-control" id="membersMemId" name="membersMemId" type="text" value="${members.memId}" readonly>
                                </div>
                            </div>
                            <!-- Form Row        -->
                            <!-- Form Group (email address)-->
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComContent">글 내용</label>
                                <textarea  rows="10" cols="50" id="comContent" name="comContent" class="form-control" placeholder="글 내용을 입력하세요" >${dto.comContent}</textarea>
                            	<spring:hasBindErrors name="boardSaveDTO">
						            <c:if test="${errors.hasFieldErrors('comContent') }">
						              <span style="color: red;font:bold">${errors.getFieldError( 'comContent' ).defaultMessage }</span>
									</c:if>
								</spring:hasBindErrors>
                            </div>
                            <!-- Save changes button-->
                            <button class="btn btn-primary" type="submit" >등록</button>
                            <button class="btn btn-primary" type="button" onclick="location.href='../'">글목록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!---->



    <!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	<!-- CKEditor -->
	<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
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
	})
	.catch(error => {
		console.error(error);
	});
	</script>
</body>

</html>