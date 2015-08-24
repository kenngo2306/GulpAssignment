
public class Reviewer {

	private int Reviewer_id;
	private String Reviewer_Name;
	private String email;
	private String zipcode;
	
	public int getReviewer_id() {
		return Reviewer_id;
	}
	public void setReviewer_id(int reviewer_id) {
		Reviewer_id = reviewer_id;
	}
	public String getReviewer_Name() {
		return Reviewer_Name;
	}
	public void setReviewer_Name(String reviewer_Name) {
		Reviewer_Name = reviewer_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
