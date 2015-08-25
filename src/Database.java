import java.sql.*;
import java.util.*;

public class Database {

	Restaurant restObj;
	Reviewer reviewerObj;
	Ratings ratingObj;

	private static Statement st;
	private static Connection conn;

	private void openConnection() {
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

	public void updateDB(String sql) throws SQLException {

		PreparedStatement preStatement = conn.prepareStatement(sql);

		preStatement.setQueryTimeout(10);
		preStatement.executeUpdate();

	}

	public Reviewer getReviewer(String email) {
		String sql= "Select * from reviewer where email=?";
		System.out.println(sql);
		reviewerObj = new Reviewer();
		ResultSet rs = null;
		try {
			openConnection();
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, email);
			rs=preStatement.executeQuery();
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

	public ArrayList<Restaurant> getAllRestaurant() {
		String sql= "Select * from restaurant";
		ArrayList<Restaurant> restArray= new ArrayList<Restaurant>();
		ResultSet rs = null;
		try {
			PreparedStatement preStatement = conn.prepareStatement(sql);
			rs=preStatement.executeQuery(sql);
			while(rs.next()){
				restObj=new Restaurant();
				restObj.setRestaurant_id(rs.getInt(1));
				restObj.setRestaurant_Name(rs.getString(2));
				restObj.setAddress(rs.getString(3));
				restObj.setDescription(rs.getString(4));
				restArray.add(restObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restArray;
	}
	
	public void getRestaurant(int restaurant_id) {
		String sql= "Select * from restaurant where restaurant_id =?";
		
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
