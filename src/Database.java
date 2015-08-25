import java.sql.*;
import java.util.*;

public class Database {

	Restaurant restObj;
	Reviewer reviewerObj;
	Ratings ratingObj;

	private static Statement st;
	private static Connection conn;

	public void openConnection() {
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, props);
			st = conn.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connection established successfully...!!");
	}

	public Reviewer getReviewer(String email) {
		String sql= "Select * from reviewer where email ='?'";
		
		ResultSet rs = null;
		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, email);
			rs=preStatement.executeQuery(sql);
			if(rs.next()){
				reviewerObj= new Reviewer();
				reviewerObj.setEmail(email);
				reviewerObj.setReviewer_id(rs.getInt(1));
				reviewerObj.setReviewer_Name(rs.getString(2));
				reviewerObj.setZipcode(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewerObj;
	}
	
	public void addReviewer(Reviewer user) {
		String sql= "Insert into reviewer(REVIEWER_NAME,EMAIL,ZIPCODE) values('?', '?', '?')";

		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, user.getReviewer_Name());
			preStatement.setString(2, user.getEmail());
			preStatement.setString(3, user.getZipcode());
			preStatement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Restaurant> getAllRestaurant(int reviewerId) {
		String sql= "SELECT rs.RESTAURANT_ID, rs.RESTAURANT_NAME, rs.ADDRESS,"+
					"rs.Description, NVL(avg(stars),0) AS AVERAGE,"
					+ " NVL((SELECT STARS FROM RATINGS WHERE USER_ID = ? and "
					+ "RESTAURANT_ID = rs.RESTAURANT_ID),0) AS USER_RATING FROM restaurant rs "
					+ "Left outer JOIN ratings ra ON rs.RESTAURANT_ID=ra.RESTAURANT_ID"
					+ " group by rs.RESTAURANT_ID, rs.RESTAURANT_NAME, rs.ADDRESS, rs.Description";
		ArrayList<Restaurant> restArray= new ArrayList<Restaurant>();
		ResultSet rs = null;
		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, reviewerId);
			rs=preStatement.executeQuery(sql);
			while(rs.next()){
				restObj=new Restaurant();
				restObj.setRestaurant_id(rs.getInt(1));
				restObj.setRestaurant_Name(rs.getString(2));
				restObj.setAddress(rs.getString(3));
				restObj.setDescription(rs.getString(4));
				restObj.setAvgRating(rs.getInt(5));
				restObj.setUser_rating(rs.getInt(6));
				restArray.add(restObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restArray;
	}
	
	public Restaurant getRestaurant(int restaurant_id) {
		String sql= "SELECT rs.RESTAURANT_ID, rs.RESTAURANT_NAME, rs.ADDRESS, rs.Description, "
				+ "NVL(avg(stars),0) AS AVERAGE FROM restaurant rs Left outer JOIN ratings ra "
				+ "ON rs.RESTAURANT_ID=ra.RESTAURANT_ID where rs.RESTAURANT_ID =? "
				+ "group by rs.RESTAURANT_ID, rs.RESTAURANT_NAME, rs.ADDRESS, rs.Description";
		
		ResultSet rs = null;
		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, restaurant_id);
			rs=preStatement.executeQuery(sql);
			if(rs.next()){
				restObj.setRestaurant_id(restaurant_id);
				restObj.setRestaurant_Name(rs.getString(2));
				restObj.setAddress(rs.getString(3));
				restObj.setDescription(rs.getString(4));
				restObj.setAvgRating(rs.getInt(5));
				restObj.setUser_rating(rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restObj;
	}
	
	public void addRestaurant(Restaurant newRestaurant) {
		String sql= "Insert into restaurant(RESTAURANT_NAME,ADDRESS,Description) values('?', '?', '?')";

		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, newRestaurant.getRestaurant_Name());
			preStatement.setString(2, newRestaurant.getAddress());
			preStatement.setString(3, newRestaurant.getDescription());
			preStatement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addRating(Ratings newRating) {
		String sql= "Insert into ratings(RESTAURANT_ID,USER_ID,Stars,description,REVIEWDATE) values(?, ?, ?,'?',TO_DATE('?','MM/DD/YYYY'))";

		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, newRating.getRestaurant_id());
			preStatement.setInt(2, newRating.getUser_id());
			preStatement.setInt(3, newRating.getStars());
			preStatement.setString(4, newRating.getDescription());
			preStatement.setString(5, newRating.getReviewDate());
			preStatement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isReviewed(int reviewerId,int restaurant_id) {
		String sql= "SELECT STARS FROM RATINGS WHERE USER_ID = ? and RESTAURANT_ID = ?";
		
		ResultSet rs = null;
		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, reviewerId);
			preStatement.setInt(2, restaurant_id);
			rs=preStatement.executeQuery(sql);
			if(rs.next()){
				return true;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
