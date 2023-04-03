<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌등록</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<jsp:include page="/separate/head.jsp"></jsp:include>
<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.input-form {
	max-width: 1080px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
label {
  font-weight: bold;
  font-family: "Arial", sans-serif;
}
</style>
</head>
<body>
	<jsp:include page="/separate/header3.jsp"></jsp:include>
	
	<!-- Header Start -->
    <div class="container-fluid py-4">
        <div class="container">
            <div class="row justify-content">
                <div class="col-lg-10">
                    <div class="display-3 text-white" style="font-size: 20px;">강좌 등록 > " ${lectitle} "의 강좌 등록하기</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->
	<!-- form Start -->
	<form id="upload" method="post" >
		<div class="container">
			<div>
				<div class="input-form-backgroud row">
					<div class="input-form col-md-12 mx-auto">
						<h2 class="mb-2">" ${lectitle} "의 강좌 등록하기</h2>
						<div class="row" id="form-container">
							<div class="mb-3">
								<input id="lectureid" type="hidden" name="lectureId" value="${id}" />
							</div>
							<div class="row">
								<div class="col-md-4">
									<label for="title">강좌명<span class="text-muted">&nbsp;</span></label>
									<input type="text" class="form-control-plaintext form-control-sm border-bottom" name="title" placeholder="예) [1강]기초다지기">
								</div>
								<div class="col-md-5">
									<label for="time">강좌 URL<span class="text-muted">&nbsp;</span></label>
									<a href='http://embedresponsively.com/' target="_blank">반응형링크로 변환하기(click!)</a>
									<input type="text" class="form-control-plaintext form-control-sm border-bottom"	name="url" placeholder="반응형 링크로 올려주세요">
								</div>
								<div class="col-md-3">
									<label for="time">강좌 시간<span class="text-muted">&nbsp;</span></label>
									<input type="text" class="form-control-plaintext form-control-sm border-bottom"	name="time" placeholder="예) 00:12:36">
								</div>
							</div>
						</div>
						<br>
						<button class="btn btn-primary btn-lg btn-block" id="add-form" type="button">+</button>
						<button class="btn btn-primary btn-lg btn-block" id="delete-form" type="button">-</button>
						<button class="btn btn-primary btn-lg float-end" type="submit">강좌 등록하기</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script>
		let formCounter = 1;
        document.getElementById("add-form").addEventListener("click", function() {
		   let id = document.getElementById("lectureid").value;
           let formContainer = document.getElementById("form-container");
           let template = formContainer.children[0].cloneNode(true);
           formContainer.appendChild(template);
           
           let newForm = 
        	    `<div class="mb-3">
        	    </div>
        	    <div class="row">
        	        <div class="col-md-4">
        	            <label for="title">강좌명<span class="text-muted">&nbsp;</span></label>
        	            <input type="text" class="form-control-plaintext form-control-sm border-bottom" name="title" placeholder="예) [1강]기초다지기">
        	        </div>
        	        <div class="col-md-5">
        	            <label for="time">강좌 URL<span class="text-muted">&nbsp;</span></label>
        	            <a href='http://embedresponsively.com/' target="_blank">반응형링크로 변환하기(click!)</a>
        	            <input type="text" class="form-control-plaintext form-control-sm border-bottom" name="url" placeholder="반응형 링크로 올려주세요">
        	        </div>
        	        <div class="col-md-3">
        	            <label for="time">강좌 시간<span class="text-muted">&nbsp;</span></label>
        	            <input type="text" class="form-control-plaintext form-control-sm border-bottom" name="time" placeholder="예) 00:12:36">
        	        </div>
        	    </div>`;
        	    
				template.innerHTML += newForm; //폼 추가
				 // json 배열을 위한 고유한 name 속성 설정
			    template.querySelector('[name="lectureId"]').name = `lectureId${formCounter}`;
			    template.querySelector('[name="title"]').name = `title${formCounter}`;
			    template.querySelector('[name="url"]').name = `url${formCounter}`;
			    template.querySelector('[name="time"]').name = `time${formCounter}`;
			    
        	    formCounter++; // 카운트 증가
       });
       document.getElementById("delete-form").addEventListener("click", function() {
           let formContainer = document.getElementById("form-container");
           let formCount = formContainer.childElementCount;
           if (formCount > 1) { // 폼이 하나 이상인 경우에만 폼 삭제 가능
               formContainer.removeChild(formContainer.lastElementChild);
           	   formCounter--; //카운트 감소
           }
       });
       //버튼 이벤트
       document.getElementById("upload").addEventListener("submit", function(event) {
    	    event.preventDefault(); // 기본 동작 중단
    	    let form = event.target;
    	    let formData = new FormData(form); // 폼 데이터 가져오기
    	    console.log(formData);
    	    let jsonData = formDataToJson(formData); // FormData를 JSON으로 변환
    	    console.log(jsonData);
    	    sendJsonData(jsonData); // JSON 데이터를 서버에 보내기
    	});
      
		//json으로 변환
       function formDataToJson(formData) {
    	    let jsonData = {};

    	    for (let [key, value] of formData.entries()) {
    	        if (jsonData.hasOwnProperty(key)) {
    	            jsonData[key].push(value);
    	        } else {
    	            jsonData[key] = [value];
    	        }
    	    }

    	    return jsonData;
    	}
		//json으로 변환된 폼 보내기
    	function sendJsonData(jsonData) {
    	    axios({
    	        method: "POST",
    	        url: "/momolearn/lectures/upload-course",
    	        data: jsonData,
    	        headers: {
    	            "Content-Type": "application/json" 	//json
    	        }
    	    }).then(function(response) {
    	    	 alert("성공적으로 등록되었습니다! 강의 페이지로 이동합니다.");
    	    	 let id = response.data.id; // 서버에서 반환된 ID 값
    	         let url = "/momolearn/lectures/detail/" + id;
    	         window.location.href = url;
    	    }).catch(function(error) {
    	    	
    	    });
    	}
   </script>

	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>

</html>