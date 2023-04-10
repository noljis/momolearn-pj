<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성공</title>
</head>
<body onload='succ("${msg}")'>

	<script type="text/javascript">
		function succ(msg) {
			let result = confirm(msg);
			  if (result == true) {
			    window.location.href="/momolearn/cart/get-cart"
			  } else {
			    window.history.back();
			  }
		}
	</script>
</body>
</html>