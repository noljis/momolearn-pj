<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("삭제 성공"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 삭제 성공</title>
</head>
<body onload='succ()'>
	
	<script type="text/javascript">
		const contextPath = '<%=request.getContextPath()%>';
		
		function succ() {
			
			alert('강의가 삭제되었습니다! 강의 목록페이지로 이동합니다.');
			location.href = contextPath + "/lectures/category-all";
			
		}
	</script>
</body>
</body>
</html>