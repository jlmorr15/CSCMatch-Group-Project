import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CSCMatch {
	
	private String userInput;
	private String memberDataFile;
	private String interestDataFile;
	
	private Scanner scanner;
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
	private void showMenu()
	{
		System.out.println("\nCSC Match Main Menu:\n");
		System.out.println("[M] Load Member Data From File");
		System.out.println("[S] Save Member Data To File");
		System.out.println("[L] List All Members");
		System.out.println("[A] Add A Member");
		System.out.println("[R] Remove A Member");
		System.out.println("[D] Display Member Data");
		System.out.println("[I] Add An Interest To A Member");
		//System.out.println("[T1] TEST: adding Members with Interests and saving");
		//System.out.println("[T2] TEST: loading members from file and listing");
		System.out.println("[X] Quit");
		
		System.out.print("\r\nWhat would you like to do?: ");
	}
	
	public void evaluateInput()
	{
		switch(userInput.toLowerCase())
		{
		case "m":
			loadMembershipList();
			break;
		case "s":
			saveMembershipList();
			break;
		case "l":
			System.out.println("Listing Members");
			membership.listMembers();
			break;
		case "a":
			addNewMember();
			break;
		case "r":
			removeMember();
			break;
		case "d":
			displayMember();
			break;
		case "i":
			addMemberInterest();
			break;
		case "x":
			System.out.println("\nExit Initialized. (╯°□°）╯︵ ┻━┻");
			break;	
		case "t1":
			this.test();
			break;
			
		case "t2":
			System.out.println("Loading Data From Disk"); 
			//membership.loadMembers();
			System.out.println("Listing Members");
			membership.listMembers();
			break;
		}
	}
	
	
	private void addMemberInterest()
	{
		boolean cancel = false;
		String meid = requestExistingMEID();
		String interest = "";
		int level = -1;
		
		if(!meid.equals("x")) {
			try {
				while(!cancel && interest.isEmpty()) {
					System.out.print("Enter an Interest [x: cancel]: ");
					interest = scanner.next();
					if(interest.equals("x"))
						cancel = true;
				}
				
				while(!cancel && (level<0 && level != 99)) {
					System.out.print("Enter an Interest Level 0-10 [99: cancel]: ");
					level = scanner.nextInt();
					if(level==99) {
						cancel = true;
					} else if(level<0 || level>10) {
						System.out.println("Invalid Interest Level Provided.");
						level=-1;
					}
				}
				
				if(!cancel) {
					Member mbr = membership.getMemberByMEID(meid);
					mbr.addInterest(interest, level);
					System.out.println("Interest Added To Member.");
				}
			} catch(NoSuchElementException ex) {
				System.out.println("Member Not Found In Membership List");
			}
		}
	}
	
	private void displayMember()
	{
		String meid = requestMEID();
		membership.listMember(meid);
	}
	
	private void saveMembershipList()
	{
		boolean saved = false;
		boolean cancel = false;
		while(!saved && !cancel)
		{
			System.out.println("Enter Membership List Filename or [d: default] [x: cancel]");
			System.out.print("Members File: ");
			String fileName = "";
			fileName = this.scanner.next();
			if(fileName.equals("d")) {
				saved = membership.saveMembers("members.ser");
			} else if(fileName.equals("x")) {
				cancel = true;
				System.out.println("Canceled loading membership file...");
			} else {
				saved = membership.saveMembers(fileName);
			}
		}
		if(!cancel) {
			System.out.println("Saved "+ membership.numMembers() +" members to file");
		}
	}
	
	private void loadMembershipList()
	{
		boolean loaded = false;
		boolean cancel = false;
		while(!loaded && !cancel) {
			System.out.print("Enter Membership List Filename or [d: default] [x: cancel]");
			System.out.print("\nMembers File: ");
			String fileName = "";
			fileName = this.scanner.next();
			if(fileName.equals("d")) {
				loaded = membership.loadMembers("members.ser");
			} else if(fileName.equals("x")) {
				cancel = true;
				System.out.println("Canceled loading membership file...");
			} else {
				loaded = membership.loadMembers(fileName);
			}
		}
		if(!cancel) {
			System.out.println("Membership file loaded "+ membership.numMembers() +" members into CSCMatch");
		}
	}
	
	/**
	 * requestMEID(boolean forceUnique)
	 * refactored collection of MEID since it happens in more than one place
	 * @param forceUnique
	 * @return
	 */
	private String requestMEID(boolean forceUnique, boolean forceExists)
	{
		String meid = "";
		
		while(meid.isEmpty())
		{
			System.out.print("Enter Student MEID [x: cancel]: ");
			meid = this.scanner.next();
			
			if(meid.toLowerCase().equals("x")) {
				System.out.println("Cancelling Operation.");
				return "x";
			}
			
			if(meid.length() != 10) {
				System.out.println("MEID Must be 10 characters");
				meid="";
			} else if(forceUnique) {
				if(membership.memberExists(meid)) {
					System.out.println("A member with that MEID already exists. Please try again.");
					meid = "";
				}
			} else if (forceExists) {
				if(!membership.memberExists(meid))
				{
					System.out.println("Member not found in Membership List");
					meid = "";
				}
			}
		}
		
		return meid;
	}
	
	private String requestMEID()
	{
		return requestMEID(false, false);
	}
	
	private String requestUniqueMEID()
	{
		return requestMEID(true, false);
	}
	
	private String requestExistingMEID()
	{
		return requestMEID(false, true);
	}
	
	private void removeMember()
	{
		String meid = requestExistingMEID();
		if(!meid.equals("x")) {
			membership.removeMember(meid);
		}
	}
	
	private void addNewMember()
	{
		boolean meidDone=false, firstDone=false, lastDone=false, yearDone=false, cancel=false;
		String meid = "";
		String firstName = "";
		String lastName = "";
		int year=0;
		System.out.println("Follow the prompts to create a new member.");
		
		meid = requestUniqueMEID();
		if(meid.equals("x")) {
			cancel = true;
			System.out.println("Cancelling Member Creation");
		}
		
		while(!firstDone && !cancel)
		{
			System.out.print("Enter Student's First Name [x: cancel]: ");
			firstName = this.scanner.next();
			if(firstName.toLowerCase().equals("x")) {
				System.out.println("Cancelling Member Creation");
				cancel=true;
			} else {
				firstDone = true;
			}
		}
		
		while(!lastDone && !cancel)
		{
			System.out.print("Enter Student's Last Name [x: cancel]: ");
			lastName = this.scanner.next();
			if(lastName.toLowerCase().equals("x")) {
				System.out.println("Cancelling Member Creation");
				cancel=true;
			} else {
				lastDone = true;
			}
		}
		
		while(!yearDone && !cancel)
		{
			try {
				System.out.print("Enter Student's year [99: cancel]: ");
				year = this.scanner.nextInt();
				if(year == 99) {
					System.out.println("Cancelling Member Creation");
					cancel=true;
				} else {
					yearDone = true;
				}
			} catch(Exception ex) {
				System.out.println("Invalid Year Entered, please try again.");
				this.scanner.next();
				year = 0;
			}
			
		}
		
		if(!cancel && !meid.isEmpty() && firstDone && lastDone) {
			membership.addMemberToList(new Member(firstName, lastName, meid, year));
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
		membership.saveMembers("members.ser");
	}
}
