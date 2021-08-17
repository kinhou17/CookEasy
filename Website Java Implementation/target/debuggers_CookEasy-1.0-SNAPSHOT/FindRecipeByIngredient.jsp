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
<title>Find Recipes By Ingredient Name</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
	<form action="findrecipebyingredient" method="post">
        <div class="jumbotron">
		<h1>Search for Recipes by ingredient name</h1>
        </div>
		<p>
			<label for="ingredientName">Ingredient Name</label>
			<input id="ingredientName" name="ingredientName" value="${fn:escapeXml(param.ingredientName)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
            <div class="alert alert-info" role="alert">
			<span id="successMessage"><b>${messages.success}</b></span>
            </div>
		</p>
	</form>
	<h1>Matching RecipeIngredients</h1>
        <table border="1" class="table table-striped">
            <tr>
                <th>RecipeID</th>
                <th>RecipeName</th>
                <th>Description</th>
                <th>DatePublished</th>
            </tr>
            <c:forEach items="${recipes}" var="recipes" >
                <tr>
                    <td><c:out value="${recipes.getRecipeID()}" /></td>
                    <td><c:out value="${recipes.getRecipeName()}" /></td>
                    <td><c:out value="${recipes.getDescription()}" /></td>
                    <td><c:out value="${recipes.getDatePublished()}" /></td>
                    <td><a href="findrecipebyrecipeid?recipeid=<c:out value="${recipes.getRecipeID()}"/>">Open</a></td>
                </tr>
            </c:forEach>
       </table>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
