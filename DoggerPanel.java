/**
 * The DoggerPanel class controls the movement of obstacles and Noodle
 * and their displays. DoggerPanel uses the presence of collisions between 
 * Noodle and Obstacles to adjust the display of Lives, and is able to show
 * an end screen corresponding to the player winning or losing.
 */
 
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

public class DoggerPanel extends JPanel
{
   private Image image;
   private Noodle mainNoodle; //the main Noodle object used for game
   private Timer t;  //timer for actionListener
   private ArrayList<Obstacle> lane1 = new ArrayList<Obstacle>(); //array that holds cars in lane 1
   private ArrayList<Obstacle> lane2 = new ArrayList<Obstacle>(); //array that holds cars in lane 2
   private ArrayList<Obstacle> lane3 = new ArrayList<Obstacle>(); //array that holds cars in lane 3
   private ArrayList<Obstacle> water1 = new ArrayList<Obstacle>(); //array that holds logs in water 1
   private ArrayList<Obstacle> water2 = new ArrayList<Obstacle>(); //array that holds turtles in water 2
   private ArrayList<Obstacle> PIZZA = new ArrayList<Obstacle>(); //array that holds pizzas in final lane
   private boolean alreadyExecuted = false, alreadyExecutedP = false; //
   private int pizzasEaten = 0; //counts goals scored
   
   private JButton restartButton;   //button for restart
   private boolean restartClicked = false; //checks if the above button is clicked 

   
    /**
    * Instantiates Noodle and all Obstacles, and sets background image. It also creates
    * an keyListener to add keyboard input, and creates a timer to enable key events.
    *
    * @return No return value.
    */
    
   public DoggerPanel()
   {
      addKeyListener(new moverNoodle()); //adds keyboard input
      setFocusable(true); //allows for motion
      mainNoodle = new Noodle(285, 570, 30, 50, 5); //creates new Noodle
      lanes(); //initializes lanes
      
      ImageIcon background = new ImageIcon(DoggerPanel.class.getResource("/images/background-2.jpg")); //creates background image
      image = background.getImage(); //sets background image
      
      t = new Timer(10, new Listener()); //enables the key events
      t.start();  //begins timer
      
      setLayout(new BorderLayout());   //border layout for this panel
      JPanel subpanel = new JPanel();  //subpanel for formatting title and buttons
      subpanel.setLayout(new FlowLayout());
      add(subpanel,BorderLayout.EAST);

      restartButton = new JButton("Restart");   //next button added to subpanel
      restartButton.addActionListener(new Listener());
      subpanel.add(restartButton);
      restartButton.setEnabled(true);
      restartButton.setVisible(true);
      
   }
   
   /**
    * Creates all obstacles in each array, with corresponding images, dimensions, 
    * x positions, and y positions. Each array contains three obstacles.
    *
    * @return No return value.
    */
    
    
   public void lanes()
   {
      for(int i = 0; i < 3; i++) //limits arrays to three obstacles
      {
         Obstacle o1 = new Obstacle(275 * i, 493, 100, 50, 2, "/images/purplecar.png"); //instantiates lane1
         lane1.add(o1); //adds obstacle with given dimensions to array
         Obstacle o2 = new Obstacle(275 * i, 411, 100, 50, -2, "/images/orangecar.png"); //instantiates lane2
         lane2.add(o2);
         Obstacle o3 = new Obstacle(275 * i, 330, 100, 50, 2, "/images/redcar.png"); //instantiates lane3
         lane3.add(o3);
         Obstacle o4 = new Obstacle(275 * i, 170, 200, 75, 2, "/images/logg.png"); //instantiates water1
         water1.add(o4);
         Obstacle o5 = new Obstacle(275 * i, 90, 85, 75, -2, "/images/turtle.png"); //instantiates water2
         water2.add(o5);
         Obstacle o6 = new Obstacle(50 + 200 * i, 2, 75, 75, 0, "/images/pizza.png"); //instantiates final lane
         PIZZA.add(o6);
      }
   }

