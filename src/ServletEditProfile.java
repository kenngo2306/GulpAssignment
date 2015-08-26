

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
		
		request.setAttribute("reviewer_name", reviewer.getReviewer_Name());
		request.setAttribute("zipcode", reviewer.getZipcode());
		
		//forward to EditReviewer.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get data from EditReviewer.jsp
		
		//validate data
		
		// format data
		
		//turn data into object
		
		//save to database
		
		//go back to profile page
	}

}
