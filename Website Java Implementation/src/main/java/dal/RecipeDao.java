package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;


public class RecipeDao {
	
	protected ConnectionManager connectionManager;
    private static RecipeDao instance = null;
    protected RecipeDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecipeDao getInstance() {
        if(instance == null) {
            instance = new RecipeDao();
        }
        return instance;
    }
    
    public Recipes create(Recipes recipe) throws SQLException {
    	String insertRecipe = "INSERT INTO Recipes(RecipeID, RecipeName, PrepTime"
    			+ ", CookTime, DatePublished, Description, Images, Calories,"
    			+ "FatContent, SaturatedFatContent, CholestrolContent, SodiumContent,"
    			+ "CarbohydrateContent, FiberContent, SugarContent, ProteinContent,"
    			+ "RecipeServings, RecipeInstructions) VALUES(?,?,?,?,?,?,?,?,?,?,"
    			+ "?,?,?,?,?,?,?,?);";
    	Connection connection = null;
    	PreparedStatement insertStmt = null;
    	try {
    		connection = connectionManager.getConnection();
    		insertStmt = connection.prepareStatement(insertRecipe);
    		insertStmt.setInt(1, recipe.getRecipeID());
    		insertStmt.setString(2, recipe.getRecipeName());
    		insertStmt.setString(3, recipe.getPrepTime());
    		insertStmt.setString(4, recipe.getCookTime());
    		insertStmt.setDate(5, recipe.getDatePublished());
    		insertStmt.setString(6, recipe.getDescription());
    		insertStmt.setString(7, recipe.getImages());
    		insertStmt.setDouble(8, recipe.getCalories());
    		insertStmt.setDouble(9, recipe.getFatContent());
    		insertStmt.setDouble(10, recipe.getSaturatedFatContent());
    		insertStmt.setDouble(11, recipe.getCholestrolContent());
    		insertStmt.setDouble(12, recipe.getSodiumContent());
    		insertStmt.setDouble(13, recipe.getCarbohydrateContent());
    		insertStmt.setDouble(14, recipe.getFiberContent());
    		insertStmt.setDouble(15, recipe.getSugarContent());
    		insertStmt.setDouble(16, recipe.getProteinContent());
    		insertStmt.setDouble(17, recipe.getRecipeServings());
    		insertStmt.setString(18, recipe.getRecipeInstructions());
    		insertStmt.executeUpdate();
    		return recipe;
    		
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
    
    public Recipes updateRecipeName(Recipes recipe, String newRecipeName) throws SQLException {
    	String updateRecipeName = "UPDATE Recipes SET RecipeName = ? WHERE RecipeId = ?;";
    	Connection connection = null;
    	PreparedStatement updateStmt = null;
    	try {
    		connection = connectionManager.getConnection();
    		updateStmt = connection.prepareStatement(updateRecipeName);
    		updateStmt.setString(1, newRecipeName);
    		updateStmt.setInt(2, recipe.getRecipeID());
    		updateStmt.executeUpdate();
    		recipe.setRecipeName(newRecipeName);
    		return recipe;
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
    
    public int delete(Recipes recipe) throws SQLException {
    	String delete = "DELETE FROM Recipes WHERE RecipeId = ?;";
    	Connection connection = null;
    	PreparedStatement deleteStmt = null;
    	try {
    		connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, recipe.getRecipeID());
			int affectedRows = deleteStmt.executeUpdate();
			return affectedRows;
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
    
    public Recipes getRecipeByID(int recipeID) throws SQLException {
    	String getRecipe = "SELECT * FROM Recipes WHERE RecipeId = ?;";
    	Connection connection = null;
    	PreparedStatement selectStmt = null;
    	ResultSet result = null;
    	try {
    		connection = connectionManager.getConnection();
    		selectStmt = connection.prepareStatement(getRecipe);
    		selectStmt.setInt(1, recipeID);
    		result = selectStmt.executeQuery();
				result.next();
    		int resultRecipeId = result.getInt("RecipeId");
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
    				datePublished, description, images, calories, fatContent,
    				saturatedFatContent, cholestrolContent, sodiumContent, 
    				carbohydrateContent, fiberContent, sugarContent, proteinContent,
    				recipeServings, recipeInstructions);
    		return recipe;
    	}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (result != null) {
				result.close();
			}
		}
    }
}
