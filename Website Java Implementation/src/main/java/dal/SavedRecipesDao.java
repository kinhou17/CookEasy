package dal;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SavedRecipesDao {
  protected ConnectionManager connectionManager;

  private static SavedRecipesDao instance = null;
  protected SavedRecipesDao() {
    connectionManager = new ConnectionManager();
  }
  public static SavedRecipesDao getInstance() {
    if(instance == null) {
      instance = new SavedRecipesDao();
    }
    return instance;
  }

  public SavedRecipes create(SavedRecipes savedRecipe) throws SQLException {
    String insertSavedRecipes =
        "INSERT INTO SavedRecipes(RecipeId,UserName,RecommendedByCookEasy) " +
            "VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertSavedRecipes,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, savedRecipe.getRecipeId());
      insertStmt.setInt(2, savedRecipe.getUserName());
      insertStmt.setBoolean(3, savedRecipe.isRecommended());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int savedRecipeId = -1;
      if (resultKey.next()) {
        savedRecipeId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      savedRecipe.setRecipeId(savedRecipeId);
      return savedRecipe;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

    public SavedRecipes delete(SavedRecipes savedRecipe) throws SQLException {
      String deleteBlogPost = "DELETE FROM SavedRecipes WHERE SavedRecipeID=?;";
      Connection connection = null;
      PreparedStatement deleteStmt = null;
      try {
        connection = connectionManager.getConnection();
        deleteStmt = connection.prepareStatement(deleteBlogPost);
        deleteStmt.setInt(1, savedRecipe.getSavedRecipeId());
        deleteStmt.executeUpdate();

        return null;
      } catch (SQLException e) {
        e.printStackTrace();
        throw e;
      } finally {
        if(connection != null) {
          connection.close();
        }
        if(deleteStmt != null) {
          deleteStmt.close();
        }
      }
  }

}