   /**
    * Draws Noodle and all Obstacles. Updates and displays lives counter.
    * Uses lives counter to display either winning endscreen or losing endscreen. 
    *
    * @return No return value.
    */
   @Override
   public void paint(Graphics g) //main paint component that draws images from object classes
   {
      super.paintComponent(g);   //necessary for painting and helps clear the panel after repaint
      Graphics2D g1 = (Graphics2D)g;   //instantiates Graphics2D object
      //-----------------------------------------------------------------------------
      g.drawImage(image, 0, 0, 600, 640, null); //draws frame  
      g.setColor(Color.BLACK); //sets color to black
      g.setFont(new Font("Courier New", Font.CENTER_BASELINE, 24)); //sets font
   
      String lives = ("Lives: "+ (mainNoodle.getLives())); //creates toString version of lives
      if(mainNoodle.getLives() > 0) //orientation when player has lives left
         g.drawString(lives, 10, getHeight() - 15);
      if (mainNoodle.getLives() == 0) //orientation when player has no lives
      {
         g.drawString("Lives: 0", 10, getHeight() - 15);
         g.drawString("GAME OVER!", 200, getHeight() - 15);
      }
      else if (pizzasEaten == 3) //orientation when player wins game
      {
         g.drawString("YOU WIN!", 200, getHeight() - 15);
      }
      //-----------------------------------------------------------------------------
      for (int i = 0; i < lane1.size(); i++) //All lanes have the same amount of cars, draws all obstacles in arrays
      {
         g1.drawImage(lane1.get(i).getImage(), lane1.get(i).getX(), lane1.get(i).getY(), lane1.get(i).getWidth(), lane1.get(i).getHeight(), null); 
         g1.drawImage(lane2.get(i).getImage(), lane2.get(i).getX(), lane2.get(i).getY(), lane2.get(i).getWidth(), lane2.get(i).getHeight(), null); 
         g1.drawImage(lane3.get(i).getImage(), lane3.get(i).getX(), lane3.get(i).getY(), lane3.get(i).getWidth(), lane3.get(i).getHeight(), null);    
         g1.drawImage(water1.get(i).getImage(), water1.get(i).getX(), water1.get(i).getY(), water1.get(i).getWidth(), water1.get(i).getHeight(), null);   
         g1.drawImage(water2.get(i).getImage(), water2.get(i).getX(), water2.get(i).getY(), water2.get(i).getWidth(), water2.get(i).getHeight(), null); 
      } 
      for (int e = 0; e < PIZZA.size(); e++) //draws pizzas in final lane, adjusts to array size
         g1.drawImage(PIZZA.get(e).getImage(), PIZZA.get(e).getX(), PIZZA.get(e).getY(), PIZZA.get(e).getWidth(), PIZZA.get(e).getHeight(), null); 
      g1.drawImage(mainNoodle.getImage(), mainNoodle.getX(), mainNoodle.getY(), mainNoodle.getWidth(), mainNoodle.getHeight(), null); //draws Noodle
   }
   
    /**
    * Listener class uses timer to allow Noodle and Obstacles to move accross the screen.
    */
   
