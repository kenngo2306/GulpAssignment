

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletEditProfile
 */
@WebServlet("/EditProfile")
public class ServletEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String error ="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doget");
		
		//get session variable about reviewer_id
		HttpSession session = request.getSession();
		int reviewer_id = (int)session.getAttribute("reviewer_id");
		System.out.println("reviewer_id = " + reviewer_id) ;
		
		//initialize database
		Database db = new Database();
		db.openConnection();
		Reviewer reviewer = db.getReviewer(reviewer_id);
		db.closeConnection();
		
		request.setAttribute("name", reviewer.getReviewer_Name());
		request.setAttribute("zipcode", reviewer.getZipcode());
		request.setAttribute("email", reviewer.getEmail());
		
		//forward to EditReviewer.jsp
		request.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/EditProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get data from EditProfile.jsp
				HttpSession session = request.getSession();
				int reviewer_id = (int)session.getAttribute("reviewer_id");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				
				//
				System.out.println(" reviewer_id" + reviewer_id);
				System.out.println(" name" + name);
				System.out.println(" email" + email);
				System.out.println(" zipcode" + zipcode);
				// validate data
				if(
						 !Validator.validateNullEmptyString(name) 
						|| !Validator.validateEmail(email)
						|| !Validator.validateNullEmptyString(zipcode))
				{
					response.sendError(400,"Invalid Inputs!");
				}
				else
				{
					// convert data
					
					//turn data into object
					Reviewer reviewer = new Reviewer();
					reviewer.setReviewer_id(reviewer_id);
					reviewer.setEmail(email);
					reviewer.setZipcode(zipcode);
					reviewer.setReviewer_Name(name);
					
					//save into database
					Database db = new Database();
					db.openConnection();
					if(db.duplicateReviewer(email))
					{
						error = "Email has already been taken";
						request.setAttribute("error", error);
						getServletContext().getRequestDispatcher("/EditProfile").forward(request, response);
					}
					else
					{
						db.editProfile(reviewer);
						db.closeConnection();
						
						//go back to restaurant rating
						session.setAttribute("reviewer_name", name);
						getServletContext().getRequestDispatcher("/Profile").forward(request, response);
					}

				}
	}
}
