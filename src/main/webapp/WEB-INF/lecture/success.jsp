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
			alert(msg);
			history.back();
		}
	</script>
</body>
</html>