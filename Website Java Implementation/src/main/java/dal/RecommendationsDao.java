package dal;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RecommendationsDao {
  protected ConnectionManager connectionManager;

  private static RecommendationsDao instance = null;
  protected RecommendationsDao() {
    connectionManager = new ConnectionManager();
  }
  public static RecommendationsDao getInstance() {
    if(instance == null) {
      instance = new RecommendationsDao();
    }
    return instance;
  }

  public Recommendations create(Recommendations recommendation) throws SQLException {
    String insertRecommendation =
        "INSERT INTO Recommendations(RecommendationId, UserName,Recipe) " +
            "VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRecommendation);

      insertStmt.setInt(1, recommendation.getRecommendationId());
      insertStmt.setString(1, recommendation.getUser().getUserName());
      insertStmt.setInt(2, recommendation.getRecipe().getRecipeID());

      insertStmt.executeUpdate();

      return recommendation;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Recommendations delete(Recommendations recommendation) throws SQLException {
    String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecommendation);
      deleteStmt.setInt(1, recommendation.getRecommendationId());
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
