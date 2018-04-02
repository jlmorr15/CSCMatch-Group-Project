
public class Interest {

	private String topic;
	private int level;
	
	public Interest(String topic, int level)
	{
		this.topic = topic;
		this.level = level;
	}
	
	public String toString()
	{
		return level+", "+topic;
	}
	
	public String getTopic()
	{
		return topic;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
}
