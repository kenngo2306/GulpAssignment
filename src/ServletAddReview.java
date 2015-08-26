

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletAddReview
 */
@WebServlet("/AddReview")
public class ServletAddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dopost");
//		System.out.println("restaurant_id = " + request.getParameter("restaurant_id"));
		String restaurant_idStr = request.getParameter("restaurant_id");
		String description = request.getParameter("description");
		String starsStr = request.getParameter("stars");
		
		if(!Validator.validateInt(restaurant_idStr) || !Validator.validateIntWithRange(starsStr, 1, 5))
		{
			response.sendError(400,"Invalid Input");
		}
		else
		{
			//insert rating
			
			//get session's user id
			HttpSession session = request.getSession();
			int reviewer_id = (int)session.getAttribute("reviewer_id");
			Database db = new Database();
			db.openConnection();
			Ratings rating = new Ratings();
			rating.setDescription(description);
			rating.setRestaurant_id(Integer.parseInt(restaurant_idStr));
			rating.setStars(Integer.parseInt(starsStr));
			rating.setUser_id(reviewer_id);
			
			db.addRating(rating);
			db.closeConnection();
			getServletContext().getRequestDispatcher("/RestaurantList").forward(request, response);
		}
		
	}

}
