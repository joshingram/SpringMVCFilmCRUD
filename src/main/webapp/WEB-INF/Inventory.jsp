<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Inventory details</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
		<link href="style.css" rel="stylesheet">
		<link rel="shortcut icon" href="images/sdvideologo.png">
	</head>
	<body>
		<div class="banner" onclick="location.href='index.html';">
		</div>
		<div class="container">
			<c:choose>
				<c:when test="${noInventory}">
					<li>No matching inventory found</li>
				</c:when>
				<c:otherwise>
					<div class="inventoryBanner">
						<h2>All inventory for film ID# ${film.id}: ${film.title}</h2>
						<hr>
					</div>
					<c:forEach items="${inventory}" var="inv">
						<h3>Inventory ID#: ${inv.id}</h3>
						<ul>
							<li>Film ID#: ${inv.filmId}</li>
							<li>Store ID#: ${inv.storeId}</li>
							<li>Media condition: ${inv.mediaCondition}</li>
							<c:if test="${not empty inv.customerFirstName}">	
								<li>Customer's name: ${inv.customerFirstName} ${inv.customerLastName}</li>
								<li>Customer's address: ${inv.address}</li> 
								<li>${inv.city}, ${inv.stateProvince} ${inv.postalCode} Country: ${inv.countryCode}</li>
							</c:if>				
						</ul>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<a href="index.html">Back to home</a>
		</div>
	</body>
</html>