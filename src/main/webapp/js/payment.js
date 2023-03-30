/*$(function(){
	$('#payment').click(function(){
		$.ajax({
			url: '/momolearn/cart/kakaopay.cls',
			dataType: 'json',
			success: function(data){
				let page = data.next_redirect_pc_url;
				window.open(page);
			},
			error: function(error){
				alert(error);
			}
		})
	})
})*/

const IMP = window.IMP; // 생략 가능
IMP.init("imp08322828");

//주문번호 생성 uuid. 16진수 문자 + 4개의 그룹, 8-4-4-4-12
//replace() 메서드를 사용하여 정규식 /[xy]/g에 매칭되는 문자를 인자로 받는 콜백 함수를 실행
//  /[xy]/g : x나 y라는 문자를 매칭하는 정규식
// 16진수 문자열은 Math.random() 난수로 랜덤 생성
//x에 해당하는 문자인 경우 0부터 f까지의 임의의 숫자를 생성
//y에 해당하는 문자인 경우 8, 9, a, 또는 b 중에서 임의로 선택한 숫자를 반환
//예 => 01234567-89ab-cdef-0123-456789abcdef
function uuidv4() {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		var r = Math.random() * 16 | 0,
			v = c == 'x' ? r : (r & 0x3 | 0x8);
		return v.toString(16);
	});
}

const merchant_uid = uuidv4();



//장바구니 결제
function requestPay() {
	//결제 금액이 0원일 경우
	const checkedTitles = Array.from(document.querySelectorAll("input#check:checked"));
	if (checkedTitles.length === 0 || checkedTitles[0] === undefined) {
		alert("결제할 강의를 선택해주세요.");
		return;
	}
	const totalPrice = $('#totalPrice').text();
	if (totalPrice === "0") {
		const memId = $("#id").val();
		
		$.ajax({
			url: '/momolearn/cart/success',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				checkedTitles: checkedTitles.map(item => item.parentNode.parentNode.querySelector("#title").innerText),
				memId: memId
			}),
			success: function() {
				alert("결제액이 없으므로 바로 수강신청 되었습니다! 내 강의 페이지로 이동합니다.");
				let url = "/momolearn/lectures/my-lecture";
				window.location.href = url;
			},
			error: function(error) {
				console.error(error);
				alert("결제에 실패했습니다. 잠시 후 다시 시도해 주십시오.");
			}
		});
		return;
	}

	// 결제금액이 0원이 아니면 IMP.request_pay() 함수 호출
	IMP.request_pay({
		pg: "kcp.store-060db7e3-01a1-4965-8ea2-9443b50c4f14",
		pay_method: "card",
		merchant_uid: merchant_uid,
		name: $('#checkedTitles').text(),
		amount: totalPrice,
		buyer_name: $('#name').val()
	}, function(rsp) {
		if (rsp.success) {
			const memId = $("#id").val();
			$.ajax({
				url: '/momolearn/cart/success',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
					checkedTitles: checkedTitles.map(item => item.parentNode.parentNode.querySelector("#title").innerText),
					memId: memId
				}),
				success: function() {
					alert("성공적으로 결제되었습니다! 내 강의 페이지로 이동합니다.");
					let url = "/momolearn/lectures/my-lecture";
					window.location.href = url;
				},
				error: function(error) {
					console.error(error);
					alert("결제에 실패했습니다. 잠시 후 다시 시도해 주십시오.");
				}
			});
		} else {
			alert('결제에 실패했습니다. 잠시 후 다시 시도해 주십시오.');
		}
	});
}

//단건 결제
function requestPayOne() {
	IMP.request_pay({
		pg: "kcp.store-060db7e3-01a1-4965-8ea2-9443b50c4f14",		//카카오페이로 신청
		pay_method: "card",
		merchant_uid: merchant_uid,   // 주문번호
		name: $('#checkedTitles').text(),		//제품명
		amount: $('#totalPrice').text(),                         // 숫자 타입 
		buyer_name: $('#name').val()
	}, function(rsp) { // callback
		if (rsp.success) {
			// 결제 성공 시 로직  $('#checkedTitles').text() 제품명, memId 필요

		} else {
			// 결제 실패 시 로직
		}
	});
}