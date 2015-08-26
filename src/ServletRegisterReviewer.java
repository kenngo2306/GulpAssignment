

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletRegisterReviewer
 */
@WebServlet("/RegisterReviewer")
public class ServletRegisterReviewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String error = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegisterReviewer() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init()
    {
    	error = "";
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
		
		//get parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		Reviewer reviewer = new Reviewer();
		Database db = new Database();
		db.openConnection();
		if(db.duplicateReviewer(email)){
			error = "Email Address is not available";
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/RegisterReviewer.jsp").forward(request, response);
		}
		else
		{
			//validate inputs
			if(!Validator.validateNullEmptyString(name) || !Validator.validateNullEmptyString(email) || !Validator.validateNullEmptyString(zipcode))
			{
				response.sendError(400,"Invalid Inputs!");
			}
			else
			{
				System.out.println(name + email + zipcode);
				reviewer.setEmail(email);
				reviewer.setReviewer_Name(name);
				reviewer.setZipcode(zipcode);
				db.addReviewer(reviewer);
				
				//get the reviewer id
				reviewer = db.getReviewer(reviewer.getEmail());
				db.closeConnection();
				HttpSession session = request.getSession();
				
				session.setAttribute("reviewer_id", reviewer.getReviewer_id());
				getServletContext().getRequestDispatcher("/RestaurantList").forward(request, response);
			}
		}

		
		
	}

}
