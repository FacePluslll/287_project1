public class main {
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
	while (userInput.compareTo("exit") != 0) {
		System.out.print("\nEnter a menu option: ");
		userInput = scnr.nextLine().trim();
		
		
		// If statements to check the user's input
		if (userInput.compareTo("dsply") == 0) {
			// Displays the movies
		}
		if (userInput.compareTo("addM") == 0) {
			// Adds a movie
		}
		if (userInput.compareTo("editDte") == 0) {
			// Edit a movie's release date
		}
		if (userInput.compareTo("editMDesc") == 0) {
			// Edit a movie's description
		}
		if (userInput.compareTo("showM") == 0) {
			// Start showing movies in theatre
		}
		if (userInput.compareTo("numMDte") == 0) {
			// Number of movies showing before a given date
			String userDate;
			System.out.println("Please enter a date in the format dd/mm/yyyy: ");
			userDate = scnr.nextLine().trim();
		}
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
