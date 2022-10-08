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
		<c:if test="${filmSearch}">
			<c:if test="${empty films}">
				<li>No matching film found</li>
			</c:if>
		</c:if>
		<c:forEach var="film" items="${films}">
			<h1>${film.title}</h1>
			<ul>
				<c:if test="${not empty film.id}"><li>Film ID: ${film.id}</li></c:if>
				<c:if test="${not empty film.description}"><li>${film.description}</li></c:if>
				<c:if test="${not empty film.year}"><li>Release date: ${film.year}</li></c:if>
				<c:if test="${not empty film.plainLanguage}"><li>${film.plainLanguage}</li></c:if>
				<c:if test="${not empty film.rating}"><li>${film.rating}</li></c:if>
				<c:if test="${not empty film.length}"><li>${film.length} minutes</li></c:if>
				<c:if test="${not empty film.category}"><li>${film.category}</li></c:if>
				<c:if test="${not empty film.specialFeatures}"><li>Special Features: ${film.specialFeatures}</li></c:if>
				<c:if test="${not empty film.rentalDuration}"><li>Rental Duration: ${film.rentalDuration} days</li></c:if>
				<c:if test="${not empty film.rentalRate}"><li>Rental Rate: $${film.rentalRate}</li></c:if>
				<c:if test="${not empty film.replacementCost}"><li>Replacement Cost: $${film.replacementCost}</li></c:if>
			</ul>
			
			<c:if test="${not empty film.actors}">
				<h3>Cast:</h3>
				<ul>
					<c:forEach var="actor" items="${film.actors}">
						<li>${actor.firstName} ${actor.lastName}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<c:if test="${filmAdded}">
				<p>Successfully updated.</p>
			</c:if>
			<c:if test="${not filmAdded and not filmSearch}">
				<p class="error">Error while trying to update your film</p>
			</c:if><br>
			
			<c:if test="${not empty film.id}">
			<a href="updateFilm.do?filmId=${film.id}">Update this film</a> || 
			<a href="removeFilm.do?filmId=${film.id}">Delete this film</a> || 
			<a href="viewInventory.do?filmId=${film.id}">View available inventory</a>
			</c:if>
		</c:forEach><br>
		<a href="index.html">Back to home</a>
	</body>
</html>