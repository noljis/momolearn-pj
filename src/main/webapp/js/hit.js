$(document).ready(function(){
  let hitPosts = [];
  let currentPost = 0;
  let hitPosts2 = [];
  let currentPost2 = 0;
  let hitPosts3 = [];
  let currentPost3 = 0;
  
  $.ajax({
    url: '../hit-board?criteria=comment',
    dataType: 'JSON',
    success: function(response){
      hitPosts = response;
	  let html = '';
	  let head = '';
	  head += '<div class="text-center wow fadeInUp" data-wow-delay="0.1s">';
      head += '<h6 class="section-title bg-white text-center text-primary px-3">실시간 인기글</h6>';
      head += '</div>';
      
	  html += '<span class="col-xl-3 col-md-6"><div class="card bg-pattern">';
	  html += '<div class="card-body"><div class="float-right">';
	  html += '<i class="fas fa-comment-dots" style="font-size:18px;color: #06BBCC"> 실시간 댓글 Top5</i>';
	  html += '</div><h5 class="font-size-20 mt-0 pt-1"></h5>';
	  html += '<div id="hit-comment" >';
	  html += '<ul></ul></div></div></div></span>';
	  
  $('#hit-head').html(head);
  $('#hit-comment-sec').html(html);
      showNextPost();
      setInterval(showNextPost, 2000);
    }
  });
  function showNextPost(){
    $('#hit-comment').html('<li><a href="'+hitPosts[currentPost].comNo+'">'+'['+hitPosts[currentPost].subject+']  '+hitPosts[currentPost].comTitle+'</a></li>');
    $(hitPosts[currentPost]).fadeIn(500, function(){
      currentPost = (currentPost+1) % hitPosts.length;
      $(hitPosts[currentPost]).fadeOut(500);
    });
  }
  
  
 $.ajax({
    url: '../hit-board?criteria=comViewCount',
    dataType: 'JSON',
    success: function(response){
      hitPosts2 = response;
	  let html = '';
      
	  html += '<span class="col-xl-3 col-md-6"><div class="card bg-pattern">';
	  html += '<div class="card-body"><div class="float-right">';
	  html += '<i class="far fa-eye" style="font-size:18px;color: #06BBCC"><b> 실시간 조회수 Top5</b></i>';
	  html += '</div><h5 class="font-size-20 mt-0 pt-1"></h5>';
	  html += '<div id="hit-comViewCount" >';
	  html += '<ul></ul></div></div></div></span>';
	  
  $('#hit-comViewCount-sec').html(html);
      showNextPost2();
      setInterval(showNextPost2, 2000);
    }
  });
  function showNextPost2(){
    $('#hit-comViewCount').html('<li><a href="'+hitPosts2[currentPost2].comNo+'">'+'['+hitPosts2[currentPost2].subject+']  '+hitPosts2[currentPost2].comTitle+'</a></li>');
    $(hitPosts2[currentPost2]).fadeIn(500, function(){
      currentPost2 = (currentPost2+1) % hitPosts2.length;
      $(hitPosts2[currentPost2]).fadeOut(500);
    });
  }
  
  
  $.ajax({
    url: '../hit-board?criteria=likes',
    dataType: 'JSON',
    success: function(response){
      hitPosts3 = response;
	  let html = '';
      
	  html += '<span class="col-xl-3 col-md-6"><div class="card bg-pattern">';
	  html += '<div class="card-body"><div class="float-right">';
	  html += '<i class="fas fa-fire-alt" style="font-size:18px;color: #06BBCC"> 실시간 좋아요 Top5</i>';
	  html += '</div><h5 class="font-size-20 mt-0 pt-1"></h5>';
	  html += '<div id="hit-likes" >';
	  html += '<ul></ul></div></div></div></span>';
	  
  $('#hit-likes-sec').html(html);
      showNextPost3();
      setInterval(showNextPost3, 2000);
    }
  });
  function showNextPost3(){
    $('#hit-likes').html('<li><a href="'+hitPosts3[currentPost3].comNo+'">'+'['+hitPosts3[currentPost3].subject+']  '+hitPosts3[currentPost3].comTitle+'</a></li>');
    $(hitPosts3[currentPost3]).fadeIn(500, function(){
      currentPost3 = (currentPost3+1) % hitPosts3.length;
      $(hitPosts3[currentPost3]).fadeOut(500);
    });
  }
  
  
});

/*function getHitData(criteria, callback) {
  $.ajax({
    url: '../hit-board?criteria=' + criteria,
    dataType: 'JSON',
    success: function(response){
      callback(response);
    }
  });
}

function showHitPosts(criteria, targetElemId) {
  let hitPosts = [];
  let currentPost = 0;
  getHitData(criteria, function(response) {
    hitPosts = response;
    showNextPost();
    setInterval(showNextPost, 2000);
  });

  function showNextPost() {
    $('#' + targetElemId).html('<li><a href="' + hitPosts[currentPost].comNo + '">' + hitPosts[currentPost].comTitle + '</a></li>');
    $(hitPosts[currentPost]).fadeIn(500, function(){
      currentPost = (currentPost+1) % hitPosts.length;
      $(hitPosts[currentPost]).fadeOut(500);
    });
  }
}

$(document).ready(function(){
  showHitPosts('comViewCount', 'hit-comViewCount');
  showHitPosts('comment', 'hit-comment');
  showHitPosts('likes', 'hit-likes');
  
});*/