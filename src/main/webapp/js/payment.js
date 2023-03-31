const IMP = window.IMP; // 생략 가능
IMP.init("imp08322828");

//주문번호 생성 uuid
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

function deleteCart() {
	const checkedTitles = Array.from(document.querySelectorAll("input#check:checked"));
	
	if (checkedTitles.length === 0 || checkedTitles[0] === undefined) {
		alert("삭제할 강의를 선택해주세요.");
		return;
	}
	
	const memId = $("#id").val();
	
	$.ajax({
		url: '/momolearn/cart/delete',
		type: 'DELETE',
		contentType: 'application/json',
		data: JSON.stringify({
			checkedTitles: checkedTitles.map(item => item.parentNode.parentNode.querySelector("#title").innerText),
			memId: memId
		}),
		success: function() {
			alert("해당 강의목록이 장바구니에서 삭제되었습니다.");
			let url = "/momolearn/cart/get-cart";
			window.location.href = url;
		},
		error: function(error) {
			console.error(error);
			alert("삭제에 실패했습니다. 원인: " + error.status + " " + error.statusText);
		}
	});
}
	
