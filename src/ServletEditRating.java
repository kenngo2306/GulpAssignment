

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletEditRating
 */
@WebServlet("/EditRating")
public class ServletEditRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditRating() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		int restaurant_id = Integer.parseInt(request.getParameter("restaurant_id"));
		Database db = new Database();
		
		HttpSession session = request.getSession();
		int reviewer_id = (int)session.getAttribute("reviewer_id");
		//open connection
		db.openConnection();
		
		//get restaurant object from restaurant id
		Ratings rating = db.getRating(restaurant_id, reviewer_id);
		
		//set parameter attributes
		request.setAttribute("restaurant_id", restaurant_id);
		request.setAttribute("description", rating.getDescription());
		request.setAttribute("stars", rating.getStars());
		System.out.println(" description" + rating.getDescription());
		
		Restaurant restaurant = db.getRestaurant(restaurant_id);
		String restaurant_info = "";
		restaurant_info += "<p>";
		restaurant_info += "Name: " + restaurant.getRestaurant_Name();
		restaurant_info += "</p>";
		
		restaurant_info += "<p>";
		restaurant_info += "Rating: ";
				for(int i = 1; i <= restaurant.getAvgRating(); i++)
				{
					restaurant_info += "&#9733;";
				}
		restaurant_info += "</p>";
		
		//forward it to EditRating.jsp
		request.setAttribute("restaurant_info", restaurant_info);
		getServletContext().getRequestDispatcher("/EditRating.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost edit restaurant");
		// get data from EditRating.jsp
		String restaurant_idStr = request.getParameter("restaurant_id");
		String starsStr = request.getParameter("stars");
		String description = request.getParameter("description");
		HttpSession session = request.getSession();
		int reviewer_id = (int)session.getAttribute("reviewer_id");
		
		//
		System.out.println(" restaurant_idStr" + restaurant_idStr);
		System.out.println(" Stars " + starsStr);
		System.out.println(" description" + description);
		
		// validate data
		if(!Validator.validateNullEmptyString(restaurant_idStr) 
				|| !Validator.validateNullEmptyString(starsStr) 
				|| !Validator.validateNullEmptyString(description))
		{
			response.sendError(400,"Invalid Inputs!");
		}
		else
		{
			// convert data
			int restaurant_id = Integer.parseInt(restaurant_idStr);
			int stars = Integer.parseInt(starsStr);
			//turn data into object
			Ratings rating = new Ratings();
			rating.setRestaurant_id(restaurant_id);
			rating.setStars(stars);
			rating.setDescription(description);
			rating.setUser_id(reviewer_id);
			
			//save into database
			Database db = new Database();
			db.openConnection();

			db.editRating(rating);
			db.closeConnection();
			
			//go back to restaurant list
			
			getServletContext().getRequestDispatcher("/RestaurantList?restaurant_id="+ restaurant_id).forward(request, response);
		}

	
	}

}
