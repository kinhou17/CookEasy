package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

public class KeywordInRecipeDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static KeywordInRecipeDao instance = null;

	protected KeywordInRecipeDao() {
		connectionManager = new ConnectionManager();
	}

	public static KeywordInRecipeDao getInstance() {
		if (instance == null) {
			instance = new KeywordInRecipeDao();
		}
		return instance;
	}
	
	public KeywordInRecipe create(KeywordInRecipe keywordInRecipe) throws SQLException {
		String insertKeywordInRecipe = "INSERT INTO KeywordInRecipe(KeywordRecipeID, KeywordName, RecipeID) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertKeywordInRecipe);

			insertStmt.setString(1, "" + keywordInRecipe.getKeywordRecipeID());
			insertStmt.setString(2, keywordInRecipe.getKeywordName());
			insertStmt.setString(3, "" + keywordInRecipe.getRecipeID());

			insertStmt.executeUpdate();
			return keywordInRecipe;
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
	
	public KeywordInRecipe updateKeywordName(KeywordInRecipe keywordInRecipe, String newName) throws SQLException {
		String updateKeywordInRecipe = "UPDATE KeywordInRecipe SET KeywordName=? WHERE KeywordName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateKeywordInRecipe);
			updateStmt.setString(1, newName);
			updateStmt.setString(2, keywordInRecipe.getKeywordName());
			updateStmt.executeUpdate();
			
			keywordInRecipe.setKeywordName(newName);
			return keywordInRecipe;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public KeywordInRecipe delete(KeywordInRecipe keywordInRecipe) throws SQLException {
		String deleteKeywordInRecipe = "DELETE FROM KeywordInRecipe WHERE KeywordName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteKeywordInRecipe);
			deleteStmt.setString(1, keywordInRecipe.getKeywordName());
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
	
	public KeywordInRecipe getKeywordInRecipeByKeywordName(String keywordName) throws SQLException {
		String selectKeywordInRecipe = "SELECT KeywordRecipeID, KeywordName, RecipeID FROM KeywordInRecipe WHERE KeywordName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeywordInRecipe);
			selectStmt.setString(1, keywordName);

			results = selectStmt.executeQuery();

			if(results.next()) {
				return new KeywordInRecipe(
						results.getInt("KeywordRecipeID"), 
						results.getString("KeywordName"),
						results.getInt("RecipeID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
}
