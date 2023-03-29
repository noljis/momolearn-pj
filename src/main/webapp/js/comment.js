const main = {
	init : function(){
		const _this = this;
		document.querySelectorAll('#btn-updateCmt').forEach(function (item) {
	       item.addEventListener('click', function () { // 버튼 클릭 이벤트 발생시
	       const form = this.closest('form'); // btn의 가장 가까운 조상의 Element(form)를 반환 (closest)
	       _this.updateComment(form); // 해당 form으로 업데이트 수행
	       });
	    });
	},
	
	
	
	updateComment: function(form){
	const data = {
		cmtNo: form.querySelector('#cmtNo').value,
		comNo: form.querySelector('#comNo').value,
		cmtContent: form.querySelector('#cmtContent').value
	}
	alert("뭐냐고");
	alert(data);
	console.log(data);
	if(!data.cmtContent || data.cmtContent.trim() == ''){
		alert("댓글 내용을 입력해주세요.");
		return false;
	}
	$.ajax({
		type: 'PUT',
		url: '../board/'+data.comNo+'/comment/'+data.cmtNo,
		data: JSON.stringify(data),
		dataType: 'JSON',
		success:function(response){
			alert("댓글 수정 성공");
			window.location.reload();
		},
		error:function(response){
			alert('세션아웃 : 댓글 수정 실패. 새로고침 해주세요');
		}
	});
}

	
	
	
	
	
};

$(document).ready(function(){
	let _this = this;
    $("#loginPlz").click(function(){
		let currentPageUrl = window.location.href;
        console.log(currentPageUrl);
        let result = confirm('로그인 하시겠습니까?');
        if(result){
            location.href="http://localhost/momolearn/member/loginView?returnUrl="+encodeURIComponent(currentPageUrl);
        }else{

        }
    });
    
    //readComment();
	
	$("#btn-comment").click(function(){
		writeComment();
	});
	
/*	$('#btn-updateCmt').each(function(){
		$(this).click(function(){
		const form = $(this).closest('form');
		_this.updateComment(form);
		});
	});*/
	
	
});



/*function readComment(){
	const comNo = $('#boardComNo').val();
	
	$.ajax({
		type: 'GET',
		url: '../board/'+comNo+'/comment',
		success:function(response){
			console.log(response);
		},
		error:function(response){
			alert('세션아웃 : 댓글 불러오기 실패. 새로고침 해주세요');
			alert(response);
		}
	});
}*/

function writeComment(){
    const data = {
        comNo: $('#boardComNo').val(),
        cmtContent: $('#cmtContent').val(),
    }
    if(!data.cmtContent || data.cmtContent.trim() == ''){
		alert("댓글 내용을 입력해주세요.");
		return false;
	}
    $.ajax({
        type: 'POST',
        url: '../board/'+data.comNo+'/comment',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success:function(response){
			alert("댓글 등록 성공");
			$('#cmtContent').val('');
			window.location.reload();
		},
		error:function(response){
			alert('세션아웃 : 댓글 등록 실패. 새로고침 해주세요');
			alert(JSON.stringify(response));
		}
    });
}

function updateComment(form){
	const data = {
		cmtNo: form.querySelector('#cmtNo').value,
		comNo: form.querySelector('#comNo').value,
		cmtContent: form.querySelector('#cmtContent').value
	}
	console.log(data);
	if(!data.cmtContent || data.cmtContent.trim() == ''){
		alert("댓글 내용을 입력해주세요.");
		return false;
	}
	$.ajax({
		type: 'PUT',
		url: '../board/'+data.comNo+'/comment/'+data.cmtNo,
		data: JSON.stringify(data),
		dataType: 'JSON',
		success:function(response){
			alert("댓글 수정 성공");
			window.location.reload();
		},
		error:function(response){
			alert('세션아웃 : 댓글 수정 실패. 새로고침 해주세요');
		}
	});
}

function deleteComment(comNo, cmtNo){
	const check = confirm("댓글을 삭제하시겠습니까?");
	if(check){
		$.ajax({
			type: 'DELETE',
			url: '../board/'+comNo+'/comment/'+cmtNo,
			dataType: 'JSON',
			success:function(response){
				//alert("댓글 삭제 성공");
				window.location.reload();
			},
			error:function(response){
				alert('세션아웃 : 댓글 삭제 실패. 새로고침 해주세요');
			}
		});
	}
}
