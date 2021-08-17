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


@WebServlet("/recipeingredientsdelete")
public class RecipeIngredientsDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete RecipeIngredients");        
        req.getRequestDispatcher("/RecipeIngredientsDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int recipeIngredientID = Integer.parseInt(req.getParameter("recipeIngredientID"));
        if (recipeIngredientID <= 0) {
            messages.put("title", "Invalid RecipeIngredientID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	RecipeIngredients recipeIngredients = new RecipeIngredients(recipeIngredientID);
	        try {
	        	recipeIngredients = recipeIngredientsDao.delete(recipeIngredients);
	        	// Update the message.
		        if (recipeIngredients == null) {
		            messages.put("title", "Successfully deleted " + recipeIngredientID);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + recipeIngredientID);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeIngredientsDelete.jsp").forward(req, resp);
    }
}
