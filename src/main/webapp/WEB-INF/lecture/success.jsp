<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 수정 성공</title>
</head>
<body onload='succ()'>
	
	<script type="text/javascript">
		const contextPath = '<%=request.getContextPath()%>';
		const lectureId = '<%=request.getAttribute("lectureId")%>';
		function succ() {
			alert('성공적으로 강좌 수정이 완료되었습니다!');
			location.href = contextPath + "/lectures/detail/" + lectureId;
		}
	</script>
</body>
</body>
</html>