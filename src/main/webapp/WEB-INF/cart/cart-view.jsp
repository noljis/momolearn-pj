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
  background-color: #dcdcdc;
  border-radius: 10px;
}
</style>

 <!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
 <!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

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
		<div class="container">
			<table class="table custom-table">
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="checkAll"></th>
						<th scope="col"><h5>강의명</h5></th>
						<th scope="col"><h5>가격</h5></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${cart}">
						<tr>
							<td scope="row"><input type="checkbox" id="check"></td>
							<td scope="row" id="title" class="text-secondary font-weight-bold">${cart.lecture.title}</td>
							<td scope="row" id="price" class="text-secondary font-weight-bold">${cart.lecture.price}원</td>
						</tr>
					</c:forEach>
					<c:if test="${empty cart}">
						<tr><td colspan="3" class="text-center"><h4 class="text-secondary" style="font-style: italic;">장바구니가 비어있습니다.</h4></td></tr>
					</c:if>
					<tr><td></td></tr>
					<tr>
						<td colspan="2" style="text-align: left;"><h6>구매 강의: <span id="checkedTitles" class="text-primary"></span></h6></td>
						<td colspan="1" style="text-align: right;"><h6>총 합계: <span id="totalPrice" class="text-primary">0</span>원</h6></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><!-- <button id="delete" class="btn btn-danger">삭제하기</button> --></td>
						<td></td>
						<td style="text-align: right;"><button id="payment" class="btn btn-primary mr-3" onclick="requestPay()">결제하기</button></td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- 장바구니 End -->
		<!-- 구매자 정보 hidden -->
		<input type="hidden" id="id" value="${members.memId}">
		<input type="hidden" id="name" value="${members.name}">
	</div>

	<script>
	  let checkAll = document.querySelector("#checkAll");	//전체 체크박스
	  let checkList = document.querySelectorAll("#check");	//체크 박스
	  let totalPrice = document.querySelector("#totalPrice");
	  
	  //체크박스 합계 구하기
	  function updateTotalPrice() {
	    let sum = 0;
	    let checkedTitles = []; // 체크된 강의명을 저장할 배열
	    //input#check:checked 체크된 속성만 반복문으로 돌려서 합산해줌
	    document.querySelectorAll("input#check:checked").forEach((item) => {
	      sum += parseInt(item.parentNode.parentNode.querySelector("#price").innerText.replace(",", "").replace("원", ""));
	      checkedTitles.push(item.parentNode.parentNode.querySelector("#title").innerText);
	    });
	    totalPrice.innerText = sum.toLocaleString();
	    document.querySelector("#checkedTitles").innerText = checkedTitles.length > 0 ? checkedTitles[0] + " 외 " + (checkedTitles.length - 1) + "개" : "";
	  }
	
	  checkAll.addEventListener("click", () => {
	    checkList.forEach((item) => {
	      item.checked = checkAll.checked;
	    });
	    updateTotalPrice();
	  });
	
	  checkList.forEach((item) => {
	    item.addEventListener("click", () => {
	    	//[...checkList]: nodeList(#check)를 배열로 반환. 배열을 돌면서 체크시켜줌
	      checkAll.checked = [...checkList].every((item) => item.checked);
	      updateTotalPrice();
	    });
	  });
	</script>


	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	<!-- axios 사용을 위한 추가 설정 -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/payment.js"></script>
</body>
</body>
</html>