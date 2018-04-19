
public class MemberMatch implements Comparable<MemberMatch> {
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

	@Override
	public int compareTo(MemberMatch match)
	{
		if(getScore() == match.getScore())
			return 0;
		return getScore() > match.getScore() ? 1:-1;
	}
}
