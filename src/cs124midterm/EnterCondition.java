package cs124midterm;
import anno.Message;

public interface EnterCondition {
	public boolean canEnter();
	
	@Message
	public String enterMessage();
	
	@Message
	public String unableToEnterMessage();
}
