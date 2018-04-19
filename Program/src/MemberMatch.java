
public class MemberMatch {
	private Member member;
	private float score;
	
	public MemberMatch(Member member, float score)
	{
		this.member = member;
		this.score = score;
	}
	
	public float getScore()
	{
		return score;
	}
	
	public Member getMember()
	{
		return member;
	}
}
