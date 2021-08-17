package model;

public class SavedRecipes {
  protected int savedRecipeId;
  protected int recipeId;
  protected int userName;
  protected boolean recommended;

  public SavedRecipes(int savedRecipeId, int recipeId, int userName, boolean recommended) {
    this.savedRecipeId = savedRecipeId;
    this.recipeId = recipeId;
    this.userName = userName;
    this.recommended = recommended;
  }

  public int getSavedRecipeId() {
    return savedRecipeId;
  }

  public void setSavedRecipeId(int savedRecipeId) {
    this.savedRecipeId = savedRecipeId;
  }

  public int getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }

  public int getUserName() {
    return userName;
  }

  public void setUserName(int userName) {
    this.userName = userName;
  }

  public boolean isRecommended() {
    return recommended;
  }

  public void setRecommended(boolean recommended) {
    this.recommended = recommended;
  }
}
