package dal;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewsDao {
  protected ConnectionManager connectionManager;

  private static ReviewsDao instance = null;
  protected ReviewsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReviewsDao getInstance() {
    if(instance == null) {
      instance = new ReviewsDao();
    }
    return instance;
  }

  public Reviews create(Reviews review) throws SQLException {
    String insertReview =
        "INSERT INTO Reviews(ReviewId,UserName,Recipe,Content) " +
            "VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReview);

      insertStmt.setInt(1, review.getReviewID());
      insertStmt.setString(4, review.getUser().getUserName());
      insertStmt.setInt(3, review.getRecipe().getRecipeID());
      insertStmt.setString(2, review.getContent());

      insertStmt.executeUpdate();

      return review;
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

  public Reviews delete(Reviews review) throws SQLException {
    String deleteReview = "DELETE FROM cook.model.Reviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReview);
      deleteStmt.setInt(1, review.getReviewID());
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
