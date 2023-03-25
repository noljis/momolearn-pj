<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성공뷰</title>
</head>
<body onload='succ("${errorMsg}")'>

	<script type="text/javascript">
		function error(msg) {
			alert('예외 발생: ' + msg);
			history.back();
		}
	</script>
</body>
<body onload='succ()'>
	<script>
		function showConfirmation() {

			// 알림창 생성
			var confirmation = confirm("강의를 성공적으로 등록했습니다. 강좌를 지금 등록하시겠습니까?");

			// '예' 버튼 클릭 시
			if (confirmation) {
				// TODO: 강좌 등록 로직 실행
				// 다음 페이지로 이동
				window.location.href = "다음 페이지 URL";
			}
			// '아니오' 버튼 클릭 시
			else {
				// 메인페이지로 이동
				window.location.href = "메인페이지 URL";
			}
		}
	</script>
</body>
</html>