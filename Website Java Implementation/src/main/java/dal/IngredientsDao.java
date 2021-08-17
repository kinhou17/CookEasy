package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Data access object (DAO) class to interact with the underlying Ingredients table in your MySQL
 * instance. This is used to store {@link Ingredients} into your MySQL instance and retrieve 
 * {@link Ingredients} from MySQL instance.
 */
public class IngredientsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static IngredientsDao instance = null;
	protected IngredientsDao() {
		connectionManager = new ConnectionManager();
	}
	public static IngredientsDao getInstance() {
		if(instance == null) {
			instance = new IngredientsDao();
		}
		return instance;
	}

	/**
	 * Save the Ingredients instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Ingredients create(Ingredients ingredient) throws SQLException {
		String insertIngredient = "INSERT INTO Ingredients(IngredientName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertIngredient);

			insertStmt.setString(1, ingredient.getIngredientName());

			insertStmt.executeUpdate();
			return ingredient;
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
	 * Update the IngredientName of the Ingredients instance.
	 * This runs a UPDATE statement.
	 */
	public Ingredients updateIngredientName(Ingredients ingredient, String newIngredientName) throws SQLException {
		String updateIngredient = "UPDATE Ingredients SET IngredientName=? WHERE IngredientName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateIngredient);
			updateStmt.setString(1, newIngredientName);
			updateStmt.setString(2, ingredient.getIngredientName());
			updateStmt.executeUpdate();
			
			ingredient.setIngredientName(newIngredientName);
			return ingredient;
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

	/**
	 * Delete the Ingredients instance.
	 * This runs a DELETE statement.
	 */
	public Ingredients delete(Ingredients ingredient) throws SQLException {
		String deleteIngredient = "DELETE FROM Ingredients WHERE IngredientName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteIngredient);
			deleteStmt.setString(1, ingredient.getIngredientName());
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

	/**
	 * Get the Ingredients record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Ingredients instance.
	 */
	public Ingredients getIngredientFromIngredientName(String ingredientName) throws SQLException {
		String selectIngredient = "SELECT IngredientName FROM Ingredients WHERE IngredientName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIngredient);
			selectStmt.setString(1, ingredientName);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultIngredientName = results.getString("IngredientName");
				Ingredients ingredient = new Ingredients(resultIngredientName);
				return ingredient;
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
