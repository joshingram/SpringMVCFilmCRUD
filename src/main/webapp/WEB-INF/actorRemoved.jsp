<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ActorUpdate</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
		<link href="style.css" rel="stylesheet">
		<link rel="shortcut icon" href="images/sdvideologo.png">
	</head>
	<body>
		<div class="banner" onclick="location.href='index.html';">
		</div>

		<div class="container">
			<c:if test="${isDeleted}">
				<h1>Update Successful</h1>
			</c:if>
			<c:if test="${not isDeleted}">
				<h1 class="error">There was an error updating your actor.</h1>
			</c:if>
			
			<a href="index.html">Back to home</a>
		</div>
	</body>
</html>