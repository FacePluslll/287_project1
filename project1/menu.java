import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class menu {
	public static ArrayList<Movie> movieListCOMING = new ArrayList<Movie>();
	public static ArrayList<Movie> movieListSHOWING = new ArrayList<Movie>();
	public static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException{
		loadlist();
		orderList();
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
		
		String userInput = "";

		while (userInput.compareTo("exit") != 0) {
			userInput = "";
			System.out.print("\nEnter a menu option: ");
			userInput = scnr.nextLine().trim();
			
			// If statements to check the user's input
			//sonal
			if (userInput.compareTo("dsply") == 0) {
				// Displays the movies
				dsplyMovie();
			}
			//erik
			else if (userInput.compareTo("addM") == 0) {
				// Adds a movie
				addMovie();
			}
			//sonal
			else if (userInput.compareTo("editMDesc") == 0) {
				// Edit a movie's release date
				editDesc();

			}
			//sonal
			else if (userInput.compareTo("editDte") == 0) {
				// Edit a movie's description
				editDate();
			}
			//erik
			else if (userInput.compareTo("showM") == 0) {
				// Start showing movies in theatre
				showMovie();
			}
			//erik
			else if (userInput.compareTo("numMDte") == 0) {
				// Number of movies showing before a given date
				countMovies();
			}
			//erik/Joey
			else if (userInput.compareTo("save") == 0) {
				// Saves the file changes
				saveList();
			}
			
			//End of user input comparison
		}
		if (userInput.compareTo("exit") == 0) {
			System.out.println("\nProgram exited. Have a nice day.");
		}
		scnr.close();
	}
	
	private static void editDate() {
		String movieName;
		String userInput;
		Movie tempovie = new Movie();
		boolean isRightMovie = false;
		System.out.print("Please enter a movie name: ");
		movieName = scnr.nextLine().trim();
		Iterator<Movie> iter = movieListCOMING.iterator();

		while(iter.hasNext()){
			tempovie = iter.next();
			if(tempovie.getName().compareTo(movieName) == 0){
				isRightMovie = true;
				break;
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		Date newDate = new Date();
		if(isRightMovie){
			System.out.print("Please enter a new release date for movie "+ tempovie.getName()+": ");
			userInput = scnr.nextLine().trim();
			newDate = dateFormat.parse(userInput,pos);
			pos.setIndex(0);
			if(newDate.before(tempovie.getReceiveDate())){
				System.out.println("Date was wrongly entered returning to menu.");
			}
			else{
				tempovie.setReleaseDate(newDate);
			}
		}
		else{
			System.out.println("Could not find movie returning to menu.");
		}
	}

	private static void editDesc() {
		String movieName;
		String userInput;
		Movie tempovie = new Movie();
		boolean isRightMovie = false;
		System.out.print("Please enter a movie name: ");
		movieName = scnr.nextLine().trim();
		Iterator<Movie> iter = movieListCOMING.iterator();

		while(iter.hasNext()){
			tempovie = iter.next();
			if(tempovie.getName().compareTo(movieName) == 0){
				isRightMovie = true;
				break;
			}
		}
		if(isRightMovie){
			System.out.print("Please enter a new Description for movie "+ tempovie.getName()+": ");
			userInput = scnr.nextLine().trim();
			tempovie.setDescription(userInput);
		}
		else{
			System.out.println("Could not find movie returning to menu.");
		}
	}

	private static void dsplyMovie() {
		Movie tempovie = new Movie();
		for (int i = 0; i < movieListCOMING.size(); ++i){
			tempovie = movieListCOMING.get(i);
			tempovie.displayMovie();
		}
					
		for (int i = 0; i < movieListSHOWING.size(); ++i){
			tempovie = movieListSHOWING.get(i);
			tempovie.displayMovie();
		}
	}

	//Orders the list into non-descending order
	// https://www.sanfoundry.com/java-program-sort-array-descending-order/ for the logic but edited to fit the code of our project ~Erik Sklocic
	public static void orderList() {
		ArrayList<Movie> tempMovieList = new ArrayList<Movie>();
		Movie tempMovie = new Movie();
		Movie tempMovieComing = new Movie();
		for(int i = 0; i < movieListCOMING.size(); i++){
			tempMovie = movieListCOMING.get(i);
			for(int x = i + 1; x < movieListCOMING.size(); x++){
				tempMovieComing = movieListCOMING.get(x);
				if(tempMovieComing.getReleaseDate().before(tempMovie.getReleaseDate())){
					tempMovieComing = movieListCOMING.get(i);
					movieListCOMING.set(x, movieListCOMING.get(i));
					movieListCOMING.set(i, tempMovieComing);
				}
			}
		}
	}
	//Combines both list and saves them to input.txt with the use of DataRead
	private static void saveList() throws IOException{
		ArrayList<Movie> tempMovieList = new ArrayList<Movie>();
		tempMovieList.addAll(movieListCOMING);
		tempMovieList.addAll(movieListSHOWING);
		DataRead.save(tempMovieList);
	}

	//This function loads the list from DataRead and spilts them into there respescted list
	private static void loadlist() throws IOException, ParseException{
		ArrayList<Movie> tempMovieList = new ArrayList<Movie>();
		tempMovieList = DataRead.readFile();
		Iterator<Movie> iter = tempMovieList.iterator();

		Movie tempMovie = new Movie();

		while(iter.hasNext()){
			tempMovie = iter.next();
			if(tempMovie.getStatus() == Movie.Status.RELEASED){
				movieListSHOWING.add(tempMovie);
			}
			else if(tempMovie.getStatus() == Movie.Status.RECEIVED){
				movieListCOMING.add(tempMovie);
			}
		}
		return;
		
	}
	// This function adds a moving to the showing list from the coming list...
	private static void showMovie(){
		Iterator<Movie> iter = movieListCOMING.iterator();
		ArrayList<Movie> moviesToShow = new ArrayList<Movie>();

		Date compareDate = new Date();
		Movie tempMovie = new Movie("name", compareDate, "description", compareDate, "RELEASED");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		
		String userInput;

		System.out.println("Please enter a date in the format dd/mm/yyyy: ");
		userInput = scnr.nextLine().trim();
		compareDate = dateFormat.parse(userInput,pos);

		while(compareDate == null){
			System.out.println("Please enter a correct date in the format dd/mm/yyyy or type exit to return to menu...: ");
			userInput = scnr.nextLine().trim();
			compareDate = dateFormat.parse(userInput,pos);
			if(userInput.compareTo("exit") == 0){
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
		//adds all movies from the list to movielistSHOWING and removes it from the coming list
		int count = 0;
		iter = moviesToShow.iterator();
		while(iter.hasNext()){
			tempMovie = iter.next();
			tempMovie.setReceiveStatus("RELEASED");
			movieListSHOWING.add(tempMovie);
			movieListCOMING.remove(tempMovie);
			count++;
		}

		System.out.println("Amount of movies added: "+count);
		return;


	}
	//This function counts the amount of Showing movies before the given date and prints them, otherwise just prints "No movies are being shown before this date!"
	private static void countMovies(){
		String userInput;
		Date compareDate = new Date();

		Movie tempMovie = new Movie("name", compareDate, "description", compareDate, "RELEASED");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);

		Iterator<Movie> iter = movieListCOMING.iterator();

		ArrayList<Movie> moviesToShow = new ArrayList<Movie>();

		System.out.println("Please enter a date in the format dd/mm/yyyy: ");
		userInput = scnr.nextLine().trim();
		compareDate = dateFormat.parse(userInput,pos);

		while(compareDate == null){
			System.out.println("Please enter a correct date in the format dd/mm/yyyy or type exit to return to menu...: ");
			userInput = scnr.nextLine().trim();
			compareDate = dateFormat.parse(userInput,pos);
			if(userInput.compareTo("exit") == 0){
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
		return;
	}

	//This fucntion adds an movie to the movieList, returns nothing and has no parms
	private static void addMovie() {
		// RECEIVED RELEASED
		String userInput;

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		Iterator<Movie> iter = movieListCOMING.iterator();
		Iterator<Movie> iter2 = movieListSHOWING.iterator();

		Date releaseDate = new Date();
		Date receiveDate = new Date();
		String curStatus = "RECEIVED"; 

		Movie addingMovie = new Movie("name", releaseDate, "description", receiveDate, curStatus);
		Movie tempMovie;

		Boolean isInputInCorrectFormat = true;

		//Adds the name and checks if its been used yet...
		System.out.print("\nEnter movie name: ");
		userInput = scnr.nextLine().trim();

		Boolean bothCondtionsPassed = true;

		//Checks if name is in the two list, if so repeat untill its not or user exits
		while(true){
			while(iter.hasNext()){
				tempMovie = iter.next();

				if(tempMovie.getName().compareTo(userInput) == 0){
					isInputInCorrectFormat = false;
					bothCondtionsPassed = true;
				}
				if(!isInputInCorrectFormat){
					//need to reset iterator, declaring it again will reset it
					iter = movieListCOMING.iterator();

					isInputInCorrectFormat = true;
					System.out.print("\nYou entered the name incorrectly and you must try again... type exit to return the menu or anything to try again...");
					userInput = scnr.nextLine().trim();
					if(userInput.compareTo("exit") == 0){
						return;
					}
					System.out.print("\nEnter movie name: ");
					userInput = scnr.nextLine().trim();
				}
			}	
			while(iter2.hasNext()){
				tempMovie = iter2.next();

				if(tempMovie.getName().compareTo(userInput) == 0){
					isInputInCorrectFormat = false;
					bothCondtionsPassed = true;
				}
				if(!isInputInCorrectFormat){
					//need to reset iterator, declaring it again will reset it
					iter = movieListCOMING.iterator();

					isInputInCorrectFormat = true;
					System.out.print("\nYou entered the name incorrectly and you must try again... type exit to return the menu or anything to try again...");
					userInput = scnr.nextLine().trim();
					if(userInput.compareTo("exit") == 0){
						return;
					}
					System.out.print("\nEnter movie name: ");
					userInput = scnr.nextLine().trim();
				}
			}	
			if(!bothCondtionsPassed){
				break;
			}
			else{
				bothCondtionsPassed = false;
			}
		}
		//made past loop checks, add movie name to new movie
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
		return;
	}
}
