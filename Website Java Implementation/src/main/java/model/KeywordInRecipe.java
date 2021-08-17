package model;

public class KeywordInRecipe {
    protected int keywordRecipeID;
    protected String keywordName;
    protected int recipeID;

    public KeywordInRecipe(int keywordRecipeID, String keywordName, int recipeID) {
        this.keywordRecipeID = keywordRecipeID;
        this.keywordName = keywordName;
        this.recipeID = recipeID;
    }

    public int getKeywordRecipeID() {
        return keywordRecipeID;
    }

    public void setKeywordRecipeID(int keywordRecipeID) {
        this.keywordRecipeID = keywordRecipeID;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
}
