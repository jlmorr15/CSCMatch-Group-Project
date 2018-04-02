import java.util.LinkedList;
import java.util.Scanner;

public class CSCMatch {
	
	private String userInput;
	private String memberDataFile;
	private String interestDataFile;
	
	private Scanner scanner;
	
	private LinkedList<Member> members;
	
	public CSCMatch()
	{
		userInput = "x"; //Default to the exit state
		memberDataFile = "members.ser"; //Default to members.ser
		interestDataFile = "interests.ser"; //Default to interests.ser
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args)
	{
		System.out.println("\n ______    ______    ______    __    __   ______   ______  ______   __  __    \r\n" + 
				"/\\  ___\\  /\\  ___\\  /\\  ___\\  /\\ \"-./  \\ /\\  __ \\ /\\__  _\\/\\  ___\\ /\\ \\_\\ \\   \r\n" + 
				"\\ \\ \\____ \\ \\___  \\ \\ \\ \\____ \\ \\ \\-./\\ \\\\ \\  __ \\\\/_/\\ \\/\\ \\ \\____\\ \\  __ \\\r\n" + 
				" \\ \\_____\\ \\/\\_____\\ \\ \\_____\\ \\ \\_\\ \\ \\_\\\\ \\_\\ \\_\\  \\ \\_\\ \\ \\_____\\\\ \\_\\ \\_\\\r\n" + 
				"  \\/_____/  \\/_____/  \\/_____/  \\/_/  \\/_/ \\/_/\\/_/   \\/_/  \\/_____/ \\/_/\\/_/\r\n" + 
				"");
		System.out.println("Emilio Gonzalez, Justin Morris, Adan Partida, Joshua Warren");
		
		
		CSCMatch app = new CSCMatch();
		do {
			app.showMenu();
			app.userInput = app.scanner.next();
			
			app.evaluateInput();
			
		} while(!app.userInput.toLowerCase().equals("x") && !app.userInput.toLowerCase().equals("exit"));
	}
	
	/** 
	 * showMenu
	 * Shows the main menu to users
	 */
	public void showMenu()
	{
		System.out.println("\nCSC Match Main Menu:\n");
		System.out.println("[M] Load Member Data From File");
		System.out.println("[S] Save Member Data To File");
		System.out.println("[L] List All Members");
		System.out.println("[A] Add A Member");
		System.out.println("[R] Remove A Member");
		System.out.println("[D] Display Member Data");
		System.out.println("[I] Add An Interest To A Member");
		System.out.println("[T] Test application");
		System.out.println("[X] Quit");
		
		System.out.print("\r\nWhat would you like to do?: ");
	}
	
	public void evaluateInput()
	{
		switch(userInput.toLowerCase())
		{
		case "x":
			System.out.println("\nExit Initialized. (╯°□°）╯︵ ┻━┻");
			break;
		case "t":
			this.test();
			break;
		}
	}
	
	public void test()
	{
		System.out.println("Initializing Unit Tests...");
		
		//CREATE MEMBER
		System.out.println("Creating Member John Doe...");
		Member john = new Member("John", "Doe", "JOH1234567", 1);
		System.out.println("Member Data: "+john.toString());
		
		System.out.println("Adding Interests To "+john.toString());
		john.addInterest("C++", 7);
		john.addInterest("Java", 10);
		System.out.println("Listing Interests for "+john.toString());
		john.listInterests();
	}
}
