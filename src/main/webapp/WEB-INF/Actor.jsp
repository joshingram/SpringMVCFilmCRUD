<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Actor details</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
		<link href="style.css" rel="stylesheet">
		<link rel="shortcut icon" href="images/sdvideologo.png">
	</head>
<body>
		<div class="banner" onclick="location.href='index.html';">
		</div>
	<div class="container">
		<c:if test="${empty actors}">
			<li>No matching actors found</li>
		</c:if>
		<c:forEach items="${actors}" var="actor">
			<h1>Details for ${actor.firstName} ${actor.lastName}</h1>
			<ul>
				<li>${actor.firstName}</li>
				<li>${actor.lastName}</li>
				<li>Actor ID#: ${actor.id}</li>
			</ul>
	
			<a href="removeActor.do?actorId=${actor.id}">Delete this Actor</a> 
		</c:forEach>
		
		<c:if test="${not empty actor}"><h3>The actor ${actor} has been added!</h3></c:if>
		
		<a href="index.html">Back to home</a>
	</div>
</body>
</html>