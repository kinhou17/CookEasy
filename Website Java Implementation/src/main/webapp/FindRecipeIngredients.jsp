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
<title>Find a RecipeIngredients</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
	<form action="findrecipeingredients" method="post">
        <div class="jumbotron">
		<h1>Search for a FindRecipeIngredients by ingredient name</h1>
        </div>
		<p>
            <h2><label for="ingredientName">Ingredient Name</label></h2>
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
	<br/>
	<div id="recipeIngredientsCreate"><a href="recipeingredientscreate">Create RecipeIngredient</a></div>
	<br/>
	<h1>Matching RecipeIngredients</h1>
        <table border="1" class="table table-striped">
            <tr>
                <th>RecipeIngredientID</th>
                <th>RecipeID</th>
                <th>IngredientName</th>
                <th>Quantity</th>
            </tr>
            <c:forEach items="${recipeIngredients}" var="recipeIngredients" >
                <tr>
                    <td><c:out value="${recipeIngredients.getRecipeIngredientID()}" /></td>
                    <td><c:out value="${recipeIngredients.getRecipeID()}" /></td>
                    <td><c:out value="${recipeIngredients.getIngredientName()}" /></td>
                    <td><c:out value="${recipeIngredients.getQuantity()}" /></td>
                    <td><a href="recipeingredientsdelete?recipeIngredientID=<c:out value="${recipeIngredients.getRecipeIngredientID()}"/>">Delete</a></td>
                    <td><a href="recipeingredientsupdate?recipeIngredientID=<c:out value="${recipeIngredients.getRecipeIngredientID()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
    </div>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
       <script src="js/bootstrap.min.js"></script>
</body>
</html>
