/**
 * 
 */


import java.awt.BasicStroke;  
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



//import timerexample.MovingShapePanel.TimerListener;

/**
 * This is a panel where all the shapes are drawn and animation take place.
 * 
 * @author Viet Nguyen
 * @version 1
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    /**
     * This is max color range.
     */
    public static final int RANGE = 255;
    
    private int myY;
    private int myX;
    private Timer myTimer;
    private int numbX;
    private int numbY;




    private int myYk;
    private int myXk;
    
    private int numbXk;
    private int numbYk;

    private boolean found;
    
    private Random myRdn;

    private int myX1;

    private int numbY1;

    private int myY1;

    private boolean found1;

    private boolean good;
    
    private BufferedImage myGood1;

    private int myCount;

    private BufferedImage myNo;

    private int myCountNo;

    private int myCountNo1;

    private BufferedImage myEastern;

    private boolean found3;

    private int myY3;

    private int myX3;

    private int numbY3;

    private int myDirection;
    
    private BufferedImage myOhNo;

    private BufferedImage myExcellent1;

    private BufferedImage myExcellent2;

    private BufferedImage myExcellent3;
    
    private BufferedImage myStart;
    
    private int myEasternCount;

    private int myEasternNo;
    
    private int myHP;

    
    AudioInputStream ai;
    Clip c;
    File myDrop;

    private int myPoint;

    private boolean myAdd;

    private boolean myAdd3;

    private boolean myAdd1;

    private boolean myHPDone;

    private boolean myHPReady;

    private BufferedImage brokenEgg;
    
    private BufferedImage myCloud;
    
    private boolean startGame;

    private boolean found4;

    private int myYH;

    private int myXH;

    private BufferedImage myHeart;

    private int myCountNo4;

    private int myCount4;
    
    private int numbY4;

    private int myCountH;

    private boolean myHeartDone;

    private int myCountNo5;

    private boolean found5;

    private int myYB;

    private BufferedImage myBomb;

    private int myXB;

    private int numbYB;

    private int myCount5;

    private int myCountB;

    private boolean myBombDone;

    private Image myExplosion;

    private BufferedImage myClound1;

    private BufferedImage myCloud1;

    private BufferedImage myExplosion1;
    /**
     * Create Art Models, Shapes, etc.
     */
    public GamePanel() {
        super();
        
        startGame = false;
        myHPReady = true;
        myHP = 10;
        myAdd = true;
        myAdd1 = true;
        myAdd3 = true;
        myPoint = 0;
        myRdn = new Random();
        myEasternCount = 0;
        myEasternNo = 0;
        myCount = 0;
        myCount4 = 0; // heart
        myCount5 = 0; //bomb timing
        myCountNo4 = 0; // heart
        myCountNo5 = 0; // bomb
        myCountNo = 0;
        myCountNo1 = 0;
        good = false;

        found = true;
        found1 = true;
        found4 = true;
        found5 = true;
        
        myX = 100;
        myX1 = 400;
        myXH = 300;
        myXB = 250;
        
        myCountH = 20; // heart timing
        myCountB = 15;
        
        
        myY = 50;
        myY1 = 50;
        myYH = 50; // y coordinate heart
        myYB = 50; // bomb
        
        numbX = 5;
        numbY = 3;
        numbY1 = 2;
        numbY4 = 3;
        numbYB = 3;
        
        // eastern
        found3 = true;
        myY3 = 50;
        
        myX3 = 500;
        numbY3 = 5;
        myDirection = 1;
        
        myYk =  430;
        myXk = 400;
        numbXk = 10;
        numbYk = 5;
        
        // create image files;
        createImageFiles();
        
        final MouseListener mouse = new MouseListener();
        
        addMouseListener(mouse);
        myTimer = new Timer(1, new TimerListener());
        
        myTimer.start();
        

    }
    
    private void createImageFiles() {
        try {
            myStart = ImageIO.read(new File("source/start.png"));
            myGood1 = ImageIO.read(new File("source/good2.png"));
            myNo = ImageIO.read(new File("source/no.png"));
            myEastern = ImageIO.read(new File("source/easternEgg1.png"));
            myOhNo = ImageIO.read(new File("source/ohNO.png"));
            myExcellent1 = ImageIO.read(new File("source/excellent1a.png"));
            myExcellent2 = ImageIO.read(new File("source/excellent2a.png"));
            myExcellent3 = ImageIO.read(new File("source/excellent3a.png"));
            brokenEgg = ImageIO.read(new File("source/brokenEgg.png"));
            myCloud = ImageIO.read(new File("source/cloud1.png"));
            myCloud1 = ImageIO.read(new File("source/cloud2.png"));
            myHeart = ImageIO.read(new File("source/heart.png"));
            myBomb = ImageIO.read(new File("source/bomb.png"));
            myExplosion = ImageIO.read(new File("source/explosion.png"));
            myExplosion1 = ImageIO.read(new File("source/explosionGround.png"));
            // drop sound
            myDrop = new File("source/drop.wav");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Paints some ellipses.
     * 
     * @param aGraphics The graphics context to use for painting.
     */
    public void paintComponent(final Graphics aGraphics) {
        super.paintComponent(aGraphics);
        final Graphics2D g2d = (Graphics2D) aGraphics;
      
          // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
     
       
        BufferedImage bg;
        BufferedImage img1;
        BufferedImage img2;
        BufferedImage skateBoard;
        
        try {
           // zero = ImageIO.read(new File("background/zero.png"));
           // one = ImageIO.read(new File("background/one.png"));
            bg = ImageIO.read(new File("source/BG1.png"));
            img1 = ImageIO.read(new File("source/goldenEgg.png"));
            img2 = ImageIO.read(new File("source/egg2.png"));
            skateBoard = ImageIO.read(new File("source/basket.png"));
            
            /* Draw the image, applying the filter */   
            g2d.drawImage(bg, 0, 0, null);
            if (!startGame) {
                g2d.drawImage(myStart, 100, 200, null);
                g2d.drawImage(myCloud, 250, 300, null);
            }
            g2d.setFont(new Font("Brush Script MT", Font.BOLD, 35));
            g2d.setPaint(Color.DARK_GRAY);
           // Font font = new Font();
            g2d.drawString("Score: " + myPoint, 410, 575);
            
            //g2d.setPaint(Color.green);
            
            int height = 8;
            int xCord = 210;
            int yCord = 565;
            g2d.setFont(new Font("Brush Script MT", Font.BOLD, 35));
            g2d.drawString("HP: ", 140, 575);
            g2d.setPaint(Color.green);
            for (int i = 0; i < myHP; i++) {
                final Rectangle2D rect = new Rectangle2D.Double(xCord, yCord, 10, height);
                
                if (myHP < 7) {
                    g2d.setPaint(Color.orange);
                } 
                if (myHP < 4) {
                    g2d.setPaint(Color.red);
                }
                g2d.draw(rect);
                g2d.fill(rect);
                height += 2;
                xCord += 15;
                yCord -= 2;
            }
           // g2d.drawString("HP: " + myHP, 180, 575);
            
            
            // draw basket
            g2d.drawImage(skateBoard, myXk , myYk, null);
            
            //if (myY == getHeight() - 115) {
                if (found && myY <= 443) {
                    
                    g2d.drawImage(img1, myX + 35 , myY += numbY, null);
                  //  myPoint += 1;
                } else {
                    if (found) {
                        g2d.drawImage(brokenEgg, myX, myY + 13, null);
                        myCountNo += 1;
                        
                        // subtracting HP
                        if (myHPReady) {
                            myHP -= 1;
                            myHPReady = false; // turn off subtracting HP
                        }
                        
                        if (myCountNo == 15) {
                            myY = 50;
                            myCountNo = 0;
                            myX = myRdn.nextInt(getWidth() - 100) + 10;
                            
                            myHPReady = true;
                        }
                     // adding drop sound
                        if(myCountNo == 0) {
                            try {
                                c = AudioSystem.getClip();
                                ai = AudioSystem.getAudioInputStream(myDrop);
                                // c.close();
                                //  c = AudioSystem.getClip();
                                c.open(ai);
                                c.start();
                            //  c.loop(10);
                            } catch (Exception e) {
                            e.printStackTrace();
                            }
                        }
                    } else {
                        g2d.drawImage(myGood1, myX, myY + 25, null);
                        // adding point
                        if (myAdd) {
                            myPoint += 2;
                            myAdd = false;
                        }
                        myCount += 1;
                       // myPoint += 1;
                        if (myCount == 15) {
                            myY = 50;
                            found = true;
                            myCount = 0;
                            myX = myRdn.nextInt(getWidth() - 100) + 10;
                            myAdd = true;
                        }
                    }
                }
                
                // egg1
                if (found1 && myY1 <= 443) {
                    
                    g2d.drawImage(img2, myX1, myY1 += numbY1, null);
                    
                } else {
                    if (found1) {
                        g2d.drawImage(brokenEgg, myX1, myY1 + 13, null);
                        myCountNo1 += 1;
                        if (myHPReady) {
                            myHP -= 1;
                            myHPReady = false;
                        }
                        if (myCountNo1 == 15) {
                            myY1 = 50;
                            myCountNo1 = 0;
                            myX1 = myRdn.nextInt(getWidth() - 100) + 10;
                            myHPReady = true;
                            
                        }
                        // adding drop sound
                        if(myCountNo1 == 0) {
                            try {
                                c = AudioSystem.getClip();
                                ai = AudioSystem.getAudioInputStream(myDrop);
                                // c.close();
                                //  c = AudioSystem.getClip();
                                c.open(ai);
                                c.start();
                            //  c.loop(10);
                            } catch (Exception e) {
                            e.printStackTrace();
                            }
                        }
                        
                    } else {
                        g2d.drawImage(myGood1, myX1 , myY1 + 25, null);
                        // adding point
                        if (myAdd1) {
                            myPoint += 1;
                            myAdd1 = false;
                        }
                        
                        myCountNo1 += 1;
                        
                        if (myCountNo1 == 15) {
                            myY1 = 50;
                            found1 = true;
                            myCountNo1 = 0;
                            myX1 = myRdn.nextInt(getWidth() - 100) + 10;
                           // myPoint += 1;
                            myAdd1 = true;
                        }
                    }
                }
                
                // eastern egg
                if (found3 && myY3 <= 440) {
                    if (myX3 >= getWidth() - 90) {
                        myDirection = -1;
                        
                    } else if (myX3 <= 0) {
                        myDirection = 1;
                    }
                        g2d.drawImage(myEastern, myX3 += 6 * myDirection, myY3 += numbY3, null);
                        
                    
                } else {
                    if (found3) {
                        g2d.setPaint(Color.red);
                        g2d.drawString("Break!", myX3 , myY3 + 50);
                        myEasternNo += 1;
                        if (myHPReady) {
                            myHP -= 1;
                            myHPReady = false;
                        }
                        if (myEasternNo == 18) {
                            myY3 = 50;
                            myEasternNo = 0;
                            myX3 = myRdn.nextInt(getWidth() - 100) + 10;
                            myHPReady = true;
                        }
                        // adding drop sound
                        if(myEasternNo == 0) {
                            try {
                                c = AudioSystem.getClip();
                                ai = AudioSystem.getAudioInputStream(myDrop);
                                // c.close();
                                //  c = AudioSystem.getClip();
                                c.open(ai);
                                c.start();
                            //  c.loop(10);
                            } catch (Exception e) {
                            e.printStackTrace();
                            }
                        }
                        
                    } else {
                        // check if point already add or not
                        if (myAdd3) {
                            myPoint += 3;
                            myAdd3 = false;
                        }
                        if (myEasternCount <= 9) {
                            g2d.drawImage(myExcellent1, myX3 , myY3, null);
                        } else if ( myEasternCount <= 18) {
                           g2d.drawImage(myExcellent2, myX3 , myY3, null); 
                        } else if ( myEasternCount <= 27){
                           g2d.drawImage(myExcellent3, myX3 , myY3, null);
                        }
                        myEasternCount ++;
                        if (myEasternCount == 28) {
                            myY3 = 50;
                            found3 = true;
                            myEasternCount = 0;
                            myX3 = myRdn.nextInt(getWidth() - 100) + 10;
                            myAdd3 = true;
                        }
                    }
                } // end eastern egg
                
                // heart
            if (myPoint >= myCountH && myPoint <= myCountH + 5) {
                myHeartDone = true;
            }
            if (myHeartDone) {
                if (found4 && myYH <= 443) {
                    
                    g2d.drawImage(myHeart, myXH + 35 , myYH += numbY4, null);
                  //  myPoint += 1;
                } else {
                    if (found4) {
                        g2d.drawImage(myNo, myXH, myYH + 13, null);
                        myCountNo4 += 1;
                        
                       
                        
                        if (myCountNo4 == 15) {
                            myYH = 50;
                            myCountNo4 = 0;
                            myXH = myRdn.nextInt(getWidth() - 100) + 10;
                            
                            myHPReady = true;
                            myHeartDone = false;
                            myCountH += 20;
                        }
                     // adding drop sound
                        if(myCountNo4 == 0) {
                            try {
                                c = AudioSystem.getClip();
                                ai = AudioSystem.getAudioInputStream(myDrop);
                                // c.close();
                                //  c = AudioSystem.getClip();
                                c.open(ai);
                                c.start();
                            //  c.loop(10);
                            } catch (Exception e) {
                            e.printStackTrace();
                            }
                        }
                    } else {
                        g2d.drawImage(myGood1, myXH, myYH + 25, null);
                        // adding HP
                        if (myHPReady) {
                            myHP += 2;
                            myHPReady = false; // turn off adding HP
                        }
                        
                        // adding point
                      /*  if (myAdd) {
                            myPoint += 2;
                            myAdd = false;
                        }*/
                        myCount4 += 1;
                       // myPoint += 1;
                        if (myCount4 == 15) {
                            myYH = 50;
                            found4 = true;
                            myCount4 = 0;
                            myXH = myRdn.nextInt(getWidth() - 100) + 10;
                           // myAdd = true;
                            myHeartDone = false;
                            myCountH += 20;
                        }
                    }
                }
                
            } // end heart
            
            // Bombs
            if (myPoint >= myCountB && myPoint <= myCountB + 5) {
                myBombDone = true;
            }
            if (myBombDone) {
                if (found5 && myYB <= 443) {
                    
                    g2d.drawImage(myBomb, myXB + 35 , myYB += numbYB, null);
                  //  myPoint += 1;
                } else { 
                    if (found5) { // touch the ground
                        g2d.drawImage(myExplosion1, myXB + 20, myYB, null);
                        myCountNo5 += 1;

                        if (myCountNo5 == 15) {
                            myYB = 50;
                            myCountNo5 = 0;
                            myXB = myRdn.nextInt(getWidth() - 100) + 10;
                            myBombDone = false;
                            myCountB += 15;
                        }
                     // adding drop sound
                        if(myCountNo5 == 0) {
                            try {
                                c = AudioSystem.getClip();
                                ai = AudioSystem.getAudioInputStream(myDrop);
                                // c.close();
                                //  c = AudioSystem.getClip();
                                c.open(ai);
                                c.start();
                            //  c.loop(10);
                            } catch (Exception e) {
                            e.printStackTrace();
                            }
                        }
                    } else { // if touch the basket
                        g2d.drawImage(myExplosion, myXB, myYB - 20, null);
                        myCount5 += 1;
                        if (myHPReady) {
                            myHP -= 2;
                            myHPReady = false; // turn off subtracting HP
                        }
                        if (myCount5 == 15) { // repeat explosion for 15 count
                            myYB = 50;
                            found5 = true;
                            myCount5 = 0;
                            myXB = myRdn.nextInt(getWidth() - 100) + 10;
                            
                            myHPReady = true;
                            myBombDone = false;
                            myCountB += 15;
                        }
                    }
                }
                
            } // end bomb
            
                
                /////////////////////
                if (myHP > 0 && startGame) {
                    repaint();
                }
            } catch (final IOException e) {
                e.printStackTrace();
        }    
        if (myHP >= 0 && startGame) {
            repaint();
        }
    }
    
    
    private class TimerListener implements ActionListener {



        

        @Override
        public void actionPerformed(ActionEvent e) {
        
           // updateHorizontalMove1();
            
            updateVerticalMove();
            if (myHP > 0 && startGame) {
            repaint();
            }
            
        }
        
        private void updateVerticalMove() {

            final int diff = Math.abs(myXk - myX);
            if (myY > 430 && myY < 450
                    && (diff < 50 &&  diff >= 0)) {
                numbY = myRdn.nextInt(3) + 1;
                found = false;
            }
            
            final int diff1 = Math.abs(myXk - myX1);
            if (myY1 > 430 && myY1 < 450
                    && (diff1 < 50 &&  diff1 >= 0)) {
                numbY1 = myRdn.nextInt(4) + 1;
                found1 = false;
              //  good = true;
            }
            
            // eastern eggs
            final int diff3 = Math.abs(myXk - myX3);
            if (myY3 > 400 && myY3 < 450
                    && (diff3 < 50 &&  diff3 >= 0)){
                
                found3 = false;
            }
            
            // heart
            final int diff4 = Math.abs(myXk - myXH);
            if (myYH > 430 && myYH < 450
                    && (diff4 < 50 &&  diff4 >= 0)) {
                numbY4 = myRdn.nextInt(4) + 1;
                found4 = false;
            }
            
            // bomb
            final int diff5 = Math.abs(myXk - myXB);
            if (myYB > 430 && myYB < 450
                    && (diff5 < 50 &&  diff5 >= 0)) {
                numbYB = myRdn.nextInt(5) + 3;
                found5 = false;
            } // end bomb
            
            
            if (myY > getHeight() - 105 && myY < getHeight() - 85 ) {
                myX = myRdn.nextInt(getWidth() - 100) + 10;
                myY = 10;
                numbY = myRdn.nextInt(6) + 1;
            }
            
            if (myY1 > getHeight() - 105 && myY1 < getHeight() - 85 ) {
                myX1 = myRdn.nextInt(getWidth() - 100) + 10;
                
                
                
                myY1 = 10;
                numbY1 = myRdn.nextInt(3) + 1;
            }
            
            // eastern eggs
            if (myY3 > getHeight() - 105 && myY3 < getHeight() - 85 ) {
                myX3 = myRdn.nextInt(getWidth() - 100) + 10;
                myY3 = 10;
                numbY3 = myRdn.nextInt(6) + 1;
                // adding drop sound
                try {
                    c = AudioSystem.getClip();
                    ai = AudioSystem.getAudioInputStream(myDrop);
                   // c.close();
                  //  c = AudioSystem.getClip();
                    c.open(ai);
                    c.start();
                    c.loop(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           
            
        /*    
            if (myY >= getHeight() - 80) {
                numbY *= -1;
            }
            if (myY <= 0) {
                numbY *= -1;
            }*/
            
        }
    }
    
    

    /**
     * @param myYk the myYk to set
     */
    public void setMyYk(int myYk) {
        this.myYk = myYk;
    }

    /**
     * @param myXk the myXk to set
     */
    public void setMyXk(int myXk) {
        this.myXk += myXk;
    }
    
    public int getMyXK() {
        return myXk;
    }
    /**
     * @param numbXk the numbXk to set
     */
    public void setNumbXk(int numbXk) {
        this.numbXk *= numbXk;
    }
    
    public int getNumbXk() {
        return numbXk;
    }

    /**
     * @param numbYk the numbYk to set
     */
    public void setNumbYk(int numbYk) {
        this.numbYk = numbYk;
    }

    public void setFound(boolean aFound) {
        found = aFound;
    }
    
    public void setMyY (final int aNumb) {
        myY = 10;
    }

    public void setFound1(boolean b) {
        found1 = b;
        
    }

    public void setMyY1(int i) {
        myY1 = i;
        
    }
    
    public class MouseListener extends MouseAdapter {
    	public void mousePressed(MouseEvent anEvent) {
            if (SwingUtilities.isLeftMouseButton(anEvent)) {
                if (anEvent.getPoint().getX() < 400 && anEvent.getPoint().getX() > 180 
                && anEvent.getPoint().getY() > 250 && anEvent.getPoint().getY() < 410) {
                    myCloud = myCloud1;
                }
            }           
    	}
    	
    	public void mouseReleased(MouseEvent anEvent) {
    	    if (SwingUtilities.isLeftMouseButton(anEvent)) {
                if (anEvent.getPoint().getX() < 400 && anEvent.getPoint().getX() > 180 
                && anEvent.getPoint().getY() > 250 && anEvent.getPoint().getY() < 410) {
                    startGame = true;
                }
            }    
    	}
    }


    public int getMyHP() {
        // TODO Auto-generated method stub
        return myHP;
    }

    public void setMyHP() {
        // TODO Auto-generated method stub
        myHP = 10;
    }

    public void setStart(final boolean aStart) {
        startGame = aStart;
        
    }
}
