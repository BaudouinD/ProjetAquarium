import java.awt.*;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;


public class Test extends JFrame{
    public static JButton button=new JButton("Button");//Button to move
    public static int x;
    public static int y;
    public Test(){
        final Container C = getContentPane();
        C.setLayout(null);
        button.setBounds(100,100,100,100);
        C.add(button);
        //MouseListenerStart
    button.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
    if(!e.isMetaDown()){
    x = e.getX();
    y = e.getY();
    }
    }
    });
    button.addMouseMotionListener(new MouseMotionAdapter() {
    public void mouseDragged(MouseEvent e) {
    if(!e.isMetaDown()){
    Point p = button.getLocation();
    button.setLocation(p.x + e.getX() - x, p.y + e.getY() - y);
    }
    }
    });
//MouseListenerend
    }
    public static void main(String[] args) {
        Test marco=new Test ();
        marco.setSize(1000,700);
        marco.setVisible(true);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
}
