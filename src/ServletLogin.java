

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
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
		String email = request.getParameter("email");
		System.out.println("email = "  + email);

		if(!Validator.validateEmail(email))
		{
			response.sendError(400,"Invalid email");
		}
		else
		{
			//do db stuff
			Database db = new Database();
			Reviewer reviewer = db.getReviewer(email);
			
			if(reviewer.getEmail().trim().equals(""))
			{
				System.out.println("login failed");
				String error= "Invalid Credential";
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
				
			}
			else
			{
				System.out.println("login success");
				HttpSession session = request.getSession();
				session.setAttribute("reviewer_id", reviewer.getReviewer_id());
				System.out.println("reviewer_name = " + reviewer.getReviewer_Name());
				session.setAttribute("reviewer_name", reviewer.getReviewer_Name());
				getServletContext().getRequestDispatcher("/RestaurantList").forward(request, response);
			}
			
			
			
			
		}
	}

}
