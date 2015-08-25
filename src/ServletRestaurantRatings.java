

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
			
			ArrayList<Ratings> ratings = db.getRatings(restaurant_id);
			
			String ratingData = "";
			for(Ratings rating:ratings)
			{
				ratingData += "<div class='panel panel-warning'>";
			}
		}
	}

}
