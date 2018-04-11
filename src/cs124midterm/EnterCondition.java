package cs124midterm;
import anno.Message;

public interface EnterCondition {
	public boolean canEnter(Player player);
	
	@Message
	public String enterMessage();
	
	@Message
	public String unableToEnterMessage();
}
