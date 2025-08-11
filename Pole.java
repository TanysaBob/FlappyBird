
import java.awt.*;
import javax.swing.*;
public class Pole
{
    // instance variables - replace the example below with your own
    private int x; //top left x value
    private int y; //top left y value
    private int width; //diameter
    private int length;
    private int dx; //direction
    private int dy;
    private Color Col;
    
    public Pole(int x2, int y2, int wi, int le, int dx2, int dy2, Color c)
    {
        // initialise instance variables
        x = x2;
        y = y2;
        width = wi; 
        length = le;
        dx = dx2;
        dy = dy2;
        Col = c;
    }
    
    //accessors
    public int getX(){return x;}

    public int getY(){return y;}

    public int getWidth(){return width;}

    public int getLength(){return length;}
    
    public int getDx(){return dx;}

    public int getDy(){return dy;}

    //mutators
    public void setX(int p){x = p;}

    public void setY(int p){y = p;}

    public void setWidth(int p){width = p;}
    
    public void setLength(int p){length = p;}

    public void setDx(int p){dx = p;}

    public void setDy(int p){dy = p;}
    
    public void setColor(Color p){Col = p;}
    
    public void move(Graphics g)
    {
        x = x - dx;
        draw(g);
        
    }
    
    
    public void draw(Graphics g)
    {
       g.setColor(Col);
       g.fillRect(x, y, width, length);
        //g.drawImage(birdOne,x,y, null);
    }
}
