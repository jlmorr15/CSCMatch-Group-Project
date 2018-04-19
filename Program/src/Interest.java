import java.io.Serializable;

public class Interest implements Serializable, Comparable<Interest>{

	private String topic;
	private int level;
	
	public Interest(String topic, int level)
	{
		this.topic = topic;
		this.level = level;
	}
	
	public String toString()
	{
		return "| "+String.format("%03d", level)+" | "+topic;
	}
	
	public String getTopic()
	{
		return topic;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	@Override
	public int compareTo(Interest interest)
	{
		if(getTopic().toLowerCase().equals(interest.getTopic().toLowerCase()))
			return 0;
		return -1;
	}
}
