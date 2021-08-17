package dal;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RatingsDao {
  protected ConnectionManager connectionManager;

  private static RatingsDao instance = null;
  protected RatingsDao() {
    connectionManager = new ConnectionManager();
  }
  public static RatingsDao getInstance() {
    if(instance == null) {
      instance = new RatingsDao();
    }
    return instance;
  }

  public Ratings create(Ratings rating) throws SQLException {
    String insertRatings =
        "INSERT INTO Ratings(RecipeId,UserName,Difficulty,Cost,Taste,Time) " +
            "VALUES(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      // BlogPosts has an auto-generated key. So we want to retrieve that key.
      insertStmt = connection.prepareStatement(insertRatings,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, rating.getRatingId());
      insertStmt.setInt(2, rating.getUserName());
      insertStmt.setFloat(3, rating.getDifficulty());
      insertStmt.setFloat(4, rating.getCost());
      insertStmt.setFloat(5, rating.getTaste());
      insertStmt.setFloat(6, rating.getTime());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int ratingId = -1;
      if(resultKey.next()) {
        ratingId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      rating.setRatingId(ratingId);
      return rating;
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

  public Ratings delete(Ratings rating) throws SQLException {
    String deleteBlogPost = "DELETE FROM Ratings WHERE RatingId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteBlogPost);
      deleteStmt.setInt(1, rating.getRatingId());
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
