/**
   * Obstacle is a class that maintains information about its x and y position, size, and whether a
   * a collision has occured with Noodle. Obstacle knows how to return its x and y positions, set its 
   * x position, move left and right, and draw itself.
   *  @authors   Alexis Echano, Sherrie Feng, and Sonika Vuyyuru
   **/

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.*;


public class Obstacle
{
   private int myY, myX, mydx, myWidth, myHeight; //arguments
   private Image image; //Image variable for Noodle sprite
   private String myPicture;  //String for file name of picture
   private Rectangle box;  //Rectangle object for bounding box
   
   /**
    * Creates Obstacle Object at location (x, y), with width z and 
    * height a, int dx for movement, and string name of image icon. 
    * It also creates a rectangle around object for crash boolean.
    * 
    * <p>
    * This method is always needed when creating a new Obstacle Object 
    *
    * @param  x  Starting x-coordinate of Noodle.
    * @param  y  Starting y-coordinate of Noodle.
    * @param  z  Width of Obstacle and bounding rectangle.
    * @param  a  Height of Obstacle and bounding rectangle.
    * @param  picture  The name of picture file.
    */
   public Obstacle(int x, int y, int z, int a, int dx, String picture)
   {
      draw(picture); //creates new Image Icon using picture name
      myX = x; //sets x arg to x-coordinate
      myY = y; //sets y arg to y-coordinate
      myWidth = z;  //sets z arg to width
      myHeight = a;  //sets a arg to height
      mydx = dx; //sets dx arg to variable
      picture = myPicture; //sets string arg to file name variable
      box = new Rectangle(myX, myY, myWidth, myHeight);  //creates rectangle that surrounds Noodle
   }
   /**
      *  To properly draw Obstacles in DoggerPanel, the ImageIcon must be
      *  in a form which the Graphics object can use. This method returns
      *  a file name which the paint component can use.
      * 
      *  @return image name of Obstacle
      */
   public String getPicture()
   {
      return myPicture;
   }
   /**
      *  Returns x-coordinate of the upper left corner
      *  of the object
      *
      *  @return The x value of Obstacle object's location.
      */
   public int getX()
   {
      return myX;
   }
    /**
      *  Returns y-coordinate of the upper left corner
      *  of the object
      *
      *  @return The y value of Obstacle object's location.
      */
   public int getY()
   {
      return myY;
   }
  /**
      *  Returns the width of the Obstacle object or rectangle.
      *
      *  @return The width value of Obstacle object/rectangle.
      */
   public int getWidth()
   {
      return myWidth;
   }
  /**
      *  Returns the height of the Obstacle object or rectangle.
      *
      *  @return The height value of Obstacle object/rectangle.
      */
   public int getHeight()
   {
      return myHeight;
   }
  /**
      *  Returns the difference x value, the amount Obstacle object
      *  moves, action timer repaints.
      *
      *  @return The x difference value of Obstacle object/rectangle.
      */
   public int getdx()
   {
      return mydx;
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
      *  Obstacle's location.
      * 
      *  @param Integer value of new x-coordinate.
      */
   public void setX(int x)
   {
      myX = x;
   }
   /**
      *  Sets the argument y to the new y-coordinate of 
      *  Obstacle's location.
      * 
      *  @param Integer value of new y-coordinate.
      */
   public void setY(int y)
   {
      myY = y;
   }
   /**
      *  Use dx Obstacle object across the panel screen 
      *  and is limited to the size of the frame and extra on left side
      *  for continuous looking movements. Uses set and get methods
      *  to change x value of sprites.
      *
      *  @return No return value.
      */
   public void move()
   {
      myX += mydx;
      if (mydx > 0)
      {
         if(myX >= 600)
            myX = -200;
      }
      else if (mydx < 0)
      {
         if(myX <= -200)
            myX = 600;
      }
      getBox().setLocation(myX, myY);
   }
   
  /**
      *  Sets the file name to ImageIcon object and  
      *  gets image of Obstacle sprite to be used in panel.
      * 
      *  @param Picture name
      *  @return No return value.
      */
   public void draw(String pictureName) 
   {
      ImageIcon sprite = new ImageIcon(Obstacle.class.getResource(pictureName));
      image = sprite.getImage();
   }
   /**
      *  To properly draw Obstacles in DoggerPanel, the ImageIcon must be
      *  in a form which the BufferedImage can use. This method returns
      *  the object sprite as a compatible Image object.
      * 
      *  @return image sprite of Obstacle.
      */
   public Image getImage()
   {
      return image;
   }
}