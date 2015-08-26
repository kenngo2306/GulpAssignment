

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRegisterRestaurant
 */
@WebServlet("/RegisterRestaurant")
public class ServletRegisterRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegisterRestaurant() {
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
		System.out.println("doPost");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		
		Database db = new Database();
		db.openConnection();
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setDescription(description);
		restaurant.setRestaurant_Name(name);
		
		db.addRestaurant(restaurant);
		
		db.closeConnection();
		String message = "<div class ='success' ><h1>Restaurant Added!</h1></div>";
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/RegisterRestaurant.jsp").forward(request, response);
	}

}
