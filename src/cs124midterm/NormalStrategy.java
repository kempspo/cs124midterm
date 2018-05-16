package cs124midterm;

public class NormalStrategy implements Strategy {

	public void read(String string, Commands c, Controller con) throws Exception {
		c.register(string);
	}

}
