import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;
import javax.imageio.*; //loads in images
//import sun.audio.*; //loads in music

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyBird extends JPanel implements KeyListener, ActionListener, MouseListener
{

    //instance vars
    private Bird bird1;
    private Pole poletop1;
    private Pole polebottom1;
    private Pole poletop2;
    private Pole polebottom2;
    private mouse Mousie;
    private mouse Mousie2;
    private mouse Mousie3;
    private fish fish1;
    private fish fish2;
    private fish fish3;
    
    
    private Timer timer;
    private boolean play;
    
    int screen = 1;
    int p;
    int highlightE = 0;
    int score = 0;
    boolean jump = false;
    
    private Image ts; //title screen or screen 1
    private Image ls; //screen 4
    private Image ls2; //screen 7
    private Image ls3;
    private Image ws; //screen 5
    private Image ws2; //screen 8
    private Image ws3;
    
    private Image levels; //level select
    private Image LE;
    private Image LM;
    private Image LH;
    
    private Image bgL1; // background for level one
    private Image bgL2; // background for level two
    private Image bgL3; //background for level three
    
    private Image birdy;
    private Image mouse1;
    private Image fishy;
    private Color col;
    private Color col2;

    //constructor
    public FlappyBird()
    {

        int p = (int)(Math.random() * (2 + 1) + 1);
        if (p == 1)
            col = Color.pink;
        else if (p == 3)
            col = Color.yellow;
        else if (p == 2)
            col = Color.green;

        int r = (int)(Math.random() * (2 + 1) + 1);
        if (r == 2)
            col2 = Color.white;
        else if (r == 3)
            col2 = Color.red;
        else if (r == 1)
            col2 = Color.blue;

        //pole 1
        int d = (int)(Math.random() * (300 + 1) + 100); //length of top pole
        poletop1 = new Pole(900, 0, 200, d, 6, 0, col);
        int u = d+400; //top y coordinate of bottom pole
        polebottom1 = new Pole(900, u, 200, 1000, 6, 0, col);

        //pole 2
        int t = (int)(Math.random() * (300 + 1) + 100); //length of top pole
        poletop2 = new Pole(1450, 0, 200, t, 6, 0, col2);
        int f = t+400; //top y coordinate of bottom pole
        polebottom2 = new Pole(1450, f, 200, 1000, 6, 0, col2);
        try
        {
            ts = ImageIO.read(new File ("Flappy Bird TS.jpg"));
            ls = ImageIO.read(new File ("Flappy Bird LS.jpg"));
            ls2 = ImageIO.read(new File ("bg med lose.jpg"));
            ls3 = ImageIO.read(new File ("FB bg hard lose.jpg"));
            
            ws = ImageIO.read(new File ("Flappy Bird WS.jpg"));
            ws2 = ImageIO.read(new File ("FB WS med.jpg"));
            ws3= ImageIO.read(new File ("FB bg hard win.jpg"));
            
            levels = ImageIO.read(new File ("Flappy Bird LevelS.jpg"));
            LE = ImageIO.read(new File ("Leveleasy.jpg"));
            LM = ImageIO.read(new File ("LevelMedium.jpg"));
            LH = ImageIO.read(new File ("LevelHard.jpg"));
            
            bgL1 = ImageIO.read(new File ("fb bg L1.jpg"));
            bgL2 = ImageIO.read(new File ("Flappy Bird bg med.jpg"));
            bgL3 = ImageIO.read(new File ("FB bg hard.jpg"));
            
            birdy = ImageIO.read(new File ("birdy.png"));
            mouse1 = ImageIO.read(new File ("mouseimage.png"));
            fishy = ImageIO.read(new File ("fishy.png"));
        }
        catch (IOException e)
        {
        }

        bird1 = new Bird(400,400, 50, 0, 6, Color.blue, birdy);
        
        int s = (int)(Math.random() * (1000 + 1) + 300);
        Mousie = new mouse(2205, s, 83, 7, 0, Color.blue, mouse1);
        Mousie2 = new mouse(1700, s-300, 83, 4, 0, Color.blue, mouse1);
        Mousie3 = new mouse(2000, s+300, 83, 6, 0, Color.blue, mouse1);
        
        fish1 = new fish(2205, s, 83, 7, 0, Color.blue, fishy);
        fish2 = new fish(1700, s-300, 83, 4, 0, Color.blue, fishy);
        fish3 = new fish(2000, s+300, 83, 6, 0, Color.blue, fishy);
        
        addMouseListener(this);
        addKeyListener(this);
        timer = new Timer(1, this);
        timer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        play = false;
    }

    public void paint(Graphics g)
    {

        if (screen == 1) //title screen
        {
            startScreen(g); //draws screen one
        }
        else if (screen == 2)
        {
            LevelScreen(g);// draws level screen

            if (highlightE == 1)
                lightE(g);
            else if (highlightE == 2)
                lightM(g);
            else if (highlightE == 3)
                lightH(g);

        }
        else if (screen == 3 ) //level one
        {
            Levelone(g);
        }
        else if (screen == 4) //lose screen level one
        {
            LoseScreen(g);
        }
        else if (screen == 5) //win screen level one
            WinScreen(g);
        else if (screen == 6) //level two
            Leveltwo(g);
        else if (screen == 7) //lose screen lvl 2
            LoseScreen2(g);
        else if (screen == 8) //win screen level two
            WinScreen2(g);
        else if (screen == 9) //level three
            Levelthree(g);
        else if (screen == 10) //lose screen lvl 2
            LoseScreen3(g);
        else if (screen == 11) //win screen level two
            WinScreen3(g);
    }

    public void startScreen(Graphics g) //title screen
    {
        g.drawImage(ts,0,0,900,900, this);
    }

    public void lightE(Graphics g) //title screen
    {
        g.drawImage(LE,132,388 ,null);
    }

    public void lightM(Graphics g) //title screen
    {
        g.drawImage(LM, 352,388 ,null);
    }

    public void lightH(Graphics g) //title screen
    {
        g.drawImage(LH,570,388 ,null);
    }

    public void LevelScreen(Graphics g) //title screen
    {
        g.drawImage(levels,0,0,900,900,this);
    }

    public void Levelone(Graphics g)
    {
        //draw bird
        g.setColor(Color.white);
        g.fillOval(0, 0, 900, 900);
        g.drawImage(bgL1,0,0, 900,900,null);
        bird1.move(g);
        //draw background

        poletop1.move(g);
        polebottom1.move(g);
        poletop2.move(g);
        polebottom2.move(g);
        
        poletop1.setDx(3);
        polebottom1.setDx(3);
        
        poletop2.setDx(3);
        polebottom2.setDx(3);
        if (bird1.getY() < p - 150)
            bird1.setDy(7);

        if ((poletop1.getX()+100) < 0) //resets first pole
        {
            score += 5;
            poletop1.setX(900);//reset x value of top pole
            polebottom1.setX(900); //reset x value of bottom pole
            int l = (int)(Math.random() * (300 + 1) + 100);
            poletop1.setLength(l);
            polebottom1.setY(l+400);

            int p = (int)(Math.random() * (2 + 1) + 1);
            if (p == 1)
                col = Color.pink;
            else if (p == 3)
                col = Color.yellow;
            else if (p == 2)
                col = Color.green;

            poletop1.setColor(col);
            polebottom1.setColor(col);
        }
        if ((poletop2.getX()+100) < 0) //resets second pole
        {
            score+= 5;;
            poletop2.setX(900);//reset x value of top pole
            polebottom2.setX(900); //reset x value of bottom pole // set to 900 so no overlap
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop2.setLength(i);
            polebottom2.setY(i+400);

            int r = (int)(Math.random() * (2 + 1) + 1);
            if (r == 2)
                col2 = Color.white;
            else if (r == 3)
                col2 = Color.red;
            else if (r == 1)
                col2 = Color.blue;

            poletop2.setColor(col2);
            polebottom2.setColor(col2);
        }

        //adds one to score when poles pass a certain point

        g.setColor(Color.black);
        String scoretext = "Score: " +score;
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,150,100);
    }

    public void LoseScreen(Graphics g) //title screen
    {
        g.drawImage(ls,0,0, 900, 900,null);
        String scoretext = "Your score: " +score+"!";
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,200,300);


    }

    public void WinScreen(Graphics g) //title screen
    {
        g.drawImage(ws,0,0,900,900,null);
        String scoretext = "Your score: " +score+"!!";
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,200,300);

    }
    
    public void Leveltwo(Graphics g)
    {
        //draw bird
        g.setColor(Color.white);
        //g.fillOval(0, 0, 900, 900);
        g.drawImage(bgL2,0,0, 900,900,null);
        bird1.move(g);
        Mousie.move(g);
        Mousie2.move(g);
        Mousie3.move(g);
        //draw background

        poletop1.move(g);
        polebottom1.move(g);
        poletop2.move(g);
        polebottom2.move(g);
        
        poletop1.setDx(6);
        polebottom1.setDx(6);
        
        poletop2.setDx(6);
        polebottom2.setDx(6);
        
        poletop1.setColor(Color.blue);
        polebottom1.setColor(Color.blue);
        
        poletop2.setColor(Color.black);
        polebottom2.setColor(Color.black);
        
        
        if (bird1.getY() < p - 200)
            bird1.setDy(7);

        if ((poletop1.getX()+100) < 0) //resets first pole
        {
            score += 5;
            poletop1.setX(900);//reset x value of top pole
            polebottom1.setX(900); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop1.setLength(i);
            polebottom1.setY(i+400);

        }
        if ((poletop2.getX()+100) < 0) //resets second pole
        {
            score+= 5;;
            poletop2.setX(900);//reset x value of top pole
            polebottom2.setX(900); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop2.setLength(i);
            polebottom2.setY(i+400);

        }
        
        if ((Mousie.getX()+80) < 0) //resets mouse
        {
            Mousie.setX(1005);//reset x value
            int i = (int)(Math.random() * (600 + 1) + 100);
            Mousie.setY(i);

        }
        
        if ((Mousie2.getX()+80) < 0) //resets mouse
        {
            Mousie2.setX(2005);//reset x value
            int i = (int)(Math.random() * (800 + 1) + 700);
            Mousie2.setY(i);

        }
        
        if ((Mousie3.getX()+80) < 0) //resets mouse
        {
            Mousie3.setX(3005);//reset x value of top pole
            int i = (int)(Math.random() * (1000 + 1) + 300);
            Mousie3.setY(i);

        }

        //adds one to score when poles pass a certain point

        g.setColor(Color.white);
        String scoretext = "Score: " +score;
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,250,100);
        
    }
    
    public void LoseScreen2(Graphics g) //lose screen 2
    {
        g.drawImage(ls2,0,0, 900, 900,null);
        g.setColor(Color.white);
        String scoretext = "Your score: " +score+"!";
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,200,350);

    }
    
    public void WinScreen2(Graphics g) //win screen 2
    {
        g.drawImage(ws2,0,0,900,900,null);
        g.setColor(Color.white);
        String scoretext = "Your score: " +score+"!!";
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,200,350);

    }
    
    public void Levelthree(Graphics g)
    {
        //draw bird
        //g.setColor(Color.white);
        //g.fillOval(0, 0, 900, 900);
        g.drawImage(bgL3,0,0, 900,900,null);
        bird1.move(g);
        fish1.move(g);
        fish2.move(g);
        fish3.move(g);
        //draw background

        poletop1.move(g);
        polebottom1.move(g);
        poletop2.move(g);
        polebottom2.move(g);
        
        //change speed of pole
        poletop1.setDx(7);
        polebottom1.setDx(7);
        
        poletop2.setDx(7);
        polebottom2.setDx(7);
        
        //change color of pole
        poletop1.setColor(Color.blue);
        polebottom1.setColor(Color.blue);
        
        poletop2.setColor(Color.black);
        polebottom2.setColor(Color.black);
        
        
        if (bird1.getY() < p - 200)
            bird1.setDy(9);

        if ((poletop1.getX()+100) < 0) //resets first pole
        {
            score += 5;
            poletop1.setX(900);//reset x value of top pole
            polebottom1.setX(900); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop1.setLength(i);
            polebottom1.setY(i+400);

        }
        if ((poletop2.getX()+100) < 0) //resets second pole
        {
            score+= 5;;
            poletop2.setX(900);//reset x value of top pole
            polebottom2.setX(900); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop2.setLength(i);
            polebottom2.setY(i+400);

        }
        
        if ((fish1.getX()+80) < 0) //resets mouse
        {
            fish1.setX(2005);//reset x value of fish
            int i = (int)(Math.random() * (600 + 1) + 100);
            fish1.setY(i);

        }
        
        if ((fish2.getX()+80) < 0) //resets mouse
        {
            fish2.setX(3005);//reset x value of top pole
            int i = (int)(Math.random() * (800 + 1) + 700);
            fish2.setY(i);

        }
        
        if ((fish3.getX()+80) < 0) //resets mouse
        {
            fish3.setX(3005);//reset x value of top pole
            int i = (int)(Math.random() * (1000 + 1) + 300);
            fish3.setY(i);

        }

        //adds one to score when poles pass a certain point

        g.setColor(Color.white);
        String scoretext = "Score: " +score;
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,550,100);
        
    }
    
    public void LoseScreen3(Graphics g) //lose screen 2
    {
        g.drawImage(ls3,0,0,900,900,null);
        g.setColor(Color.black);
        String scoretext = "Your score: " +score+"!";
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,400,300);

    }
    
    public void WinScreen3(Graphics g) //win screen 2
    {
        g.drawImage(ws3,0,0,900,900,null);
        g.setColor(Color.black);
        String scoretext = "Your score: " +score+"!!";
        Font stringfont = new Font("SansSerif", Font.PLAIN, 60 );
        g.setFont(stringfont);
        g.drawString(scoretext,400,300);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (play)
            repaint();

        if (screen == 3) //level one
        {
            repaint();
            screen = 3; //game
            if (bird1.getLost())
                screen = 4; //lose screen for lvl one

            // if ball hits top poles
            int j = poletop1.getX() + poletop1.getWidth();
            if (bird1.getCX() > poletop1.getX() && bird1.getCX() < j && bird1.getY() < poletop1.getLength())
                screen = 4;

            int y = poletop2.getX() + poletop2.getWidth();
            if (bird1.getCX() > poletop2.getX() && bird1.getCX() < y && bird1.getY() < poletop2.getLength())
                screen = 4;

            // if ball hits bottom poles
            if (bird1.getCX() > poletop1.getX() && bird1.getCX() < j && (bird1.getY()+bird1.getSize()) > polebottom1.getY())
                screen = 4;

            if (bird1.getCX() > poletop2.getX() && bird1.getCX() < y && (bird1.getY()+bird1.getSize()) > polebottom2.getY())
                screen = 4;

            if (score == 100)
                screen = 5; //win screen
        }
        
        if (screen == 6) //level two
        {
            
            if (bird1.getLost())
                screen = 7; //lose screen for lvl two

            // if ball hits top poles
            int j = poletop1.getX() + poletop1.getWidth();
            if (bird1.getCX() > poletop1.getX() && bird1.getCX() < j && bird1.getY() < poletop1.getLength())
                screen = 7;

            int y = poletop2.getX() + poletop2.getWidth();
            if (bird1.getCX() > poletop2.getX() && bird1.getCX() < y && bird1.getY() < poletop2.getLength())
                screen = 7;

            // if ball hits bottom poles
            if (bird1.getCX() > poletop1.getX() && bird1.getCX() < j && (bird1.getY()+bird1.getSize()) > polebottom1.getY())
                screen = 7;

            if (bird1.getCX() > poletop2.getX() && bird1.getCX() < y && (bird1.getY()+bird1.getSize()) > polebottom2.getY())
                screen = 7;

            //if ball hits mouse
            int r = Mousie.getX() + Mousie.getSize();
            int q = Mousie2.getX() + Mousie2.getSize();
            int w = Mousie3.getX() + Mousie3.getSize();
            if (bird1.getCX() > Mousie.getX() && bird1.getCX() < r && (bird1.getY()+ bird1.getSize()) > Mousie.getY() &&
               (bird1.getY()+bird1.getSize()) < Mousie.getY()+ Mousie.getSize())
               screen = 7;
            
            if (bird1.getCX() > Mousie2.getX() && bird1.getCX() < q && (bird1.getY()+ bird1.getSize()) > Mousie2.getY() &&
               (bird1.getY()+bird1.getSize()) < Mousie2.getY()+ Mousie2.getSize())
               screen = 7;
                
            if (bird1.getCX() > Mousie3.getX() && bird1.getCX() < w && (bird1.getY()+bird1.getSize()) > Mousie3.getY() &&
               (bird1.getY()+bird1.getSize()) < Mousie3.getY()+ Mousie3.getSize())
                screen = 7;   
                
               
            if (score == 100)
                screen = 8;  //win sceen
        }
        
        if (screen == 9) //level three
        {
            
            if (bird1.getLost())
                screen = 10; //lose screen for lvl three

            // if ball hits top poles
            int j = poletop1.getX() + poletop1.getWidth();
            if (bird1.getCX() > poletop1.getX() && bird1.getCX() < j && bird1.getY() < poletop1.getLength())
                screen = 10;

            int y = poletop2.getX() + poletop2.getWidth();
            if (bird1.getCX() > poletop2.getX() && bird1.getCX() < y && bird1.getY() < poletop2.getLength())
                screen = 10;

            // if ball hits bottom poles
            if (bird1.getCX() > poletop1.getX() && bird1.getCX() < j && (bird1.getY()+bird1.getSize()) > polebottom1.getY())
                screen = 10;

            if (bird1.getCX() > poletop2.getX() && bird1.getCX() < y && (bird1.getY()+bird1.getSize()) > polebottom2.getY())
                screen = 10;

            //if ball hits mouse
            int r = fish1.getX() + fish1.getSize();
            int q = fish2.getX() + fish2.getSize();
            int w = fish3.getX() + fish3.getSize();
            if (bird1.getCX() > fish1.getX() && bird1.getCX() < r && (bird1.getY()+ bird1.getSize()) > fish1.getY() &&
               (bird1.getY()+bird1.getSize()) < fish1.getY()+ fish1.getSize())
               screen = 10;
            
            if (bird1.getCX() > fish2.getX() && bird1.getCX() < q && (bird1.getY()+ bird1.getSize()) > fish2.getY() &&
               (bird1.getY()+bird1.getSize()) < fish2.getY()+ fish2.getSize())
               screen = 10;
                
            if (bird1.getCX() > fish3.getX() && bird1.getCX() < w && (bird1.getY()+bird1.getSize()) > fish3.getY() &&
               (bird1.getY()+bird1.getSize()) < fish3.getY()+ fish3.getSize())
                screen = 10;   
                
               
            if (score == 100)
                screen = 11;  //win sceen
        }
        
        if (screen == 4 || screen == 5) //resetting easy level
        {
            poletop1.setX(905);//reset x value of top pole
            polebottom1.setX(905); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop1.setLength(i);
            polebottom1.setY(i+400);

            poletop2.setX(1405);//reset x value of top pole
            polebottom2.setX(1405); //reset x value of bottom pole
            int b = (int)(Math.random() * (300 + 1) + 100);
            poletop2.setLength(b);
            polebottom2.setY(b+400);

            bird1.setX(400);
            bird1.setY(400);
            bird1.setLost(false);
        }
        
        if (screen == 7 || screen == 8) //resetting medium level
        {
            poletop1.setX(900);//reset x value of top pole
            polebottom1.setX(900); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop1.setLength(i);
            polebottom1.setY(i+400);

            poletop2.setX(1400);//reset x value of top pole
            polebottom2.setX(1400); //reset x value of bottom pole
            int b = (int)(Math.random() * (300 + 1) + 100);
            poletop2.setLength(b);
            polebottom2.setY(b+400);

            bird1.setX(400);
            bird1.setY(400);
            bird1.setLost(false);
            
            Mousie.setX(1000);
            Mousie2.setX(2000);
            Mousie3.setX(3000);
        }
        
        if (screen == 10 || screen == 11) //resetting hard level
        {
            poletop1.setX(900);//reset x value of top pole
            polebottom1.setX(900); //reset x value of bottom pole
            int i = (int)(Math.random() * (300 + 1) + 100);
            poletop1.setLength(i);
            polebottom1.setY(i+400);

            poletop2.setX(1405);//reset x value of top pole
            polebottom2.setX(1405); //reset x value of bottom pole
            int b = (int)(Math.random() * (300 + 1) + 100);
            poletop2.setLength(b);
            polebottom2.setY(b+400);

            bird1.setX(400);
            bird1.setY(400);
            bird1.setLost(false);
            
            fish1.setX(2000);
            fish2.setX(3000);
            fish3.setX(4000);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        play = true;
        if (screen == 1)
            screen = 2;
        else if (screen == 2 && highlightE == 1)
        {
            screen = 3; //level one
        }
        else if (screen == 3)
        {
            bird1.setDy(-4);   //press to jump
            p = bird1.getY();
        }
        else if (screen == 2 && highlightE == 2)
        {
            screen = 6; //level two
        }
        else if (screen == 2 && highlightE == 3)
        {
            screen = 9; //must change later
        }
        else if (screen == 6)
        {
            bird1.setDy(-6);   //press to jump
            p = bird1.getY();
        }
        else if (screen == 9) 
        {
            bird1.setDy(-6);   //press to jump
            p = bird1.getY();
        }

    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        //level select
        if (screen == 2 && x > 130 && x < 292 && y > 388 && y < 598 ) //easy level selected
            highlightE = 1;
        if (screen == 2 && x > 350 && x < 550 && y > 388 && y < 598 ) //medium level
            highlightE = 2;
        if (screen == 2 && x > 620 && x < 755 && y > 388 && y < 598 )//hard level
            highlightE = 3;

        //lose page buttons for level one 
        if (screen == 4 && x > 394 && x < 1100 && y > 370 && y < 520) //home page/level select
        {
            screen = 2;
            score = 0;
        }
        if (screen == 4 && x > 394 && x < 1100 && y > 611 && y < 750) //play again
        {
            screen = 3;
            score = 0;
        }
        //win page
        if (screen == 5 && x > 394 && x < 1100  && y > 600 && y < 1000)
        {
            screen = 2;
            score = 0;
        }
        
        //lose page buttons for level two
        if (screen == 7 && x > 394 && x < 1100 && y > 370 && y < 520) //level select
        {
            screen = 2;
            score = 0;
        }
        if (screen == 7 && x > 394 && x < 1100 && y > 611 && y < 750) //play again
        {
            screen = 6;
            score = 0;
        }
        //win screen
        if (screen == 8 && x > 394 && x < 1100  && y > 600 && y < 1000)
        {
            screen = 2;
            score = 0;
        }
        
        //lose page buttons for level three
        if (screen == 10 && x > 394 && x < 1100 && y > 370 && y < 520) //level select
        {
            screen = 2;
            score = 0;
        }
        if (screen == 10 && x > 394 && x < 1100 && y > 611 && y < 750) //play again
        {
            screen = 9;
            score = 0;
        }
        //win screen
        if (screen == 11 && x > 394 && x < 1100  && y > 600 && y < 1000)
        {
            screen = 2;
            score = 0;
        }
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
    }
}