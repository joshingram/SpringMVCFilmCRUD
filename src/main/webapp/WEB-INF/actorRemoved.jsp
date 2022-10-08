<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ActorUpdate</title>
	</head>
	<body>
		<c:if test="${isDeleted}">
			<h1>Update Successful</h1>
		</c:if>
		<c:if test="${not isDeleted}">
			<h1 class="error">There was an error updating your actor.</h1>
		</c:if>
		
		<a href="index.html">Back to home</a>
	</body>
</html>