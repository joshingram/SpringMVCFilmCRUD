<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inventory details</title>
</head>
<body>
	<c:if test="${empty inventory}">
		<li>No matching inventory found</li>
	</c:if>
	<H2> All inventory for film ID# ${film.id}: ${film.title}</H2>
	<c:forEach items="${inventory}" var="inv">
		<h3>Inventory ID#: ${inv.id}</h3>
		<ul>
			<li>Film ID#: ${inv.filmId}</li>
			<li>Store ID#: ${inv.storeId}</li>
			<li>Media condition: ${inv.mediaCondition}</li>
			<li>Customer's name: ${inv.customerFirstName} ${inv.customerLastName}</li>
			<li>Customer's address: ${inv.address}</li>
			${inv.city}, ${inv.stateProvince} ${inv.postalCode} Country: ${inv.countryCode}
		</ul>
		</c:forEach>
	
	<a href="index.html">Back to home</a>

</body>
</html>