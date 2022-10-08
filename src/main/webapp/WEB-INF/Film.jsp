<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Film details</title>
	</head>
	<body>
		<c:forEach var="film" items="${films}">
			<h1>${film.title}</h1>
			<ul>
				<c:if test="${not empty film.description}"><li>${film.description}</li></c:if>
				<c:if test="${not empty film.year}"><li>Release date: ${film.year}</li></c:if>
				<c:if test="${not empty film.plainLanguage}"><li>${film.plainLanguage}</li></c:if>
				<c:if test="${not empty film.rating}"><li>${film.rating}</li></c:if>
				<c:if test="${not empty film.length}"><li>${film.length} minutes</li></c:if>
				<c:if test="${not empty film.category}"><li>${film.category}</li></c:if>
			</ul>
			<h3>Cast:</h3>
			<ul>
				<c:forEach var="actor" items="${film.actors}">
					<li>${actor.firstName} ${actor.lastName}</li>
				</c:forEach>
			</ul>
			<a href="editFilm.do">Update this film</a>
			<a href="removeFilm.do">Delete this film</a>
			<a href="viewInventory.do/?filmId=${film.id}">View available inventory</a>
		</c:forEach>
	</body>
</html>