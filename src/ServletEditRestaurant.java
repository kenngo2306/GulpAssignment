

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEditRestaurant
 */
@WebServlet("/EditRestaurant")
public class ServletEditRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditRestaurant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dopost");
		int restaurant_id = Integer.parseInt(request.getParameter("restaurant_id"));
		Database db = new Database();
		
		//open connection
		db.openConnection();
		
		//get restaurant object from restaurant id
		Restaurant restaurant = db.getRestaurant(restaurant_id);
		
		//set parameter attributes
		request.setAttribute("restaurant_id", restaurant_id);
		request.setAttribute("restaurant_name", restaurant.getRestaurant_Name());
		request.setAttribute("address", restaurant.getAddress());
		request.setAttribute("description", restaurant.getDescription());
		
		//forward it to EditRestaurant.jsp
		getServletContext().getRequestDispatcher("/EditRestaurant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost edit restaurant");
		// get data from EditRestaurant.jsp
		String restaurant_idStr = request.getParameter("restaurant_id");
		String restaurant_name = request.getParameter("restaurant_name");
		String description = request.getParameter("description");
		String address = request.getParameter("address");
		
		//
		System.out.println(" restaurant_idStr" + restaurant_idStr);
		System.out.println(" restaurant_name" + restaurant_name);
		System.out.println(" description" + description);
		System.out.println(" address" + address);
		// validate data
		if(!Validator.validateNullEmptyString(restaurant_idStr) 
				|| !Validator.validateNullEmptyString(restaurant_name) 
				|| !Validator.validateNullEmptyString(description)
				|| !Validator.validateNullEmptyString(address))
		{
			response.sendError(400,"Invalid Inputs!");
		}
		else
		{
			// convert data
			int restaurant_id = Integer.parseInt(restaurant_idStr);
			//turn data into object
			Restaurant restaurant = new Restaurant();
			restaurant.setRestaurant_id(restaurant_id);
			restaurant.setRestaurant_Name(restaurant_name);
			restaurant.setDescription(description);
			restaurant.setAddress(address);
			
			//save into database
			Database db = new Database();
			db.openConnection();
			db.editRestaurant(restaurant);
			db.closeConnection();
			
			//go back to restaurant rating
			getServletContext().getRequestDispatcher("/RestaurantRatings?restaurant_id="+ restaurant_id).forward(request, response);
		}

	}

}
