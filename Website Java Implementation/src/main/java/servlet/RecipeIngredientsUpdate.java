package servlet;

import dal.*;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/recipeingredientsupdate")
public class RecipeIngredientsUpdate extends HttpServlet {
	
	protected RecipeIngredientsDao recipeIngredientsDao;
	
	@Override
	public void init() throws ServletException {
		recipeIngredientsDao = RecipeIngredientsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int recipeIngredientID = Integer.parseInt(req.getParameter("recipeIngredientID"));
        if (recipeIngredientID <= 0) {
            messages.put("success", "Please enter a valid RecipeIngredientID.");
        } else {
        	try {
        		RecipeIngredients recipeIngredients = recipeIngredientsDao.getRecipeIngredientFromID(recipeIngredientID);
        		if(recipeIngredients == null) {
        			messages.put("success", "RecipeIngredientID does not exist.");
        		}
        		req.setAttribute("recipeIngredients", recipeIngredients);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeIngredientsUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int recipeIngredientID = Integer.parseInt(req.getParameter("recipeIngredientID"));
        if (recipeIngredientID <= 0) {
            messages.put("success", "Please enter a valid RecipeIngredientID.");
        } else {
        	try {
        		RecipeIngredients recipeIngredients = recipeIngredientsDao.getRecipeIngredientFromID(recipeIngredientID);
        		if(recipeIngredients == null) {
        			messages.put("success", "RecipeIngredientID does not exist. No update to perform.");
        		} else {
        			String newQuantity = req.getParameter("quantity");
        			if (newQuantity == null || newQuantity.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid IngredientName.");
        	        } else {
        	        	recipeIngredients = recipeIngredientsDao.updateQuantity(recipeIngredients, newQuantity);
        	        	messages.put("success", "Successfully updated " + newQuantity);
        	        }
        		}
        		req.setAttribute("recipeIngredients", recipeIngredients);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeIngredientsUpdate.jsp").forward(req, resp);
    }
}
