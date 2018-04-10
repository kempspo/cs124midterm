package cs124midterm;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        UI lol = new UI();
        lol.setSize( 1280, 720 );
        lol.setVisible( true );
        lol.setTitle( "Mazes are cool" );
        lol.addWindowListener(new java.awt.event.WindowAdapter()
        {
          @Override
          public void windowClosing(java.awt.event.WindowEvent windowEvent)
          {
            lol.closer();
            windowEvent.getWindow().dispose();
          }
        });
    }
}
