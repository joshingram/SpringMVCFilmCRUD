<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Film Updater</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
		<link href="style.css" rel="stylesheet">
		<link rel="shortcut icon" href="images/sdvideologo.png">
	</head>
	<body>
		<div class="banner">
		</div>
		<div class="container">
			<form action="updateFilm.do?filmId=${film.id}" method="POST">
				<label for="title" required><strong>Title</strong></label> <input
					type="text" name="title" value="${film.title}"><br> <label
					for="description" required><strong>Description</strong></label><br>
				<textarea name="description">${film.description}</textarea>
				<br> <label for="year"><strong>Release Date</strong></label> <input
					type="year" name="year" value="${film.year}"><br> <label
					for="rentalDuration"><strong>Rental Duration</strong></label> <input
					type="number" name="rentalDuration" value="${film.rentalDuration}"><br>
		
				<label for="rentalRate"><strong>Rental Rate</strong></label> <input
					type="number" name="rentalRate" value="${film.rentalRate}"><br>
		
				<label for="length"><strong>Movie duration</strong></label> <input
					type="number" name="length" value="${film.length}"><br>
		
				<label for="replacementCost"><strong>Replacement Cost</strong></label>
				<input type="number" name="replacementCost"
					value="${film.replacementCost}"><br> <label for="rating"><strong>Rating</strong></label>
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
					<label for="specialFeatures"><strong>Special
							Features</strong></label><br>
					<c:choose>
						<c:when test="${fn:contains(film.specialFeatures, 'Trailers')}">
							<input type="checkbox" name="specialFeatures" value="Trailers"
								selected>Trailers
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="specialFeatures" value="Trailers">Trailers
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(film.specialFeatures, 'Commentaries')}">
							<input type="checkbox" name="specialFeatures" value="Commentaries"
								checked>Commentaries
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="specialFeatures" value="Commentaries">Commentaries
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${fn:contains(film.specialFeatures, 'Deleted Scenes')}">
							<input type="checkbox" name="specialFeatures"
								value="Deleted Scenes" checked>Deleted Scenes
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="specialFeatures"
								value="Deleted Scenes">Deleted Scenes
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${fn:contains(film.specialFeatures, 'Behind the Scenes')}">
							<input type="checkbox" name="specialFeatures"
								value="Behind the Scenes" checked>Behind the Scenes
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="specialFeatures"
								value="Behind the Scenes">Behind the Scenes
						</c:otherwise>
					</c:choose>
				</div>
				<br> <label for="plainLanguage">Language</label> <select
					name="plainLanguage">
					<c:choose>
						<c:when test="${film.plainLanguage == 'English'}">
							<option value="English" selected>English</option>
						</c:when>
						<c:otherwise>
							<option value="English">English</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.plainLanguage == 'Italian'}">
							<option value="Italian" selected>Italian</option>
						</c:when>
						<c:otherwise>
							<option value="Italian">Italian</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.plainLanguage == 'Japanese'}">
							<option value="Japanese" selected>Japanese</option>
						</c:when>
						<c:otherwise>
							<option value="Japanese">Japanese</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.plainLanguage == 'Mandarin'}">
							<option value="Mandarin" selected>Mandarin</option>
						</c:when>
						<c:otherwise>
							<option value="Mandarin">Mandarin</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.plainLanguage == 'French'}">
							<option value="French" selected>French</option>
						</c:when>
						<c:otherwise>
							<option value="French">French</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.plainLanguage == 'German'}">
							<option value="German" selected>German</option>
						</c:when>
						<c:otherwise>
							<option value="German">German</option>
						</c:otherwise>
					</c:choose>
		
				</select><br> <label for="category">Category</label> <select
					name="category">
					<c:choose>
						<c:when test="${film.category == 'Action'}">
							<option value="Action" selected>Action</option>
						</c:when>
						<c:otherwise>
							<option value="Action">Action</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Animation'}">
							<option value="Animation" selected>Animation</option>
						</c:when>
						<c:otherwise>
							<option value="Animation">Animation</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Children'}">
							<option value="Children" selected>Children</option>
						</c:when>
						<c:otherwise>
							<option value="Children">Children</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Classics'}">
							<option value="Classics" selected>Classics</option>
						</c:when>
						<c:otherwise>
							<option value="Classics">Classics</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Comedy'}">
							<option value="Comedy" selected>Comedy</option>
						</c:when>
						<c:otherwise>
							<option value="Comedy">Comedy</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Documentary'}">
							<option value="Documentary" selected>Documentary</option>
						</c:when>
						<c:otherwise>
							<option value="Documentary">Documentary</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Drama'}">
							<option value="Drama" selected>Drama</option>
						</c:when>
						<c:otherwise>
							<option value="Drama">Drama</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Family'}">
							<option value="Family" selected>Family</option>
						</c:when>
						<c:otherwise>
							<option value="Family">Family</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Foreign'}">
							<option value="Foreign" selected>Foreign</option>
						</c:when>
						<c:otherwise>
							<option value="Foreign">Foreign</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Games'}">
							<option value="Games" selected>Games</option>
						</c:when>
						<c:otherwise>
							<option value="Games">Games</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Horror'}">
							<option value="Horror" selected>Horror</option>
						</c:when>
						<c:otherwise>
							<option value="Horror">Horror</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Music'}">
							<option value="Music" selected>Music</option>
						</c:when>
						<c:otherwise>
							<option value="Music">Music</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'New'}">
							<option value="New" selected>New</option>
						</c:when>
						<c:otherwise>
							<option value="New">New</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Sci-Fi'}">
							<option value="Sci-Fi" selected>Sci-Fi</option>
						</c:when>
						<c:otherwise>
							<option value="Sci-Fi">Sci-Fi</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Sports'}">
							<option value="Sports" selected>Sports</option>
						</c:when>
						<c:otherwise>
							<option value="Sports">Sports</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${film.category == 'Travel'}">
							<option value="Travel" selected>Travel</option>
						</c:when>
						<c:otherwise>
							<option value="Travel">Travel</option>
						</c:otherwise>
					</c:choose>
		
				</select><br>
		
				<button type="submit">Add to database</button>
			</form>
		
			<a href="index.html">Back to home</a>
		</div>
	</body>
</html>