

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRestaurantRatings
 */
@WebServlet("/RestaurantRatings")
public class ServletRestaurantRatings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRestaurantRatings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dopost");
		
		String restaurant_idStr = request.getParameter("restaurant_id");
		
		if(!Validator.validateInt(restaurant_idStr))
		{
			response.sendError(400,"Invalid inputs!");
		}
		else
		{
			int restaurant_id = Integer.parseInt(restaurant_idStr);
			
			Database db = new Database();
			db.openConnection();
			
			Restaurant restaurant = db.getRestaurant(restaurant_id);
			String restaurantData = "";
			restaurantData += "<div class='panel panel-primary col-sm-8'>";
			restaurantData += "<div class='panel-heading'>";	
			restaurantData +=	"Name: " + restaurant.getRestaurant_Name();
			restaurantData += "</div>";
			
			restaurantData += "<div class='panel-body'>";	
			restaurantData +=	"Address: " + restaurant.getAddress();
			restaurantData += "<br>";
			restaurantData +=	"Description: " + restaurant.getDescription();
			restaurantData += "<br>";
			restaurantData += "Average Rating: " + restaurant.getAvgRating(); 
			restaurantData += "<br>";
			restaurantData += "<a href='EditRestaurant?restaurant_id="+ restaurant_id +"'><span class='glyphicon glyphicon-pencil'></span>Edit</a>";
			restaurantData += "</div>";		
			restaurantData += "</div>";
			
			request.setAttribute("restaurantData", restaurantData);
			
			ArrayList<Ratings> ratings = db.getRatings(restaurant_id);
			
			String ratingData = "";
			for(Ratings rating:ratings)
			{
				ratingData += "<div class='panel panel-warning col-sm-6 col-sm-offset-3'>";
				ratingData += "<div class='panel-heading'>";	
				ratingData += "<div class='row'>";
				ratingData += "<div class='col-sm-4'> User: ";
				
				//get user name from user id
				Reviewer reviewer2 = db.getReviewer(rating.getUser_id());
				ratingData += reviewer2.getReviewer_Name();
				ratingData += "</div>";
				ratingData += "<div class='col-sm-4'> Rating: ";
				for(int i = 1; i<= rating.getStars(); i++)
				{
					ratingData += "&#9733;";
				}
				
				ratingData += "</div>";
				ratingData += "<div class='col-sm-4'> Date:";
				ratingData += rating.getReviewDate();
				ratingData += "</div>";
				ratingData += "</div>";
				ratingData += "</div>";
				ratingData += "<div class='panel-body'>";	
				ratingData += rating.getDescription();
				ratingData += "</div>";		
				ratingData += "</div>";
			}
			db.closeConnection();
			request.setAttribute("ratingData", ratingData);
			getServletContext().getRequestDispatcher("/RestaurantRatings.jsp").forward(request, response);
		}
	}

}
