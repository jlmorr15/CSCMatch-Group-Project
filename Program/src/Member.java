import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Member implements Serializable {
	private LinkedList<Interest> interests;
	private LinkedList<MemberMatch> matches;
	private String firstName, lastName, MEID;
	private int year;
	
	public Member(String first, String last, String id, int yr)
	{
		firstName = first;
		lastName = last;
		MEID = id;
		year = yr;
		interests = new LinkedList<Interest>();
		matches = new LinkedList<MemberMatch>();
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
		
		String output = "| LVL | Topic\n";
		
		for(Interest interest : interests)
		{
			output = output+interest.toString()+"\n";
		}
		System.out.println(output);
	}
	
	private LinkedList<Interest> getInterestList()
	{
		return this.interests;
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
	
	public String getMEID()
	{
		return MEID;
	}
	
	
	
	public void calculateMatches(LinkedList<Member> members)
	{
		for(Member mbr : members)
		{
			float score = 0;
			if(!mbr.getMEID().equals(this.getMEID())) {
				//Ok we aren't looking at ourselves, lets get it on!
				for(Interest interest : mbr.getInterestList())
				{
					int myInterestIndex = this.matchingInterestIndex(interest.getTopic());
					if(myInterestIndex>-1) {
						//DING DING DING, WE HAVE A MATCH
						score += this.interests.get(myInterestIndex).getLevel() * interest.getLevel();
					} else {
						score += interest.getLevel()/2;
					}
				}
				
				//score is complete now...
				MemberMatch match = new MemberMatch(mbr,score);
				addMatch(match);
			}
		}
		showTopMatches();
	}
	
	
	private void addMatch(MemberMatch match)
	{
		for(MemberMatch m : matches)
		{
			if(m.getMember().getMEID().equals(match.getMember().getMEID())) {
				//Not on my watch!
				matches.remove(m);
			}
		}
		matches.add(match);
	}
	
	private void showTopMatches()
	{
		System.out.println("\nMember's Top 5 Matches:");
		Collections.sort(matches,Collections.reverseOrder()); // Now with Comparable MemberMatch
		for(int i = 0; i<5; i++)
		{
			if(i<matches.size()) {
				MemberMatch match = matches.get(i);
				System.out.println(match.getScore() + ": " + match.getMember());
			}
		}
	}
}