<c:choose>
                	<c:when test="${empty members}">
                		<div>
               	<button id="btn-likeLogin" class="btn btn-primary"><i class='far fa-heart' style='font-size:15px'> 좋아요</i><br>
               	<p style="font-size: 10px; color: blue; margin: auto;" >(클릭시 로그인창으로 이동)</p></button>
                  </div>
                	</c:when>
                	<c:otherwise>
                  <c:choose >
                  <c:when test="${check}">
	<div>
		<button id="btn-cancel" class="btn btn-primary"><i class='fas fa-heart' style='font-size:15px'> 좋아요 취소</i></button>
	</div>
</c:when>
                  	
                  	<c:otherwise>
                    <div>
                 	<button id="btn-like" class="btn btn-primary"><i class='far fa-heart' style='font-size:15px'> 좋아요</i></button>
                    </div>
                  	</c:otherwise>
                  </c:choose>
                	</c:otherwise>
                </c:choose>

