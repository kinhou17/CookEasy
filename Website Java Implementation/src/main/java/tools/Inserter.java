package tools;

import dal.*;
import model.*;

import java.sql.SQLException;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		IngredientsDao ingredientsDao = IngredientsDao.getInstance();
		
		// INSERT objects from our model.
		Ingredients ingredient = new Ingredients("testIngre");
		ingredient = ingredientsDao.create(ingredient);
		
		// READ.
		Ingredients ing1 = ingredientsDao.getIngredientFromIngredientName("testIngre");
	}
}
