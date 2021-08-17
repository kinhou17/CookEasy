package model;

public class Recommendations {
  protected int recommendationId;
  protected Users user;
  protected Recipes recipe;

  public Recommendations(int recommendationId, Users user, Recipes recipe) {
    this.recommendationId = recommendationId;
    this.user = user;
    this.recipe = recipe;
  }

  public Recommendations(int recommendationId) {
    this.recommendationId = recommendationId;
  }

  public Recommendations(Users user, Recipes recipe) {
    this.user = user;
    this.recipe = recipe;
  }

  public int getRecommendationId() {
    return recommendationId;
  }

  public void setRecommendationId(int recommendationId) {
    this.recommendationId = recommendationId;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public Recipes getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipes recipe) {
    this.recipe = recipe;
  }
}
