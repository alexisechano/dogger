/**
   * Noodle is a class that maintains information about its number of lives, x and y position 
   * and collisions. Noodle knows how to set and return its number of lives and x and y positions, 
   * move forward, backward, left, and right, and check whether a collision with an obstacle has occured.
   *
   *@authors   Alexis Echano, Sherrie Feng, and Sonika Vuyyuru
   */
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class Noodle 
{
   private int dx, dy, myX, myY, numLives, myWidth, myHeight; //Noodle arguments
   private Image image; //Image variable for Noodle sprite
   private String myPicture;  //String for file name of picture
   private Rectangle box;  //Rectangle object for bounding box
   
   /**
    * Creates Noodle Object at location (x, y), with width z and 
    * height a, and number of lives. It also creates a rectangle
    * for which it uses for crashes and object detection.
    * <p>
    * This method is always needed when creating a new Noodle Object 
    *
    * @param  x  Starting x-coordinate of Noodle.
    * @param  y  Starting y-coordinate of Noodle.
    * @param  z  Width of Noodle and bounding rectangle.
    * @param  a  Height of Noodle and bounding rectangle.
    * @param  lives  The number of lives Noodle has.
    */
    
   public Noodle(int x, int y, int z, int a, int lives)
   {
      myPicture = "/images/NoodleUp.png"; //Sets file name and path to Picture String
      draw();  //creates new Image Icon using file above
      myX = x; //sets x arg to x-coordinate
      myY = y; //sets y arg to y-coordinate
      myWidth = z;  //sets z arg to width
      myHeight = a;  //sets a arg to height
      numLives = lives; //sets lives arg to usable lives variable
      box = new Rectangle(myX, myY, myWidth, myHeight);  //creates rectangle that surrounds Noodle
   }
   
   /**
      *  To properly draw Noodle in DoggerPanel, the ImageIcon must be
      *  in a form which the BufferedImage can use. This method returns
      *  the Noodle sprite as a compatible Image object.
      * 
      *  @return image sprite of Noodle
      */
   public Image getImage()
   {
      return image;
   }
   /**
      *  To properly draw Noodle in DoggerPanel, the file name of the 
      *  desired picture has to be set.
      * 
      *  @param String of file name.
      */
   public void setPicture(String picture)
   {
      myPicture = picture;
   }
   /**
      *  Returns the amount of lives or tries left that Noodle has
      *  or the player has to achieve the highest score possible.
      *
      *  @return Number of lives Noodle has at moment.
      */
   public int getLives()
   {
      return numLives;
   }
   /**
      *  When wanting to change the value of lives, setting 
      *  the number of lives is done by this method.
      * 
      *  @param Integer value of Noodle lives.
      */
   public void setLives(int lives)
   {
      numLives = lives;
   }
   /**
      *  Returns x-coordinate of the upper left corner
      *  of the Noodle object
      *
      *  @return The x value of Noodle object's location.
      */
   public int getX()
   {
      return myX;
   }
    /**
      *  Returns y-coordinate of the upper left corner
      *  of the Noodle object
      *
      *  @return The y value of Noodle object's location.
      */
   public int getY()
   {
      return myY;
   }
   /**
      *  Returns the width of the Noodle object or rectangle.
      *
      *  @return The width value of Noodle object/rectangle.
      */
   public int getWidth()
   {
      return myWidth;
   }
   /**
      *  Returns the height of the Noodle object or rectangle.
      *
      *  @return The height value of Noodle object/rectangle.
      */
   public int getHeight()
   {
      return myHeight;
   }
   /**
      *  Returns the difference x value, the amount Noodle object
      *  moves, when key is pressed or action occurs.
      *
      *  @return The x difference value of Noodle object/rectangle.
      */
   public int getdx()
   {
      return dx;
   }
   /**
      *  Returns the difference y value, the amount Noodle object
      *  moves, when key is pressed or action occurs.
      *
      *  @return The y difference value of Noodle object/rectangle.
      */
   public int getdy()
   {
      return dy;
   }
    /**
      *  Returns the bounding rectangle around Noodle along with its
      *  characteristics such as width and height. 
      *
      *  @return The rectangle characteristics.
      */
   public Rectangle getBox()
   {
      return box;
   }
   /**
      *  Sets the argument x to the new x-coordinate of 
      *  Noodle's location.
      * 
      *  @param Integer value of new x-coordinate.
      */
   public void setX(int x)
   {
      myX = x;
   }
   /**
      *  Sets the argument y to the new y-coordinate of 
      *  Noodle's location.
      * 
      *  @param Integer value of new y-coordinate.
      */
   public void setY(int y)
   {
      myY = y;
   }
   /**
      *  Sets the file name to ImageIcon object and  
      *  gets image of Noodle sprite.
      * 
      *  @return No return value.
      */
   public void draw() 
   {
      ImageIcon noodleSprite = new ImageIcon(Noodle.class.getResource(myPicture));
      image = noodleSprite.getImage();
   }
   /**
      *  Use dx and dy to move upper left coordinate of Noodle object  
      *  and is limited to the size of the frame. Uses set and get methods
      *  to change x and y coordinates of sprite.
      *
      *  @return No return value.
      */
   public void move()
   {
      myX += dx;
      if (myX < 0)
         setX(getX() + 5);
      else if (myX > 600 - myWidth)
         setX(getX() - 5);
      
      myY += dy;
      if (myY < 0)
         setY(getY() + 5);
      else if (myY > 680 - myHeight)
         setY(getY() - 5);
      getBox().setLocation(myX, myY);
   }
   /**
      *  Moves Noodle to starting place in panel by setting
      *  x and y coordinates to pre-set values in frame.
      *
      *  @return No return value.
      */
   public void reset()
   {
      myX = 285;
      myY = 570;
   }
   /**
      *  Uses rectangle width, height, x, and y values of Obstacle and Noodle 
      *  to compare points. If overlapping occurs, a crash is detected.
      *
      *  @param Obstacle object
      *  @return Returns true or false depending if Obstacle and
      *  Noodle are touching.
      */
   public boolean checkCrash(Obstacle o)
   {
      int x = getBox().x;
      int y = getBox().y;
      int width = getBox().width;
      int height = getBox().height;
      Rectangle n = o.getBox();
      return (x < n.x + n.width && x + width > n.x && y < n.y + n.height && y + height > n.y);
   }
   /**
      *  Uses arrow keys to amend dy and dx values to allow
      *  Noodle sprite to move across panel.
      *
      *  @param Keyevent object.
      *  @return No return value.
      */
   public void keyPressed(KeyEvent e) 
   {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_LEFT) 
         dx = -5;
      else if (key == KeyEvent.VK_RIGHT) 
         dx = 5;
      else if (key == KeyEvent.VK_UP) 
         dy = -5;
      else if (key == KeyEvent.VK_DOWN)
         dy = 5;
   }  
   /**
      *  When a key is not pressed, dy and dx values don't change
      *  which keeps Noodle constant when no key is pressed.
      *
      *  @param Keyevent object.
      *  @return No return value.
      */
   public void keyReleased(KeyEvent e) 
   { 
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_LEFT)
         dx = 0;
      if (key == KeyEvent.VK_RIGHT)
         dx = 0;
      if (key == KeyEvent.VK_UP)
         dy = 0;
      if (key == KeyEvent.VK_DOWN)
         dy = 0;
   }
}