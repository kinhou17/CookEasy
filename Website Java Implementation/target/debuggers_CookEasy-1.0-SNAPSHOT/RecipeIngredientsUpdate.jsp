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
<title>Update a RecipeIngredient</title>
</head>
<body>
	<div class="container theme-showcase" role="main">

	<div class="jumbotron">
	<h1>Update RecipeIngredient</h1>
	</div>
	<form action="recipeingredientsupdate" method="post">
		<p>
			<label for="recipeIngredientID">RecipeIngredientID</label>
			<input id="recipeIngredientID" name="recipeIngredientID" value="${fn:escapeXml(param.recipeIngredientID)}">
		</p>
		<p>
			<label for="quantity">New Quantity</label>
			<input id="quantity" name="quantity" value="">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
		</p>
	</form>
	<br/><br/>
	<p>
		<div class="alert alert-success" role="alert">
		<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</p>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>