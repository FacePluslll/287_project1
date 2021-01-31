import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class menu {
	public static ArrayList<Movie> movieListCOMING = new ArrayList<Movie>();
	public static ArrayList<Movie> movieListSHOWING = new ArrayList<Movie>();
    public static void main(String[] args) {
		
		System.out.println("--------------------------------------------Menu--------------------------------------------");
		System.out.println("Choose one of the following options, to choose, type the input shown in quotes; ex: \"prnt\"");
		System.out.println(" * Please note, the menu choices are CASE SENSITIVE!\n");
		System.out.println(" - Display movies                     \"dsply\"");
		System.out.println(" - Add movies                         \"addM\"");
		System.out.println(" - Edit release Date                  \"editDte\"");
		System.out.println(" - Edit movie description             \"editMDesc\"");
		System.out.println(" - Start showing movies in theatre    \"showM\"");
		System.out.println(" - Number of movies before a date     \"numMDte\"");
		System.out.println(" - Save                               \"save\"");
		System.out.println("\nType \"exit\" to exit the program.");
		System.out.println("--------------------------------------------------------------------------------------------");
		
		
		Scanner scnr = new Scanner(System.in);
		
		String userInput = "";
		//Remove when readable files is done___________________________________________________
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);

		Date m1Arrived = new Date();
		Date m1Released = new Date();

		m1Arrived = dateFormat.parse("12/12/1997",pos);
		pos.setIndex(0);
		m1Released = dateFormat.parse("12/19/1997",pos);

		Date m2Arrived = new Date();
		Date m2Released = new Date();

		pos.setIndex(0);
		m2Arrived = dateFormat.parse("09/25/2019",pos);
		pos.setIndex(0);
		m2Released = dateFormat.parse("10/5/2019",pos);

		Movie m1 = new Movie("Titanic", m1Released, "Romance/Drama/Historical", m1Arrived, "RELEASED");
		Movie m2 = new Movie("Parasite", m2Released, "Drama/Thriller", m2Arrived, "RECEIVED");
		System.out.println(m1.getName());
		System.out.println(m1.getReleaseDate());
		System.out.println(m1.getReceiveDate());
		System.out.println(m1.getDescription());
		System.out.println(m1.getStatus());

		System.out.println(m2.getName());
		System.out.println(m2.getReleaseDate());
		System.out.println(m2.getReceiveDate());
		System.out.println(m2.getDescription());
		System.out.println(m2.getStatus());
		movieListSHOWING.add(m1);
		movieListCOMING.add(m2);

		Calendar cal = Calendar.getInstance();
		cal.setTime(m1Arrived);
		System.out.println(dateFormat.format(m1Arrived));
		//Remove when readable files is done___________________________________________________

		while (userInput.compareTo("exit") != 0) {
			System.out.print("\nEnter a menu option: ");
			userInput = scnr.nextLine().trim();
			
			// If statements to check the user's input
			//sonal
			if (userInput.compareTo("dsply") == 0) {
				// Displays the movies
			}
			//erik
			if (userInput.compareTo("addM") == 0) {
				// Adds a movie
				addMovie();
			}
			//sonal
			if (userInput.compareTo("editDte") == 0) {
				// Edit a movie's release date
			}
			//sonal
			if (userInput.compareTo("editMDesc") == 0) {
				// Edit a movie's description
			}
			//erik
			if (userInput.compareTo("showM") == 0) {
				// Start showing movies in theatre
				showMovie();
			}
			//erik
			if (userInput.compareTo("numMDte") == 0) {
				// Number of movies showing before a given date
				countMovies();
			}
			//erik
			if (userInput.compareTo("save") == 0) {
				// Saves the file changes
			}
			
			//End of user input comparison
			
		}
		if (userInput.compareTo("exit") == 0) {
			System.out.println("\nProgram exited. Have a nice day.");
		}
		
		scnr.close();
	}
	// This function adds a moving to the showing list from the coming list...
	private static void showMovie(){
		Iterator<Movie> iter = movieListCOMING.iterator();
		ArrayList<Movie> moviesToShow = new ArrayList<Movie>();

		Date compareDate = new Date();
		Movie tempMovie = new Movie("name", compareDate, "description", compareDate, "RELEASED");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		
		Scanner scnr = new Scanner(System.in);
		String userInput;

		System.out.println("Please enter a date in the format dd/mm/yyyy: ");
		userInput = scnr.nextLine().trim();
		compareDate = dateFormat.parse(userInput,pos);

		while(compareDate == null){
			System.out.println("Please enter a correct date in the format dd/mm/yyyy or type exit to return to menu...: ");
			userInput = scnr.nextLine().trim();
			compareDate = dateFormat.parse(userInput,pos);
			if(userInput.compareTo("exit") == 0){
				scnr.close();
				return;
			}
		}
		//adds all movies to list with given date
		while(iter.hasNext()){
			tempMovie = iter.next();
			if(compareDate.equals(tempMovie.getReleaseDate())){
				moviesToShow.add(tempMovie);
			}
		}
		//check: removes possible dupe from moviesToShow
		iter = movieListSHOWING.iterator();
		while(iter.hasNext()){
			tempMovie = iter.next();
			if(movieListSHOWING.contains(tempMovie)){
				moviesToShow.remove(tempMovie);
			}
		}
		//adds all movies from the list to movielistSHOWING
		int count = 0;
		iter = moviesToShow.iterator();
		while(iter.hasNext()){
			tempMovie = iter.next();
			movieListSHOWING.add(tempMovie);
			count++;
		}

		System.out.println("Amount of movies added: "+count);
		scnr.close();
		return;


	}
	//This function counts the amount of Showing movies before the given date and prints them, otherwise just prints "No movies are being shown before this date!"
	private static void countMovies(){
		Scanner scnr = new Scanner(System.in);
		String userInput;
		Date compareDate = new Date();

		Movie tempMovie = new Movie("name", compareDate, "description", compareDate, "RELEASED");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);

		Iterator<Movie> iter = movieListSHOWING.iterator();

		ArrayList<Movie> moviesToShow = new ArrayList<Movie>();

		System.out.println("Please enter a date in the format dd/mm/yyyy: ");
		userInput = scnr.nextLine().trim();
		compareDate = dateFormat.parse(userInput,pos);

		while(compareDate == null){
			System.out.println("Please enter a correct date in the format dd/mm/yyyy or type exit to return to menu...: ");
			userInput = scnr.nextLine().trim();
			compareDate = dateFormat.parse(userInput,pos);
			if(userInput.compareTo("exit") == 0){
				scnr.close();
				return;
			}
		}
		//adds movies to the moviesToShow list if it passes condition of being before date or equal to
		while(iter.hasNext()){
			tempMovie = iter.next();
			if(compareDate.after(tempMovie.getReleaseDate()) || compareDate.equals(tempMovie.getReleaseDate())){
				moviesToShow.add(tempMovie);
			}
		}
		int count = 0;
		//Checks if movie is empty, else print movie information
		if(moviesToShow.isEmpty()){
			System.out.println("No movies are being shown before this date! Returning to menu...");
		}
		else{
			iter = moviesToShow.iterator();
			while(iter.hasNext()){
				tempMovie = iter.next();
				System.out.println("\n-------Movie info-------");
				System.out.println("Name: " + tempMovie.getName());
				System.out.println("Genre/description: " + tempMovie.getDescription());
				System.out.println("Receive date: " + tempMovie.getReceiveDate());
				System.out.println("Release date: " + tempMovie.getReleaseDate());
				System.out.println("Status: " + tempMovie.getStatus());
				System.out.println("------------------------");
				count++;
			}
			System.out.println("Amount of Movies: " + count);
			System.out.println("------------------------");
		}
		scnr.close();
		return;
	}

	//This fucntion adds an movie to the movieList, returns nothing and has no parms
	private static void addMovie() {
		// RECEIVED RELEASED
		Scanner scnr = new Scanner(System.in);
		String userInput;

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		Iterator<Movie> iter = movieListCOMING.iterator();

		Date releaseDate = new Date();
		Date receiveDate = new Date();
		String curStatus = "RECEIVED"; 

		Movie addingMovie = new Movie("name", releaseDate, "description", receiveDate, curStatus);
		Movie tempMovie;

		Boolean isInputInCorrectFormat = true;

		//Adds the name and checks if its been used yet...
		System.out.print("\nEnter movie name: ");
		userInput = scnr.nextLine().trim();
		while(iter.hasNext()){
			tempMovie = iter.next();

			if(tempMovie.getName().compareTo(userInput) == 0){
				isInputInCorrectFormat = false;
			}
			if(!isInputInCorrectFormat){
				//need to reset iterator, declaring it again will reset it
				iter = movieListCOMING.iterator();

				isInputInCorrectFormat = true;
				System.out.print("\nYou entered the name incorrectly and you must try again... type exit to return the menu or anything to try again...");
				userInput = scnr.nextLine().trim();
				if(userInput.compareTo("exit") == 0){
					scnr.close();
					return;
				}
				System.out.print("\nEnter movie name: ");
				userInput = scnr.nextLine().trim();
			}
		}	
		//made past loop check, add movie name to new movie
		addingMovie.setName(userInput);

		//Adds description to movie... nothing to do special here move along...
		System.out.print("\nEnter movie genre(s): ");
		userInput = scnr.nextLine().trim();
		addingMovie.setDescription(userInput);

		// This will loop the two loops until the dates are correct or until the user exits the addM function
		while(true){
			//This adds recieve Date
			while(isInputInCorrectFormat){
				System.out.print("\nEnter arrival date in the format dd/mm/yyyy: ");
				userInput = scnr.nextLine().trim();
				//The sting length is greater then 6 and less then 10 making it atleast the bare minium of the date string
				if(userInput.length() >= 6 && userInput.length() <=10){
					receiveDate = dateFormat.parse(userInput,pos);
					pos.setIndex(0);
					//Check if the format has been correctly added, if not make isInputInCorrectFormat to false
					if(receiveDate == null){
						isInputInCorrectFormat = false;
					}
					else{
						//break from loop since the input should be correct format
						break;
					}
				}
				else{
					isInputInCorrectFormat = false;
				}

				if(!isInputInCorrectFormat){
					isInputInCorrectFormat = true;
					System.out.print("\nYou entered the date incorrectly and you must try again... type exit to return the menu or anything to try again...");
					userInput = scnr.nextLine().trim();
					if(userInput.compareTo("exit") == 0){
						scnr.close();
						return;
					}
					userInput = "";
				}
			}

			//This adds release Date
			while(isInputInCorrectFormat){
				System.out.print("\nEnter release date in the format dd/mm/yyyy: ");
				userInput = scnr.nextLine().trim();
				//The sting length is greater then 6 and less then 10 making it atleast the bare minium of the date string
				if(userInput.length() >= 6 && userInput.length() <=10){
					releaseDate = dateFormat.parse(userInput,pos);
					pos.setIndex(0);
					//Check if the format has been correctly added, if not make isInputInCorrectFormat to false
					if(releaseDate == null){
						isInputInCorrectFormat = false;
					}
					else{
						//break from loop since the input should be correct format
						break;
					}
				}
				else{
					isInputInCorrectFormat = false;
				}

				if(!isInputInCorrectFormat){
					isInputInCorrectFormat = true;
					System.out.print("\nYou entered the date incorrectly and you must try again... type exit to return the menu or anything to try again...");
					userInput = scnr.nextLine().trim();
					if(userInput.compareTo("exit") == 0){
						scnr.close();
						return;
					}
					userInput = "";
				}
			}
			//TODO: discuss if the dates can or can not equal eachother
			if(releaseDate.before(receiveDate)){
				System.out.print("\nYou entered the dates incorrectly and you must try again(the releasedate is before the receivedate)... type exit to return the menu or anything to try again...");
				userInput = scnr.nextLine().trim();
				if(userInput.compareTo("exit") == 0){
					scnr.close();
					return;
				}
			}
			else{
				break;
			}

		}
		//The dates have passed the trials of conditions and been deemed worthy of being added to newMovie.
		addingMovie.setReceiveDate(receiveDate);
		addingMovie.setReleaseDate(releaseDate);

		movieListCOMING.add(addingMovie);
		scnr.close();
		return;
	}
}
