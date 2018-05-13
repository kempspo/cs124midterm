package cs124midterm;

public class Main
{	
	public static UIReading ui;
	public static void main(String[] args) throws Exception
	{    	
		Context context = new Context(new FileStrategy());
		ui = new UIReading();
		ui.setSize( 1280, 720 );
		ui.setVisible( true );
		ui.setTitle( "Mazes are cool" );
		ui.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				windowEvent.getWindow().dispose();
			}
		});

		ui.load();		
	}


}