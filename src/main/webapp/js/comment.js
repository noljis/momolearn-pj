$(document).ready(function(){
    $("#loginPlz").click(function(){
		let currentPageUrl = window.location.href;
        console.log(currentPageUrl);
        let result = confirm('로그인 하시겠습니까?');
        if(result){
            location.href="http://localhost/momolearn/member/loginView?returnUrl="+encodeURIComponent(currentPageUrl);
        }else{

        }
    });
    
    readComment();
    
	
    $("#btn-comment").click(function() {
    	writeComment();
	});
	
    $("#btn-deleteCmt").click(function() {
    	deleteComment(comNo,cmtNo);
	});
	
    
});

$(document).on('click', '#btn-updateCmt', function() {
  const data = {
	comNo : $('#input-comNo').val(),
    cmtNo : $(this).closest('form').find('#input-cmtNo').val(),
    cmtContent : $(this).closest('form').find('#input-cmtContent').val()
  }
  const comNo = $('#board-comNo').val();
  $.ajax({
    type: 'PUT',
    url: '../board/'+comNo+'/comment/'+data.cmtNo,
    data: JSON.stringify(data),
    dataType: 'text',
    contentType: 'application/json; charset=utf-8',
    success:function(response){
		readComment();
    },
    error:function(error){
      alert('댓글 수정 실패. 다시 시도해주세요');
      alert(JSON.stringify(error));
    }
  });
});




function readComment(){
	const comNo = $('#board-comNo').val();
	const members = $('#session-members').val();
	const memId = $('#session-memId').val();
	
	$.ajax({
		type: 'GET',
		url: '../board/'+comNo+'/comment',
		success:function(response){
			let count = response.length;
			let html = '<h1 class="comments-title">댓글 '+count+'개</h1>';
			
			if(response.length>0){//댓글이 존재할경우
				for(let i in response){
					const cmtRegdate = new Date(response[i].cmtRegdate);
					const options = {
					  year: "numeric",
					  month: "2-digit",
					  day: "2-digit",
					  hour: "2-digit",
					  minute: "2-digit",
					  second: "2-digit",
					  hour12: false,
					};
					const formattedDate = cmtRegdate.toLocaleString("ko-KR", options);

					html += '<div class="be-comment" id="comment-block">';
					html += '<div class="be-img-comment"><a>';
					html += '<img class="profile-image rounded-circle" src="../img/profile/'+response[i].membersProfile+'" style="width: 50px; height: 50px; border: 2px solid #06BBCC;">';
					html += '</a></div>';
					html += '<div class="be-comment-content">';
				
					if(members.length === 0){//로그아웃일경우
						html += '<span><a>'+response[i].membersMemId+'</a></span>';
						
					}else{//로그인일경우
						html += '<span class="dropdown">'
						html += '<a href="javascript: void(0);" class="team-member dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" >';
						html += response[i].membersMemId+'</a>';
						
						if(memId == response[i].membersMemId){//내가 쓴 댓글일때
							html += '<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">';
							html += '<li><a class="dropdown-item" href="#" onclick="window.open(\''+contextPath+'/member-info?memId='+response[i].membersMemId+'\', \'memberInfo\', \'width=600, height=500\'); return false;">내 정보 보기</a></li>';
							html += '<li><a class="dropdown-item" href="#" onclick="window.open(\''+contextPath+'/board/search-one-member-posts?searchType=writer&searchText='+response[i].membersMemId+'\', \'memberInfo\', \'width=1500, height=600\'); return false;">내가 쓴 글 보기</a></li></ul>';							    
						}else{//남이 쓴 댓글일때
							html += '<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">';
							html += '<li><a class="dropdown-item" href="#" onclick="window.open(\''+contextPath+'/member-info?memId='+response[i].membersMemId+'\', \'memberInfo\', \'width=600, height=500\'); return false;">회원정보 보기</a></li>';
							html += '<li><a class="dropdown-item" href="#" onclick="window.open(\''+contextPath+'/board/search-one-member-posts?searchType=writer&searchText='+response[i].membersMemId+'\', \'memberInfo\', \'width=1500, height=600\'); return false;">작성한 글 보기</a></li></ul>';
						}
						html += '</span>';
					}
					html += '<span class="be-comment-time"><i class="fa fa-clock-o"></i>';
					html += formattedDate+'</span>';
					html += '<p class="be-comment-text">'+response[i].cmtContent+'</p>';
					html += '<form class="collapse" id="text-updateCmt-'+response[i].cmtNo+'" name="updateForm" >';
					html += '<input type="hidden" id="input-cmtNo" value="'+response[i].cmtNo+'" name="cmtNo">';
					html += '<input type="hidden" id="input-comNo" value="'+response[i].comNo+'" name="comNo">';
					html += '<div class="form-group">';
					html += '<textarea class="form-input2" id="input-cmtContent" name="cmtContent" required>'+response[i].cmtContent+'</textarea>';
					html += '</div>';
					html += '<button id="btn-updateCmt" class="btn btn-primary" type="button">수정하기</button>';
					html += '</form></div>';
					
					if(memId == response[i].membersMemId){//내가 쓴 댓글일때
						html += '<div id="btn-putdelete" class="list-unstyled list-inline media-detail pull-right">';
						html += '<a data-bs-toggle="collapse" href="#text-updateCmt-'+response[i].cmtNo+'" role="button" aria-expanded="false" aria-controls="text-updateCmt-'+response[i].cmtNo+'">수정</a>';
						html += '<a id="btn-deleteCmt" type="button" style="color: red" onclick="deleteComment('+comNo+','+response[i].cmtNo+')">삭제</a>';
						html += '</div>';
					}
					html += '</div><br><hr>';
				}
				$('#cmtTest').html(html);
				document.getElementById('cmtCount').innerHTML = '댓글 '+count;
			}else{//댓글이 없을경우
				html += '<p class="be-comment-text">등록된 댓글이 없습니다.</p>';
				$('#cmtTest').html(html);
				document.getElementById('cmtCount').innerHTML = '댓글 0';
			}
			
		},
		error:function(response){
			alert('댓글 불러오기 실패');
		}
	});
}

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
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success:function(response){
			$('#cmtContent').val('');
			readComment();
		},
		error:function(response){
			alert('댓글 등록 실패. 다시 시도해주세요');
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
			dataType: 'text',
			success:function(response){
				readComment();
			},
			error:function(response){
				alert('댓글 삭제 실패. 다시 시도해주세요');
			}
		});
	}
}
