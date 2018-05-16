package cs124midterm;

public class Context {
	   private Strategy strategy;

	   public void setStrategy(Strategy strategy){
	      this.strategy = strategy;
	   }

	   public void executeStrategy(String string) throws Exception{
	      strategy.read(string);
	   }
	}