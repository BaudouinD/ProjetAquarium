import aquarium.Aquarium;
import aquarium.Size;
import aquarium.Fish;
import aquarium.Point;


public class Main {
    public static void main(String[] args) {
        Size fish1Size = new Size(100, 100);
        Point fish1Position = new Point(400, 400);
        Size fish2Size = new Size(100, 100);
        Point fish2Position = new Point(200, 200);
        Fish Espadon = new Fish(fish1Size, fish1Position, "Espadon");
        Fish Thon = new Fish(fish2Size, fish2Position, "Thon");
        Size screen = new Size(1500, 925);
        Aquarium aquarium = new Aquarium(screen);
        aquarium.addFish(Espadon);
        aquarium.addFish(Thon);
        aquarium.run();
      }  
}
