package cs124midterm;

public class Main
{	
	public static Controller control;
	public static UIReading ui;
	public static void main(String[] args) throws Exception
	{    	
		Controller control = new Controller();
		ui = new UIReading(control);
		ui.setSize( 1280, 720 );
		ui.setVisible( true );
		ui.setTitle( "UI Reading" );
		ui.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				windowEvent.getWindow().dispose();
			}
		});
	}
}