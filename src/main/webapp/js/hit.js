function getHitData(criteria, callback) {
  $.ajax({
    url: 'hit-board?criteria=' + criteria,
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
    $('#' + targetElemId).html('<li><a href="board/' + hitPosts[currentPost].comNo + '">' + '['+hitPosts[currentPost].subject+']  '+hitPosts[currentPost].comTitle + '</a></li>');
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
  
});