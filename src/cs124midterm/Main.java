package cs124midterm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import anno.CheckEnter;
import anno.Direction;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import cs124midterm.*;

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
