package model;

import java.sql.Date;

public class Recipes {
	private int recipeID;
	private String recipeName;
	private String prepTime;
	private String cookTime;
	private Date datePublished;
	private String description;
	private String images;
	private double calories;
	private double fatContent;
	private double saturatedFatContent;
	private double cholestrolContent;
	private double sodiumContent;
	private double carbohydrateContent;
	private double fiberContent;
	private double sugarContent;
	private double proteinContent;
	private double recipeServings;
	private String recipeInstructions;

	public Recipes(int recipeID, String recipeName, String prepTime, String cookTime, Date datePublished,
			String description, String images, double calories, double fatContent, double saturatedFatContent,
			double cholestrolContent, double sodiumContent, double carbohydrateContent, double fiberContent,
			double sugarContent, double proteinContent, double recipeServings, String recipeInstructions) {
		this.recipeID = recipeID;
		this.recipeName = recipeName;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.datePublished = datePublished;
		this.description = description;
		this.images = images;
		this.calories = calories;
		this.fatContent = fatContent;
		this.saturatedFatContent = saturatedFatContent;
		this.cholestrolContent = cholestrolContent;
		this.sodiumContent = sodiumContent;
		this.carbohydrateContent = carbohydrateContent;
		this.fiberContent = fiberContent;
		this.sugarContent = sugarContent;
		this.proteinContent = proteinContent;
		this.recipeServings = recipeServings;
		this.recipeInstructions = recipeInstructions;
	}

	public Recipes(String recipeName, String prepTime, String cookTime, Date datePublished, String description,
			String images, double calories, double fatContent, double saturatedFatContent, double cholestrolContent,
			double sodiumContent, double carbohydrateContent, double fiberContent, double sugarContent,
			double proteinContent, double recipeServings, String recipeInstructions) {
		this.recipeName = recipeName;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.datePublished = datePublished;
		this.description = description;
		this.images = images;
		this.calories = calories;
		this.fatContent = fatContent;
		this.saturatedFatContent = saturatedFatContent;
		this.cholestrolContent = cholestrolContent;
		this.sodiumContent = sodiumContent;
		this.carbohydrateContent = carbohydrateContent;
		this.fiberContent = fiberContent;
		this.sugarContent = sugarContent;
		this.proteinContent = proteinContent;
		this.recipeServings = recipeServings;
		this.recipeInstructions = recipeInstructions;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getFatContent() {
		return fatContent;
	}

	public void setFatContent(double fatContent) {
		this.fatContent = fatContent;
	}

	public double getSaturatedFatContent() {
		return saturatedFatContent;
	}

	public void setSaturatedFatContent(double saturatedFatContent) {
		this.saturatedFatContent = saturatedFatContent;
	}

	public double getCholestrolContent() {
		return cholestrolContent;
	}

	public void setCholestrolContent(double cholestrolContent) {
		this.cholestrolContent = cholestrolContent;
	}

	public double getSodiumContent() {
		return sodiumContent;
	}

	public void setSodiumContent(double sodiumContent) {
		this.sodiumContent = sodiumContent;
	}

	public double getCarbohydrateContent() {
		return carbohydrateContent;
	}

	public void setCarbohydrateContent(double carbohydrateContent) {
		this.carbohydrateContent = carbohydrateContent;
	}

	public double getFiberContent() {
		return fiberContent;
	}

	public void setFiberContent(double fiberContent) {
		this.fiberContent = fiberContent;
	}

	public double getSugarContent() {
		return sugarContent;
	}

	public void setSugarContent(double sugarContent) {
		this.sugarContent = sugarContent;
	}

	public double getProteinContent() {
		return proteinContent;
	}

	public void setProteinContent(double proteinContent) {
		this.proteinContent = proteinContent;
	}

	public double getRecipeServings() {
		return recipeServings;
	}

	public void setRecipeServings(double recipeServings) {
		this.recipeServings = recipeServings;
	}

	public String getRecipeInstructions() {
		return recipeInstructions;
	}

	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}
	
}
