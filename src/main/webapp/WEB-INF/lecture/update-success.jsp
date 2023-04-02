<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("수정 성공"); %>
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
		console.log("강의Id" + lectureId);
		function succ() {
			alert('성공적으로 수정이 완료되었습니다!');
			location.href = contextPath + "/lectures/detail/" + lectureId;
		}
	</script>
</body>
</body>
</html>