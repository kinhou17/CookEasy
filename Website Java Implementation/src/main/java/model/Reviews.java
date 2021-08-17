package model;

public class Reviews {
  protected int reviewID;
  protected Users user;
  protected Recipes recipe;
  protected String content;

  public Reviews(int reviewID, Users user, Recipes recipe, String content) {
    this.reviewID = reviewID;
    this.user = user;
    this.recipe = recipe;
    this.content = content;
  }

  public Reviews(int reviewID) {
    this.reviewID = reviewID;
  }

  public Reviews(Users user, Recipes recipe, String content) {
    this.user = user;
    this.recipe = recipe;
    this.content = content;
  }

  public int getReviewID() {
    return reviewID;
  }

  public void setReviewID(int reviewID) {
    this.reviewID = reviewID;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
