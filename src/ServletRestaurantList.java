

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletRestaurantList
 */
@WebServlet("/RestaurantList")
public class ServletRestaurantList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRestaurantList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doget");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dopost");
		
		Database db = new Database();
		HttpSession session = request.getSession();
		int reviewer_id = (int)session.getAttribute("reviewer_id");
		ArrayList<Restaurant> restaurants = db.getAllRestaurant(reviewer_id);
		
		String tableData ="";
		tableData += "<tr>";
		tableData += "<thead>";
		tableData += "<th>";
		tableData += "ID";
		tableData += "</th>";
		tableData += "<th>";
		tableData += "Restaurant Name";
		tableData += "</th>";
		tableData += "<th>";
		tableData += "Description";
		tableData += "</th>";
		tableData += "<th>";
		tableData += "Address";
		tableData += "</th>";
		tableData += "<th>";
		tableData += "Average Rating";
		tableData += "</th>";
		tableData += "<th>";
		tableData += "User Rating";
		tableData += "</th>";
		tableData += "</thead>";
		tableData += "</tr>";
		for(Restaurant restaurant:restaurants)
		{
			System.out.println(restaurant.getRestaurant_Name());
			tableData += "<tr>";
			
			tableData += "<td>";
			tableData += restaurant.getRestaurant_id();
			tableData += "</td>";
			tableData += "<td>";
			tableData += restaurant.getRestaurant_Name();
			tableData += "</td>";
			tableData += "<td>";
			tableData += restaurant.getDescription();
			tableData += "</td>";
			tableData += "<td>";
			tableData += restaurant.getAddress();
			tableData += "</td>";
			tableData += "<td>";
			tableData += "<a href='RestaurantRatings?restaurant_id=" + restaurant.getRestaurant_id() + "'>";
					//input starts instead of words
					for(int i = 1; i <= restaurant.getAvgRating(); i ++)
					{
						tableData += "&#9733;";
					}
			tableData += "</a>";
			tableData += "</td>";
			
			//check if user has ever rated the restaurant
			tableData += "<td>";
			if(restaurant.getUser_rating()<1)
			{
				tableData += "<a class='form-control button btn-info'  href='RestaurantDetail?restaurant_id="+ restaurant.getRestaurant_id() +"'>Rate</a>";
			}
			else
			{
				tableData += restaurant.getUser_rating();
			}
			
			tableData += "</td>";
			
			tableData += "</tr>";
		}
		
		request.setAttribute("tableData", tableData);
		getServletContext().getRequestDispatcher("/RestaurantList.jsp").forward(request, response);
		
		
	}

}
