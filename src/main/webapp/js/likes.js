$(document).ready(function(){
    $("#btn-likeLogin").click(function(){
		let currentPageUrl = window.location.href;
        console.log(currentPageUrl);
        let result = confirm('로그인 하시겠습니까?');
        if(result){
            location.href="http://localhost/momolearn/member/loginView?returnUrl="+encodeURIComponent(currentPageUrl);
        }else{

        }
    });
    
    //실험
    const comNo = form.querySelector('#comNo').value;
    const memId= form.querySelector('#membersMemId').value;
		/*comNo: form.querySelector('#comNo').value,
		memId: form.querySelector('#membersMemId').value*/
    $.ajax({
		type: 'GET',
		url: '../likes/'+comNo,
		dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
		data: {
			"comNo": comNo,
			"memId": memId
			},
		success:function(response){
			alert("로딩완료");
			alert(response);
		},
		error:function(error){
			alert("로딩실패");
		}
	});
    
    
    
    
    
});
    $(document).on('click', '#btn-like', function() {
    $(this).html("<i class='fas fa-heart' style='font-size:15px'> 좋아요 취소</i>");
    $(this).attr('id', 'btn-cancel');
    const comNo = $('#boardComNo').val();
    $.ajax({
		type: 'POST',
		url: '../likes/'+comNo,
		data: {"comNo": comNo},
		contentType: 'application/json; charset=utf-8',
		success: function(response){
			$('#likesCount').html('좋아요 '+response);
			alert("좋아요를 눌렀습니다.");
		},
		error:function(error){
			alert("실패. 새로고침 해주세요");
		}
	});
  });
  
  $(document).on('click', '#btn-cancel', function() {
    $(this).html("<i class='far fa-heart' style='font-size:15px'> 좋아요</i>");
    $(this).attr('id', 'btn-like');
    const comNo = $('#boardComNo').val();
    $.ajax({
		type: 'DELETE',
		url: '../likes/'+comNo,
		data: {"comNo": comNo},
		contentType: 'application/json; charset=utf-8',
		success: function(response){
			$('#likesCount').html('좋아요 '+response);
			alert("좋아요를 취소했습니다.");
		},
		error:function(error){
			alert("실패. 새로고침 해주세요");
		}
	});
  });

