

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletProfile
 */
@WebServlet("/Profile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfile() {
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
		
		HttpSession session = request.getSession();
		int reviewer_id = (int) session.getAttribute("reviewer_id");
		
		System.out.println("reviewer = " + reviewer_id);
		Database db = new Database();
		
		
		ArrayList<Ratings> ratings = db.getRatingsByUser(reviewer_id);
		String ratingData = "";
		for(Ratings rating : ratings)
		{
			ratingData += "<div class='panel panel-warning col-sm-6 col-sm-offset-3'>";
			ratingData += "<div class='panel-heading'>";	
			ratingData += "<div class='row'>";
			ratingData += "<div class='col-sm-4'> UserID:";
			ratingData += rating.getUser_id();
			ratingData += "</div>";
			ratingData += "<div class='col-sm-4'> Rating:";
			ratingData += rating.getStars();
			ratingData += "</div>";
			ratingData += "<div class='col-sm-4'> Date:";
			ratingData += rating.getReviewDate();
			ratingData += "</div>";
			ratingData += "</div>";
			ratingData += "</div>";
			ratingData += "<div class='panel-body'>";	
			ratingData += rating.getDescription();
			ratingData += "</div>";		
			ratingData += "</div>";
		}
		
		request.setAttribute("ratingData", ratingData);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request, response);
	}

}
