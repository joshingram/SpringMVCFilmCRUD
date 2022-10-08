<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Film Updater</title>
	</head>
	<body>
		<form action="updateFilm.do?filmId=${film.id}" method="POST">
			<label for="title" required><strong>Title</strong></label>
			<input type="text" name="title" value="${film.title}"><br>
			
			<label for="description" required><strong>Description</strong></label><br>
			<input type="textbox" name="description" value="${film.description}"><br>
			
			<label for="year"><strong>Release Date</strong></label>
			<input type="year" name="year" value="${film.year}"><br>
			
			<label for="rentalDuration"><strong>Rental Duration</strong></label>
			<input type="number" name="rentalDuration" value="${film.rentalDuration}"><br>
			
			<label for="rentalRate"><strong>Rental Rate</strong></label>
			<input type="number" name="rentalRate" value="${film.rentalRate}"><br>
			
			<label for="length"><strong>Movie duration</strong></label>
			<input type="number" name="length" value="${film.length}"><br>
			
			<label for="replacementCost"><strong>Replacement Cost</strong></label>
			<input type="number" name="replacementCost" value="${film.replacementCost}"><br>
			
			<label for="rating"><strong>Rating</strong></label>
			<select name="rating">
				<c:choose>
					<c:when test="${film.rating == 'G'}">
						<option value="G" selected>G</option>
					</c:when>
					<c:otherwise>
						<option value="G">G</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					 <c:when test="${film.rating == 'PG'}">
						<option value="PG" selected>PG</option>
					</c:when>
					<c:otherwise>
						<option value="PG">PG</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${film.rating == 'PG13'}">
						<option value="PG13" selected>PG13</option>
					</c:when>
					<c:otherwise>
						<option value="PG13">PG13</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${film.rating == 'R'}">
						<option value="R" selected>R</option>
					</c:when>
					<c:otherwise>
						<option value="R">R</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${film.rating == 'NC17'}">
						<option value="NC17" selected>NC17</option>
					</c:when>
					<c:otherwise>
						<option value="NC17">NC17</option>
					</c:otherwise>
				</c:choose>
	
			</select>
			
			<div>
				<label for="specialFeatures"><strong>Special Features</strong></label><br>
				<input type="checkbox" name="specialFeatures" value="Trailers">Trailers</input>
				<input type="checkbox" name="specialFeatures" value="Commentaries">Commentaries</input>
				<input type="checkbox" name="specialFeatures" value="Deleted Scenes">Deleted Scenes</input>
				<input type="checkbox" name="specialFeatures" value="Behind the Scenes">Behind the Scenes</input>
			</div><br>
			
			<label for="plainLanguage">Language</label>
			<select name="plainLanguage">
				<option value="English" selected>English</option>
				<option value="Italian">Italian</option>
				<option value="Japanese">Japanese</option>
				<option value="Mandarin">Mandarin</option>
				<option value="French">French</option>
				<option value="German">German</option>
			</select><br>
			
			<label for="category">Category</label>
			<select name="category">
				<option value="Action">Action</option>
				<option value="Animation">Animation</option>
				<option value="Children">Children</option>
				<option value="Classics">Classics</option>
				<option value="Comedy">Comedy</option>
				<option value="Documentary">Documentary</option>
				<option value="Drama">Drama</option>
				<option value="Family">Family</option>
				<option value="Foreign">Foreign</option>
				<option value="Games">Games</option>
				<option value="Horror">Horror</option>
				<option value="Music">Music</option>
				<option value="New">New</option>
				<option value="Sci-Fi">Sci-Fi</option>
				<option value="Sports">Sports</option>
				<option value="Travel">Travel</option>
			</select><br>
			
			<button type="submit">Add to database</button>
		</form>
		
		<a href="index.html">Back to home</a>
	</body>
</html>