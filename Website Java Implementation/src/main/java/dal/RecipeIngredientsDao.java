package dal;

import java.util.Date;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Ingredients table in your MySQL
 * instance. This is used to store {@link RecipeIngredients} into your MySQL instance and retrieve 
 * {@link RecipeIngredients} from MySQL instance.
 */
public class RecipeIngredientsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RecipeIngredientsDao instance = null;
	protected RecipeIngredientsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipeIngredientsDao getInstance() {
		if(instance == null) {
			instance = new RecipeIngredientsDao();
		}
		return instance;
	}

	/**
	 * Save the RecipeIngredients instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public RecipeIngredients create(RecipeIngredients recipeIngredient) throws SQLException {
		String insertRecipeIngredient = "INSERT INTO RecipeIngredients(RecipeID,IngredientName,Quantity) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipeIngredient,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, recipeIngredient.getRecipeID());
			insertStmt.setString(2, recipeIngredient.getIngredientName());
			insertStmt.setString(3, recipeIngredient.getQuantity());

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int recipeIngredientID = -1;
			if(resultKey.next()) {
				recipeIngredientID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recipeIngredient.setRecipeIngredientID(recipeIngredientID);
			return recipeIngredient;
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
	 * Update the Quantity of the RecipeIngredients instance.
	 * This runs a UPDATE statement.
	 */
	public RecipeIngredients updateQuantity(RecipeIngredients recipeIngredient, String newQuantity) throws SQLException {
		String updateRecipeIngredient = "UPDATE RecipeIngredients SET Quantity=? WHERE RecipeIngredientID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecipeIngredient);
			updateStmt.setString(1, newQuantity);
			updateStmt.setInt(2, recipeIngredient.getRecipeIngredientID());
			updateStmt.executeUpdate();
			
			recipeIngredient.setQuantity(newQuantity);
			return recipeIngredient;
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
	 * Delete the RecipeIngredients instance.
	 * This runs a DELETE statement.
	 */
	public RecipeIngredients delete(RecipeIngredients recipeIngredient) throws SQLException {
		String deleteRecipeIngredient = "DELETE FROM RecipeIngredients WHERE RecipeIngredientID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipeIngredient);
			deleteStmt.setInt(1, recipeIngredient.getRecipeIngredientID());
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
	 * Get the RecipeIngredients record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single RecipeIngredients instance.
	 */
	public RecipeIngredients getRecipeIngredientFromID(int recipeIngredientID) throws SQLException {
		String selectRecipeIngredient = "SELECT RecipeIngredientID,RecipeID,IngredientName,Quantity FROM RecipeIngredients WHERE RecipeIngredientID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipeIngredient);
			selectStmt.setInt(1, recipeIngredientID);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultRecipeIngredientID = results.getInt("RecipeIngredientID");
				int recipeID = results.getInt("RecipeID");
				String ingredientName = results.getString("IngredientName");
				String quantity = results.getString("Quantity");
				RecipeIngredients recipeIngredient = new RecipeIngredients(resultRecipeIngredientID, recipeID, ingredientName, quantity);
				return recipeIngredient;
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

	/**
	 * Get the all the RecipeIngredients for a ingredient.
	 */
	public List<RecipeIngredients> getRecipeIngredientsForIngredient(String ingredientName) throws SQLException {
		List<RecipeIngredients> recipeIngredients = new ArrayList<RecipeIngredients>();
		String selectBlogPosts =
			"SELECT RecipeIngredientID,RecipeID,IngredientName,Quantity FROM RecipeIngredients WHERE IngredientName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogPosts);
			selectStmt.setString(1, ingredientName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recipeIngredientID = results.getInt("RecipeIngredientID");
				int recipeID = results.getInt("RecipeID");
				String resultIngredientName = results.getString("IngredientName");
				String quantity = results.getString("Quantity");
				RecipeIngredients recipeIngredient = new RecipeIngredients(recipeIngredientID, recipeID, resultIngredientName, quantity);
				recipeIngredients.add(recipeIngredient);
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
		return recipeIngredients;
	}

	/**
	 * Get the all the Recipes for a ingredient.  PM6
	 */
	public List<Recipes> getRecipesForIngredient(String ingredientName) throws SQLException {
		List<Recipes> recipes = new ArrayList<Recipes>();
		String selectBlogPosts =
				"SELECT * "
						+ "FROM Recipes INNER JOIN (SELECT RecipeID FROM RecipeIngredients WHERE IngredientName=?) AS FILTERED "
						+ "ON Recipes.RecipeID = FILTERED.RecipeID;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogPosts);
			selectStmt.setString(1, ingredientName);
			result = selectStmt.executeQuery();
			while(result.next()) {
				int resultRecipeId = result.getInt("Recipes.RecipeId");
				String recipeName = result.getString("RecipeName");
				String prepTime = result.getString("PrepTime");
				String cookTime = result.getString("CookTime");
				Date datePublished = result.getDate("DatePublished");
				String description = result.getString("Description");
				String images = result.getString("Images");
				double calories = result.getDouble("Calories");
				double fatContent = result.getDouble("FatContent");
				double saturatedFatContent = result.getDouble("SaturatedFatContent");
				double cholestrolContent = result.getDouble("CholestrolContent");
				double sodiumContent = result.getDouble("SodiumContent");
				double carbohydrateContent = result.getDouble("CarbohydrateContent");
				double fiberContent = result.getDouble("FiberContent");
				double sugarContent = result.getDouble("SugarContent");
				double proteinContent = result.getDouble("ProteinContent");
				double recipeServings = result.getDouble("RecipeServings");
				String recipeInstructions = result.getString("RecipeInstructions");
				Recipes recipe = new Recipes(resultRecipeId, recipeName, prepTime, cookTime,
						(java.sql.Date) datePublished, description, images, calories, fatContent,
						saturatedFatContent, cholestrolContent, sodiumContent,
						carbohydrateContent, fiberContent, sugarContent, proteinContent,
						recipeServings, recipeInstructions);
				recipes.add(recipe);
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
			if(result != null) {
				result.close();
			}
		}
		return recipes;
	}
}
