<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movies</title>
</head>
<body>
	<h1>Reviews</h1>
	<c:if test="${requestScope.reviewList.size()==0}">
		<h2>No Review Now</h2>
	</c:if>
	<c:if test="${requestScope.reviewList.size()!=0}">
		<c:forEach var="item" items="${requestScope.reviewList}">
			<p>Commented by : ${item.userName}</p>
			<p>${item.comment}</p> 
		</c:forEach>
	</c:if>
	<h2>Your Comments</h2>
	<form method="post" action="HomeController">
		<input type="text" name="mode" value="addReview" hidden="true" />	
		<input type="text" name="movieId" value="${requestScope.movieId}" hidden="true"/>	
		<p>User Name: <input type="text" name="userName" /></p>
		<p>Comments:<br/>	
		<textarea rows="3" cols="20" name="comment">
		</textarea></p>
		<input type="submit" value="Submit" />
	</form>
	
	<a href="HomeController"> Back to full list</a>
	
</body>
</html>