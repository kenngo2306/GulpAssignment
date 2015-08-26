

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRestaurantDetail
 */
@WebServlet("/RestaurantDetail")
public class ServletRestaurantDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRestaurantDetail() {
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
		System.out.println("dopost in servlet restaurant detail");
		String restaurant_idStr = request.getParameter("restaurant_id");
				
		if(!Validator.validateInt(restaurant_idStr))
		{
			response.sendError(400,"Invalid Input");
		}
		else
		{
			int restaurant_id = Integer.parseInt(restaurant_idStr);
			String restaurant_info = "";
			
			Database db = new Database();
			db.openConnection();
			Restaurant restaurant = db.getRestaurant(restaurant_id);
			
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
			db.closeConnection();
			request.setAttribute("restaurant_info", restaurant_info);
			getServletContext().getRequestDispatcher("/RateRestaurant.jsp").forward(request, response);
		}
		
		
	}

}
