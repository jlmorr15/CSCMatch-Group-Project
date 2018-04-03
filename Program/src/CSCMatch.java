import java.util.LinkedList;
import java.util.Scanner;

public class CSCMatch {
	
	private String userInput;
	private String memberDataFile;
	private String interestDataFile;
	
	private Scanner scanner;
	
	private LinkedList<Member> members;
	private Membership membership;
	
	public CSCMatch()
	{
		userInput = "x"; //Default to the exit state
		memberDataFile = "members.ser"; //Default to members.ser
		interestDataFile = "interests.ser"; //Default to interests.ser
		membership = new Membership();
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
		System.out.println("[T1] TEST: adding Members with Interests and saving");
		System.out.println("[T2] TEST: loading members from file and listing");
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
			
		case "l":
			System.out.println("Listing Members");
			membership.listMembers();
		case "t1":
			this.test();
			break;
			
		case "t2":
			System.out.println("Loading Data From Disk");
			membership.loadMembers();
			System.out.println("Listing Members");
			membership.listMembers();
			break;
		}
	}
	
	public void test()
	{
		System.out.println("Initializing Unit Tests...");
		
		//CREATE MEMBER
		System.out.println("Creating Member John Doe...");
		Member john = new Member("John", "Doe", "JOH1234567", 1);
		System.out.println("Creating Member Jane Doe...");
		Member jane = new Member("Jane", "Doe", "JAN8901234", 1);
		
		membership.addMemberToList(john); //directly add to members list.
		membership.addMemberToList(jane);
		
		System.out.println("Member Data: "+john);
		System.out.println("Member Data: "+jane);
		
		System.out.println("Adding Interests To "+john);
		john.addInterest("C++", 7);
		john.addInterest("Java", 10);
		System.out.println("Listing Interests for "+john);
		john.listInterests();
		System.out.println("Updating C++ to a 9");
		john.addInterest("C++", 9);
		john.listInterests();
		
		System.out.println("Adding Interests To "+jane);
		jane.addInterest("C++", 5);
		jane.addInterest("Java", 8);
		jane.addInterest("Assembly", 3);
		System.out.println("Listing Interests for "+jane);
		jane.listInterests();
		System.out.println("Updating Assembly to a 5");
		jane.addInterest("Assembly", 5);
		jane.listInterests();
		
		System.out.println("Saving Data To Disk");
		membership.saveMembers();
	}
}
