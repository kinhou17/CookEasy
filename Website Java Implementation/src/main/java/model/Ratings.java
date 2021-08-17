package model;

public class Ratings {
  protected int ratingId;
  protected int recipeId;
  protected int userName;
  protected float difficulty;
  protected float cost;
  protected float taste;
  protected float time;

  public Ratings(int ratingId, int recipeId, int userName, float difficulty, float cost,
      float taste,
      float time) {
    this.ratingId = ratingId;
    this.recipeId = recipeId;
    this.userName = userName;
    this.difficulty = difficulty;
    this.cost = cost;
    this.taste = taste;
    this.time = time;
  }

  public int getRatingId() {
    return ratingId;
  }

  public void setRatingId(int ratingId) {
    this.ratingId = ratingId;
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

  public float getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(float difficulty) {
    this.difficulty = difficulty;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public float getTaste() {
    return taste;
  }

  public void setTaste(float taste) {
    this.taste = taste;
  }

  public float getTime() {
    return time;
  }

  public void setTime(float time) {
    this.time = time;
  }
}
