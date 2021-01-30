import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class DataRead {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		readFile();
	}
	
	
	/***
	 * 
	 * @return ArrayList of strings that represent the variables; however, they would lose their types: ie, Date and enum if it is returned like this which the directions specify to use certain types.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<String> readFile() throws IOException, ParseException{
		FileInputStream inputFile = new FileInputStream("input.txt"); //Creates a fileInputStream that will receive from input.txt
		Scanner scnr = new Scanner(inputFile); //Creates a scanner that will read from inputFile, input.txt
		FileOutputStream outputFile = new FileOutputStream("output.txt");
		PrintWriter writer = new PrintWriter(outputFile);
	
		//Creating variables that will store the data
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); //Uses SimpleDateFormat to receive a string input in the form of month/day/year . ex: 04/30/2021
		Date releaseDate = new Date(); // This is a Date type called releaseDate that will store the release date in month/day/year format
		Date receiveDate = new Date();// This is a Date type called receiveDate that will store the release date in month/day/year format
		String name; //This is a string that will store the name of the movie
		String description; //This is a string that will store the genre/description of the movie
		Status curStatus = null; //This calls the enum that sets the current status of the movie
		ArrayList<String> movie = new ArrayList<String>(); // This will be the final ArrayList that will store the movie info
		
		
		String line = "";
		while (scnr.hasNext()) { //Checks to see if there is another line in the file
			line = scnr.nextLine(); // Sets a variable equal to that line
			String[] lineList = line.split(",");
			for (int i = 0; i < lineList.length; i++) {
				lineList[i] = lineList[i].trim();
			}
			
			name = lineList[0]; //Sets the name variable equal to the first item in the linLiest, which if properly formatted is the name
			description = lineList[1]; //Sets the description variable equal to the second item in the lineList, which if properly formatted is the description
			receiveDate = dateFormat.parse(lineList[2]); //Sets the receiveDate equal to lineList[2] which is converted to a Date type with the variable dateFormat
			releaseDate = dateFormat.parse(lineList[3]); //Sets the releaseDate equal to lineList[3] which is converted to a Date type with the variable dateFormat
			
			
			//Checks to see if the movie status is received or released, then sets the enum accordingly
			if (lineList[4].equals("received")) {
				curStatus = Status.RECEIVED;
			}
			else if (lineList[4].equals("released")) {
				curStatus = Status.RELEASED;
			}
			else {
				System.out.println("Invalid status type");
			}
			
			/*
			//Prints a formatted version of all the data that shows how the data can be used
			System.out.println("\n-------Movie info-------");
			System.out.println("Name: " + name);
			System.out.println("Genre/description: " + description);
			System.out.println("Receive date: " + receiveDate);
			System.out.println("Release date: " + releaseDate);
			System.out.println("Status: " + curStatus); */
			
 		}
		
		//Closes all open writers, fileInput streams, files, and fileOutputStreams
		writer.close();
		scnr.close();
		inputFile.close();
		
		
		return movie;
		
	}
	
	public enum Status{RECEIVED, RELEASED}

}
