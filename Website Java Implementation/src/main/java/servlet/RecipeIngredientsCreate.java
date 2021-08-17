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


@WebServlet("/recipeingredientscreate")
public class RecipeIngredientsCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/RecipeIngredientsCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
    	// Create the RecipeIngredient.
    	int recipeID = Integer.parseInt(req.getParameter("recipeID"));
    	String ingredientName = req.getParameter("ingredientName");
    	String quantity = req.getParameter("quantity");
        try {
        	// Exercise: parse the input for StatusLevel.
        	RecipeIngredients recipeIngredient = new RecipeIngredients(recipeID, ingredientName, quantity);
        	recipeIngredient = recipeIngredientsDao.create(recipeIngredient);
        	messages.put("success", "Successfully created " + ingredientName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/RecipeIngredientsCreate.jsp").forward(req, resp);
    }
}
