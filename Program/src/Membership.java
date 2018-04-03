import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

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
	
	public void loadMembers()
	{
		try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(saveFile));
            Object mbrList = oi.readObject();
            members = (LinkedList<Member>)mbrList;
            oi.close();
        } catch (Exception ex){
        	System.out.println("Exception: "+ex.getMessage());
        }
	}
	
	public void saveMembers()
	{
		try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(saveFile));
            oo.writeObject(members);
            oo.close();
        } catch (IOException ex){
        	System.out.println("Exception: "+ex.getMessage());
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
	
	public void removeMember(Member mbr)
	{
		members.remove(mbr);
	}
	
	public void listMember(int c)
	{
		
	}
	
	public void listMember(String meid)
	{
		
	}
}
