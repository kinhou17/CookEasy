package model;

/**
 * RecipeIngredients is a simple, plain old java objects (POJO).
 */
public class RecipeIngredients {
	protected int recipeIngredientID;
	protected int recipeID;
	protected String ingredientName;
	protected String quantity;
	
	public RecipeIngredients(int recipeIngredientID, int recipeID, String ingredientName, String quantity) {
		super();
		this.recipeIngredientID = recipeIngredientID;
		this.recipeID = recipeID;
		this.ingredientName = ingredientName;
		this.quantity = quantity;
	}

	public RecipeIngredients(int recipeIngredientID) {
		super();
		this.recipeIngredientID = recipeIngredientID;
	}

	public RecipeIngredients(int recipeID, String ingredientName, String quantity) {
		super();
		this.recipeID = recipeID;
		this.ingredientName = ingredientName;
		this.quantity = quantity;
	}

	public int getRecipeIngredientID() {
		return recipeIngredientID;
	}

	public void setRecipeIngredientID(int recipeIngredientID) {
		this.recipeIngredientID = recipeIngredientID;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