   public void playGame()
   {
         moveEverything(); //enables Noodle to move and Obstacles to autonomously move across
         repaint();  //repaints the images every tick  
         alreadyExecuted = false; //allows for possibility of losing lives
         alreadyExecutedP = false; //allows for possibility of losing lives
         if(mainNoodle.getLives() == 0 || pizzasEaten == 3) //checks if player has won or lost game
            t.stop(); //stops timer
   
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) //method executed every timer tick
      {
         playGame(); //plays game?
      }
      
   }
  private class restart implements ActionListener   //restart game
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            
            removeAll();
            revalidate();
            mainNoodle.setLives(5);
            pizzasEaten = 0;
            t.start(); //starts timer again
            playGame(); //specfically for button

         
         }
      }
   
    /**
    * Contains restrictions for move functions of Obstacles and Noodle. Uses checkCrash method 
    * to detect whether or not Noodle has collided with an obstacle. In car lanes, Noodle looses
    * a life if a crash has occured, whereas in water lanes, Noodle must collide with an obstacle
    * in order to retain lives. If Noodle collides with a pizza, the pizza is removed from the 
    * final lane array.
    *
    * @return No return value.
    */
    
   public void moveEverything()
   {
      mainNoodle.move();   //moves Noodle according to keyEvent in Noodle class
      for (int i = 0; i < lane1.size(); i++) //moves Obstacles within loop
      {
         lane1.get(i).move();
         lane2.get(i).move();
         lane3.get(i).move();
         water1.get(i).move();
         water2.get(i).move();
      }
      for (int q = 0; q < lane1.size(); q++) //checks whether or not a crash has occured for each car in the array
      {
         if(mainNoodle.checkCrash(lane1.get(q)) || mainNoodle.checkCrash(lane2.get(q)) || mainNoodle.checkCrash(lane3.get(q))) //checks crash for indiviual car
         {
            mainNoodle.reset(); //if a crash has occured, Noodle returns to starting position
            if(!alreadyExecuted) //checks if loss has occured within time step
            {
               mainNoodle.setLives(mainNoodle.getLives()-1); //decreases life count
               alreadyExecuted = true; //sets to true
            }
         }
      }   
      for (int b = 0; b < water1.size(); b++) //checks through each obstacle in water1 array
      {
         if(mainNoodle.checkCrash(water1.get(b))) //checks crash for individual obstacle
         {
            mainNoodle.setX(mainNoodle.getX() + water1.get(b).getdx() + mainNoodle.getdx()); //moves Noodle with obstacle
         }
      }
      for (int b = 0; b < water2.size(); b++) //checks through each obstacle in water2 array
      {
         if(mainNoodle.checkCrash(water2.get(b))) //checks crash for individual obstacle
         {
            mainNoodle.setX(mainNoodle.getX() + water2.get(b).getdx() + mainNoodle.getdx()); //moves Noodle with obstacle
         }
      }
      
      if(75 < mainNoodle.getY() && mainNoodle.getY() < 230) //checks if Noodle is in "river" boundary
      {
         if(!(mainNoodle.checkCrash(water1.get(0))) && !(mainNoodle.checkCrash(water1.get(1))) && !(mainNoodle.checkCrash(water1.get(2))) && !(mainNoodle.checkCrash(water2.get(0))) && !(mainNoodle.checkCrash(water2.get(1))) && !(mainNoodle.checkCrash(water2.get(2)))) //checks if Noodle is not on /has not crashed with any obstacles in river
         {
            mainNoodle.reset(); //if Noodle has fallen into water, Noodle returns to starting position
            if(!alreadyExecuted) //checks if loss has occured within time step
            {
               mainNoodle.setLives(mainNoodle.getLives()-1); //decreases life count
               alreadyExecuted = true; //sets to true
            }
         }
      }
               
      for (int p = 0; p < PIZZA.size(); p++) //checks through each pizza in final lane array
      {
         if (mainNoodle.checkCrash(PIZZA.get(p))) //checks if Noodle has "eaten" pizza
         {
            PIZZA.remove(p); //if pizza was eaten, it is removed from the array
            if(!alreadyExecutedP) //checks if loss has occured within time step
            {
               pizzasEaten++; //increases "goals scored" in game
               alreadyExecutedP = true; //sets to true
            }
            mainNoodle.reset(); //if Noodle has eaten pizza, Noodle returns to starting position
         }
         else if (mainNoodle.getY() < 75 && !mainNoodle.checkCrash(PIZZA.get(p)))
         {
            mainNoodle.reset();
            if(!alreadyExecuted) //checks if loss has occured within time step
            {
               mainNoodle.setLives(mainNoodle.getLives()-1); //decreases life count
               alreadyExecuted = true; //sets to true
            }
         }
      }
   }
   
    /**
    * moverNoodle class connects keys with key methods in Noodle class.
    */
    
   private class moverNoodle extends KeyAdapter //key event for arrow keys to move Noodle
   {
      public void keyPressed(KeyEvent e)  //changes X and Y values according to key pressed
      {
         mainNoodle.keyPressed(e);
      }
      public void keyReleased(KeyEvent e) //when a key is not pressed, Noodle stays in the same place
      {  
         mainNoodle.keyReleased(e);
      }
   } 

}
