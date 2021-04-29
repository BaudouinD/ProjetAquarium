package aquarium;

import java.util.*;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Aquarium extends JFrame implements Runnable {

  private List<Fish> fishies;
  private JPanel rootPanel;


  public Aquarium(Size screenSize){
    this.setTitle("Aquarium");
    this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());

    fishies = new ArrayList<Fish>();

    this.setLocationRelativeTo(null);

    rootPanel = new JPanel();
    rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
    rootPanel.setBackground(Color.BLUE);
    this.setLayout(null);
    this.setContentPane(rootPanel);

    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}

    public void run(){   
      System.out.println(Thread.currentThread().getName() + ", executing run() method!");

      while (true) {
        this.rootPanel.repaint();
      }
    }

    public void addFish(Fish fish){

      if(getFish(fish.getId()) == null){
        fishies.add(fish);
        rootPanel.add(fish);
        fish.setVisible(true);
        System.out.println("    --> OK");
      }else{
        System.out.println("Fish with id <" + fish.getId() + "> already exists.");
      }
    }

    public Fish getFish(String fishName){
      for (Fish fish : fishies) {
        if(fish.getId().equals(fishName)){
          return fish;
        }
      }
      return null;
    }

    public List<Fish> getFishes() {
      return fishies;
    }


  }

