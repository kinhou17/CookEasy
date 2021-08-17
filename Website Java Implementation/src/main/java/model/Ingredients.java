package model;

/**
 * Ingredients is a simple, plain old java objects (POJO).
 */
public class Ingredients {
	protected String ingredientName;

	public Ingredients(String ingredientName) {
		super();
		this.ingredientName = ingredientName;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

}
