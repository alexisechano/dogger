/**
   * DoggerDriver is a class that controls the display on the JFrame. It sets up the user
   * interface that is shown to players. It also adds the main game panel on to the frame.
   *
   *  @authors   Alexis Echano, Sherrie Feng, and Sonika Vuyyuru
   *
   **/

import javax.swing.*;
import java.awt.*;

public class DoggerDriver 
{
 /**
   * Sets up JFrame for DoggerPanel and frame characteristics such as size and location.
   *
   * @param arg A string array containing the command line arguments.
   * @return No return value.
   */ 
   public static void main(String[] args) 
      {
      DoggerPanel display = new DoggerPanel();  //Creates new DoggerPanel for gameplay
      JFrame frame = new JFrame("Dogger the Game"); //creates new JFrame with Dogger the Game as the title
      frame.setContentPane(display);   //Displays the game panel
      frame.setSize(600,650); //set size of window
      frame.setLocation(100, 100); //sets the location of the window
      display.requestFocus(); //line for setting up listeners
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits when red x is pressed in corner
      frame.setVisible(true); //the window is visible and not hidden
   }
}
