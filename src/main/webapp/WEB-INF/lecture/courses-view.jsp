<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 시청 뷰</title>
<meta content="" name="keywords">
<meta content="" name="description">
<style>
.container-fluid {
	max-width: 1600px;
}
</style>

<jsp:include page="/separate/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/separate/header.jsp"></jsp:include>
	<!-- searchList: 스터디 검색시 비동기로 출력-->
	<div id="searchList">

		<div class="container-fluid">
		<br>
			<div class="row">
				<div class="col-12">
					<h3 class="text-center">강의명: 강좌명</h3>
				</div>
			</div>
			<div class="row d-flex">
				<!-- 왼쪽 동영상 -->
				<div class="col-12 col-md-9 flex-grow-1">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe class="embed-responsive-item"
							src="https://www.youtube.com/embed//Eb_-b2QKPBw" allowfullscreen
							style="width: 1015px; height: 600px;"></iframe>
					</div>
				</div>
				<!-- 강의 목록 -->
				<div class="col-12 col-md-3">
					<div id="accordion" style="height: 800px;">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">
									<button class="btn btn-link" data-toggle="collapse"
										data-target="#collapseOne" aria-expanded="true"
										aria-controls="collapseOne">강의 목록</button>
								</h5>
							</div>
							<div id="collapseOne" class="collapse show"
								aria-labelledby="headingOne" data-parent="#accordion">
								<div class="card-body">
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><a href="#">강좌 1</a></li>
										<li class="list-group-item"><a href="#">강좌 2</a></li>
										<li class="list-group-item"><a href="#">강좌 3</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>





	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</html>