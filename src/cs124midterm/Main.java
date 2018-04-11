package cs124midterm;

public class Main
{

	public static UI ui;
	
    public static void main(String[] args) throws Exception
    {    	
        ui = new UI();
        ui.setSize( 1280, 720 );
        ui.setVisible( true );
        ui.setTitle( "Mazes are cool" );
        ui.addWindowListener(new java.awt.event.WindowAdapter()
        {
          @Override
          public void windowClosing(java.awt.event.WindowEvent windowEvent)
          {
            ui.closer();
            windowEvent.getWindow().dispose();
          }
        });
        
    	ui.load();
    }
}
