<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actor details</title>
</head>
<body>
	<c:forEach items="${actors}" var="actor">
		<h1>Details for ${actor.firstName} ${actor.lastName}</h1>
		<ul>
			<li>${actor.firstName}</li>
			<li>${actor.lastName}</li>
			<li>Actor ID#: ${actor.id}</li>
		</ul>
	</c:forEach>
	
	<c:if test="${not empty actor}"><h3>The actor ${actor} has been added!</h3></c:if>
	
	<a href="removeActor.do?actorId=${actor.id}">Delete this Actor</a> 

</body>
</html>