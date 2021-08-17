package dal;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersDao {
  protected ConnectionManager connectionManager;

  private static UsersDao instance = null;
  protected UsersDao() {
    connectionManager = new ConnectionManager();
  }
  public static UsersDao getInstance() {
    if(instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }

  /**
   * Save the Users instance by storing it in your MySQL instance.
   * This runs a INSERT statement.
   */
  public Users create(Users user) throws SQLException {
    String insertUser = "INSERT INTO Users(UserName,FirstName,LastName,Email) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setString(1, user.getUserName());
      insertStmt.setString(2, user.getFirstName());
      insertStmt.setString(3, user.getLastName());
      insertStmt.setString(4, user.getEmail());

      insertStmt.executeUpdate();

      return user;
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
    }
  }

  /**
   * Delete the Users instance.
   * This runs a DELETE statement.
   */
  public Users delete(Users user) throws SQLException {
    String deleteUser = "DELETE FROM Users WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      deleteStmt.setString(1, user.getUserName());
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