import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Membership {
	private LinkedList<Member> members;
	private String saveFile;
	
	public Membership()
	{
		saveFile = "members.ser";
		members = new LinkedList<Member>();
	}
	
	public void addMember()
	{
		
	}
	
	public void addMemberToList(Member mbr)
	{
		members.add(mbr);
	}
	
	public boolean loadMembers(String fileName)
	{
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fileName));
            Object mbrList = oi.readObject();
            members = (LinkedList<Member>)mbrList;
            oi.close();
            return true;
        } catch (Exception ex){
        	System.out.println("Error Loading Membership File. Please make sure it exists and is a valid membership file.");
        	System.out.println(ex.getMessage());
        	return false;
        }	
	}
	
	public boolean saveMembers(String fileName)
	{
		try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(fileName));
            oo.writeObject(members);
            oo.close();
            return true;
        } catch (IOException ex){
        	System.out.println("Error Saving Membership File. Please make sure a valid file path was provided");
        	System.out.println(ex.getMessage());
        	return false;
        }
	}
	
	public void listMembers()
	{
		for(Member mbr : members)
		{
			System.out.println(mbr);
			mbr.listInterests();
		}
	}
	
	public boolean memberExists(String meid)
	{
		for(Member mbr : members)
		{
			if(mbr.getMEID().toLowerCase().equals(meid))
			{
				return true;
			}
		}
		return false;
	}
	
	public void removeMember(String meid)
	{
		boolean mbrFound = false;
		//Using iterator should fix the issues encountered in testing.
		try {
			Iterator<Member> itr = members.iterator();
			while(itr.hasNext()) {
				Member mbr = itr.next();
				if(mbr.getMEID().toLowerCase().equals(meid.toLowerCase())) {
					System.out.println("\nRemoved member from Membership List");
					members.remove(mbr);
					mbrFound = true;
				}
			}
		} catch(ConcurrentModificationException ex) {
			System.out.println("Unable to remove member.");
			System.out.println(ex.getMessage());
		}
		
		
		/*for(Member mbr : members) {
			if(mbr.getMEID().toLowerCase().equals(meid.toLowerCase())) {
				System.out.println("\nRemoved member from Membership List");
				members.remove(mbr);
				mbrFound = true;
			}
		}*/
		
		if(!mbrFound)
			System.out.println("\nNo such member in Membership List");
	}
	
	public void listMember(int c)
	{
		
	}
	
	public void listMember(String meid)
	{
		boolean mbrFound = false;
		for(Member mbr : members) {
			if(mbr.getMEID().toLowerCase().equals(meid.toLowerCase())) {
				System.out.println(mbr);
				mbr.listInterests();
				mbr.calculateMatches(members);
				mbrFound = true;
			}
		}
		if(!mbrFound)
			System.out.println("Member Not Found In List.");
	}
	
	public Member getMemberByMEID(String meid) throws NoSuchElementException
	{
		for(Member mbr : members) {
			if(mbr.getMEID().toLowerCase().equals(meid.toLowerCase())) {
				return mbr;
			}
		}
		throw new NoSuchElementException("Member Not Found In List");
	}
	
	public int numMembers()
	{
		return members.size();
	}
}
