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
    
	
	$(document).on('click', '#btn-updateCmt', function() {
	  const data = {
		comNo : $('#input-comNo').val(),
	    cmtNo : $(this).closest('form').find('#input-cmtNo').val(),
	    cmtContent : $(this).closest('form').find('#input-cmtContent').val()
	  }
	  $.ajax({
	    type: 'PUT',
	    url: '../board/'+data.comNo+'/comment/'+data.cmtNo,
	    data: JSON.stringify(data),
	    dataType: 'JSON',
	    contentType: 'application/json; charset=utf-8',
	    success:function(response){
	      alert("댓글 수정 성공");
	      window.location.reload();
	    },
	    error:function(error){
	      alert('댓글 수정 실패. 다시 시도해주세요');
	      alert(JSON.stringify(error));
	    }
	  });
	});
		
	
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
			alert('세션아웃 : 댓글 등록 실패. 다시 시도해주세요');
			alert(JSON.stringify(response));
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
				alert('댓글 삭제 실패. 다시 시도해주세요');
			}
		});
	}
}
