package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

public class KeywordsDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static KeywordsDao instance = null;

	protected KeywordsDao() {
		connectionManager = new ConnectionManager();
	}

	public static KeywordsDao getInstance() {
		if (instance == null) {
			instance = new KeywordsDao();
		}
		return instance;
	}

	public Keywords create(Keywords keyWords) throws SQLException {
		String insertKeywords = "INSERT INTO Keywords(KeywordName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertKeywords);

			insertStmt.setString(1, keyWords.getKeyWordName());

			insertStmt.executeUpdate();
			return keyWords;
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
		}
	}

	public Keywords updateKeywordName(Keywords keyWords, String newName) throws SQLException {
		String updateKeywords = "UPDATE Keywords SET KeywordName=? WHERE KeywordName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateKeywords);
			updateStmt.setString(1, newName);
			updateStmt.setString(2, keyWords.getKeyWordName());
			updateStmt.executeUpdate();

			keyWords.setKeyWordName(newName);
			return keyWords;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Keywords delete(Keywords keyWords) throws SQLException {
		String deleteKeywords = "DELETE FROM Keywords WHERE KeywordName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteKeywords);
			deleteStmt.setString(1, keyWords.getKeyWordName());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Keywords getKeywordInRecipeByKeywordName(String keywordName) throws SQLException {
		String selectKeywordInRecipe = "SELECT KeywordName FROM Keywords WHERE KeywordName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeywordInRecipe);
			selectStmt.setString(1, keywordName);

			results = selectStmt.executeQuery();

			if (results.next()) {
				return new Keywords(results.getString("KeywordName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
}
