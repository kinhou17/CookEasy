<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title><c:out value="${recipes.get(0).getRecipeName()}" /></title>
</head>
<body>
	<div class="container theme-showcase" role="main">
    <c:forEach items="${recipes}" var="recipes" >
		<h1><c:out value="${recipes.getRecipeName()}" /></h1>
		
		<h3>Description</h3>
		<p>
			<c:out value="${recipes.getDescription()}" />
		</p>
		
		<h3>RecipeInstructions</h3>
		<p>
			<c:out value="${recipes.getRecipeInstructions()}" />
		</p>
		
		<h4>Calories</h4>
		<p><c:out value="${recipes.getCalories()}" /></p>
		<h4>FatContent</h4>
		<p><c:out value="${recipes.getFatContent()}" /></p>
		<h4>SaturatedFatContent</h4>
		<p><c:out value="${recipes.getSaturatedFatContent()}" /></p>
		<h4>CholestrolContent</h4>
		<p><c:out value="${recipes.getCholestrolContent()}" /></p>
		<h4>SodiumContent</h4>
		<p><c:out value="${recipes.getSodiumContent()}" /></p>
		<h4>CarbohydrateContent</h4>
		<p><c:out value="${recipes.getCarbohydrateContent()}" /></p>
		<h4>FiberContent</h4>
		<p><c:out value="${recipes.getFiberContent()}" /></p>
		<h4>SugarContent</h4>
		<p><c:out value="${recipes.getSugarContent()}" /></p>
		<h4>ProteinContent</h4>
		<p><c:out value="${recipes.getProteinContent()}" /></p>
		
		<h3>DatePublished</h3>
		<p>
			<c:out value="${recipes.getDatePublished()}" />
		</p>
    </c:forEach>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
