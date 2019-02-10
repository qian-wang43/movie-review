<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Movies</title>
	<style type="text/css">
		body{
			background-color:#F8F8FF;
		}
		
	</style>
</head>
<body>
	<h1>Movies</h1>
	<c:if test="${requestScope.movieList.size()==0}">
		<h2>No such movie</h2>
	</c:if>
	<c:forEach var="item" items="${requestScope.movieList}">
		<h2>${item.title}</h2>
		Genre: ${item.genre} | Director: ${item.director} | Start Rating: ${item.star}<br/>
		<form method="post" action="HomeController">			
			<input type="text" name="mode" value="review" hidden="true" />
			<input type="text" name="movieId" value="${item.movieId}" hidden="true"/>
			<input type="submit" value="Reviews" />
		</form>
	</c:forEach>

	<form method="post" action="HomeController">
		<input type="text" name="mode" value="search" hidden="true" />
		Search: <input type="text" name="search" /><br />
		Sort By: 
		<select name="sort">
		  <option value ="starRating" selected >Star Rating</option>
		  <option value ="atoz">Title: A to Z</option>
		  <option value="ztoa">Title: Z to A</option>
		</select><br/>
		<input type="submit" value="Go!" />
	</form>
	
	<a href="HomeController"> Back to full list</a>
	
</body>
</html>