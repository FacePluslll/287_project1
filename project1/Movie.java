import java.util.*;
import java.text.*;
import java.util.Date;
//package project;
public class Movie implements Comparable<Movie> {
	private Date releaseDate;
	private String name;
	private String description;
	private Date receiveDate;
	enum Status {
		RECEIVED,
		RELEASED
	}
	private Status status;
	// mov = new Movie(name, releaseDate, description, receiveDate, strStatus);
	public Movie(String name, Date releaseDate, String description, Date receiveDate, String strStatus) {
		this.name = name;
		this.releaseDate = releaseDate;
		this.description = description;
		this.receiveDate = receiveDate;
		this.status = Status.valueOf(strStatus);
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public Status getStatus() {
		return status;
	}
	public int compareTo(Movie m) {
		int compare = this.releaseDate.compareTo(m.getReleaseDate());
		return compare;
	}
	public void setName(String newName) {
		this.name = newName;
	}
	// mov = new Movie(name, releaseDate, description, receiveDate, strStatus);
	public void setReleaseDate(Date newReleaseDate) {
		this.releaseDate = newReleaseDate;
	}
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	public void setReceiveDate(Date newReceiveDate) {
		this.receiveDate = newReceiveDate;
	}
	public void setReceiveDate(String newStrStatus) {
		this.status = Status.valueOf(newStrStatus);
	}

	@Override
	public String toString() {
		DateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		String relDate = sdf.format(this.releaseDate);
		String recDate = sdf.format(this.receiveDate);
		return(this.name + ", " + this.description + ", " + recDate+ ", " + relDate + ", " + this.status);
	}
}
