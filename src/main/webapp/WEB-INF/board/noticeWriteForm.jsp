<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<script src="../js/ckeditor5/build/ckeditor.js"></script>
	
</head>
<body>

	<jsp:include page="/separate/header2.jsp"></jsp:include>

    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-4 text-white animated slideInDown">공지사항</h1>
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
                    <div class="card-header">공지쓰기</div>
                    <div class="card-body">
                        <!-- Form Row-->
                        <form:form modelAttribute="boardSaveDTO" class="container" action="${ pageContext.servletContext.contextPath }/notice" method="post">
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComTitle">제목</label>
                                <form:input path="comTitle" class="form-control" id="comTitle" name="comTitle" type="text" placeholder="제목을 입력하세요"/>
                                <spring:hasBindErrors name="boardSaveDTO">
						            <c:if test="${errors.hasFieldErrors('comTitle') }">                                     
						              <span style="color: red;font:bold">${errors.getFieldError( 'comTitle' ).defaultMessage }</span>
									</c:if>
								</spring:hasBindErrors>
                            </div>
                            <div>
                            	<input id="type" name="type" value="notice" type="hidden">
                            </div>
                            <div class="row gx-3 mb-3">
                                <div class="col-md-6">
                                    <label class="small mb-1" for="inputSubject">카테고리</label>
                                    <form:select path="subject" name="subject" id="subject" class="form-control">
                                        <form:option value="">---선 택---</form:option>
                                        <form:option value="공지">공  지</form:option>
                                        <form:option value="이벤트">이 벤 트</form:option>
                                    </form:select>
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
                            <div class="mb-3">
                                <label class="small mb-1" for="inputComContent">공지 내용</label>
                                <form:textarea path="comContent" rows="10" cols="50" id="comContent" name="comContent" class="form-control" placeholder="글 내용을 입력하세요"/>
                            	<spring:hasBindErrors name="boardSaveDTO">
						            <c:if test="${errors.hasFieldErrors('comContent') }">                                     
						              <span style="color: red;font:bold">${errors.getFieldError( 'comContent' ).defaultMessage }</span>
									</c:if>
								</spring:hasBindErrors>
								<hr>
								
                            </div>
                            <!-- Save changes button-->
                            <button class="btn btn-primary" type="submit" >등록</button>
                            <button class="btn btn-primary" type="button" onclick="location.href='${pageContext.request.contextPath}/notice'">공지목록</button>
                        </form:form>
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