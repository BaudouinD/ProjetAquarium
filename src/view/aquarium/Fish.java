package aquarium;

//import java.util.*;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Fish extends JPanel {
    
    private Size size; // Size of the fish
    private Point position; // Position on the fish 
    private String id; // ID of the fish
    private Image img; // Image of the fish
    
    private boolean moveFish = false; // True is the fish is moving
    // private boolean finishedMoving = false;

    public Fish (Size size, Point position, String id){
        this.size = size;
        this.position = position;
        this.id = id;
        this.setSize((int) size.getWidth(), (int) size.getHeight());
        this.setLocation((int) position.getX() , (int) position.getY());


        try {
          Image im = ImageIO.read(new File("/Users/baudouindouliery/Desktop/2A/S8/Projet_Reseaux/free-RE203-3R/src/view/Fish_Images/" + id + ".jpg"));
          img = im.getScaledInstance((int)size.getWidth() , (int)size.getHeight(),Image.SCALE_DEFAULT);

        }
        catch (IOException e) {
          try{
          Image em = ImageIO.read(new File("/Users/baudouindouliery/Desktop/2A/S8/Projet_Reseaux/free-RE203-3R/src/view/Fish_Images/" + id + ".jpg"));
          img = em.getScaledInstance((int)size.getWidth() , (int)size.getHeight(),Image.SCALE_DEFAULT);
          }
            catch(IOException e2){
              System.out.println(e2);
            }
    
    }
  }

    public void startFish(){
        moveFish = true;
      }

    public boolean isStarted(){
        return moveFish;
    } 

    public void setPosition(Point position){
        this.position=position;
      }
    
    public String getId(){
        return this.id;
      }

    public void paintComponent(Graphics g){
      g.drawImage(img, 0,0, null);
      this.setBounds((int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight());    
    }
}