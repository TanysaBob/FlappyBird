import java.awt.*;
import javax.swing.*;
public class Bird
{
    // instance variables - replace the example below with your own
    private int x; //top left x value
    private int y; //top left y value
    private int size; //diameter
    private int dx; //direction
    private int dy;
    private int centrex;
    private Color Col;
    private Image birdy;
    boolean lost = false; // if ball touches the ground 
    public Bird(int x2, int y2, int sz, int dx2, int dy2, Color c, Image b)
    {
        // initialise instance variables
        x = x2;
        y = y2;
        size = sz; 
        dx = dx2;
        dy = dy2;
        Col = c;
        birdy = b;
        centrex = x + (size/2);
    }
    
    //accessors
    public int getX(){return x;}

    public int getY(){return y;}

    public int getSize(){return size;}

    public int getDx(){return dx;}

    public int getDy(){return dy;}
    
    public boolean getLost(){return lost;}
    
    public int getCX(){return centrex;}

    //mutators
    public void setX(int p){x = p;}

    public void setY(int p){y = p;}

    public void setSize(int p){size = p;}

    public void setDx(int p){dx = p;}

    public void setDy(int p){dy = p;}
    
    public void setLost(boolean l){lost = l;} // thats an l not a one
    
    public void move(Graphics g)
    {
        x = x + dx;
        y += dy;
        
        if (y>900)
        {
            y = 100;
            x = 200;
            lost = true;//if ball hits floor
        }
        
        
        draw(g);
        
    }
    
    
    public void draw(Graphics g)
    {
       //g.setColor(Col);
       //g.fillOval(x, y, size, size);
       g.drawImage(birdy,x,y, null);
       
        
    }
}
