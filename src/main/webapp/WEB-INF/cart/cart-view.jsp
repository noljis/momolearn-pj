<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의목록</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<style>
.custom-table {
  background-color: #EBFBFF;
  border-radius: 10px;
}
</style>
<jsp:include page="/separate/head.jsp"></jsp:include>

</head>
<body>
	<jsp:include page="/separate/header.jsp"></jsp:include>
	<!-- searchList: 스터디 검색시 비동기로 출력-->
	<div id="searchList">
		<!-- Header Start -->
		<div class="container-fluid bg-primary py-5 mb-5 page-header">
			<div class="container py-5">
				<div class="row justify-content-center">
					<div class="col-lg-10 text-center">
						<h1 class="display-4 text-white animated slideInDown">수강 바구니</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb justify-content-center">
								<li class="breadcrumb-item text-white active"
									aria-current="page"></li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- Header End -->

		<!-- 장바구니 Start -->
		<div class="container mt-3">
			<table class="table custom-table">
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="checkAll"></th>
						<th scope="col" class=""><h5>강의명</h5></th>
						<th scope="col"><h5>가격</h5></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${cart}">
						<tr>
							<td scope="row"><input type="checkbox" id="check"></td>
							<td scope="row" id="title">${cart.lecture.title}</td>
							<td scope="row" id="price">${cart.lecture.price}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"><span>총 합계: <span id="totalPrice"
								class="text-left"></span></span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="1" class="left"><button class="btn btn-danger">삭제하기</button>
						<td colspan="1"></td>
						<td colspan="1" class="text-right"><button
								class="btn btn-primary mr-3">결제하기</button></td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- 장바구니 End -->
	</div>

	<script>
	  let checkAll = document.querySelector("#checkAll");	//전체 체크박스
	  let checkList = document.querySelectorAll("#check");	//체크 박스
	  let totalPrice = document.querySelector("#totalPrice");
	  //체크박스 합계 구하기
	  function updateTotalPrice() {
	    let sum = 0;
	    //input#check:checked 체크된 속성만 반복문으로 돌려서 합산해줌
	    document.querySelectorAll("input#check:checked").forEach((item) => {
	      sum += parseInt(item.parentNode.parentNode.querySelector("#price").innerText.replace(",", "").replace("원", ""));
	    });
	    totalPrice.innerText = sum.toLocaleString() + "원";
	  }
	
	  checkAll.addEventListener("click", () => {
	    checkList.forEach((item) => {
	      item.checked = checkAll.checked;
	    });
	    updateTotalPrice();
	  });
	
	  checkList.forEach((item) => {
	    item.addEventListener("click", () => {
	      checkAll.checked = [...checkList].every((item) => item.checked);
	      updateTotalPrice();
	    });
	  });
	</script>


	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
</body>
</body>
</html>