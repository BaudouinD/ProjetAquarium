package aquarium;

//import java.util.Date;

public class Destination{
  private Point address;
  //private double time; Je sais pas à quoi sert le temps

  public Destination(Point address){
    //Date date = new Date(); Je sais pas à quoi sert la date non plus

    this.address = address;
    //this.time = time;
  }

  public Point getAddress(){
    return address;
  }
  /*
  public double getTime(){
    return time;
  }*/
  public void setAddress(Point address){
    this.address = address;
  }
  /*
  public void setTime(double time){
    this.time = time;
  }*/
}