import java.io.Serializable;
import java.util.LinkedList;
import java.util.Vector;

public class Member implements Serializable {
	private LinkedList<Interest> interests;
	private String firstName, lastName, MEID;
	private int year;
	
	public Member(String first, String last, String id, int yr)
	{
		firstName = first;
		lastName = last;
		MEID = id;
		year = yr;
		interests = new LinkedList<Interest>();
	}
	
	public String toString()
	{
		return lastName+", "+firstName+": "+MEID;
	}
	
	public void addInterest(String topic, int level)
	{
		int intIndex = matchingInterestIndex(topic);
		if(intIndex!=-1) {
			Interest extInterest = interests.get(intIndex);
			extInterest.setLevel(level);
		} else {
			Interest newInterest = new Interest(topic,level);
			interests.add(newInterest);
		}
	}
	
	public void listInterests()
	{
		System.out.println("Interests:");
		int longestTopic = 0;
		
		String output = "| LVL | Topic\n";
		
		for(Interest interest : interests)
		{
			output = output+interest.toString()+"\n";
		}
		System.out.println(output);
	}
	
	
	/**
	 * hasInterest
	 * predicate method powered by matchingInterestIndex for code readability when index isn't needed
	 * @param i
	 * @return
	 */
	public Boolean hasInterest(Interest i)
	{
		int index = matchingInterestIndex(i.getTopic());
		return index > -1 ? true:false; 
	}
	
	/**
	 * matchingInterestIndex
	 * returns the index of an interest matching the topic
	 * returns -1 if no match was found.
	 * @param topic
	 * @return
	 */
	public int matchingInterestIndex(String topic)
	{
		int count = 0;
		for(Interest interest : interests)
		{
			if(interest.getTopic().toLowerCase().equals(topic.toLowerCase()))
				return count;
			count++;
		}
		return -1;
	}
}