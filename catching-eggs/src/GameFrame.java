import java.awt.BorderLayout;  
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;







public class GameFrame extends JFrame {
    
	AudioInputStream ai;
	Clip c;
	File myMusic;
	
    private GamePanel myPanel;
    private int width;
    
    public GameFrame() {
        super();
        myPanel = new GamePanel();
        add(myPanel, BorderLayout.CENTER);
        setSize(635, 505); 
        
        width = getWidth();
        myMusic = new File("source/music.wav");
        
        KListener key = new KListener();
        addKeyListener(key);
        
        
    }
    
    
    public class KListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent anEvent) {
            // TODO Auto-generated method stub
           // Object source = anEvent.getSource();
          //  if (source.getClass().get)
            if ( anEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                if (myPanel.getMyXK() >= 53) {
                    myPanel.setMyXk(-15);
                    if (myPanel.getMyHP() >= 0) {
                        repaint();
                    }
                }
            }
            if ( anEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                //myPanel.setNumbXk(-1);;
                if (myPanel.getMyXK() < myPanel.getWidth() - 108) {
                    myPanel.setMyXk(15);
                    if (myPanel.getMyHP() >= 0) {
                        repaint();
                    }
                }
            }
            
            if ( anEvent.getKeyCode() == KeyEvent.VK_R) {
                myPanel.setMyHP();
                myPanel.setStart(true);
                
            }
            
            // stop key
            if ( anEvent.getKeyCode() == KeyEvent.VK_S) {
                myPanel.setStart(false);
               
            }
            
            
            if ( anEvent.getKeyCode() == KeyEvent.VK_M) {
                myPanel.setFound(true);
                myPanel.setMyY(10);
                myPanel.setFound1(true);
                myPanel.setMyY1(10);
                
                try {
                	c = AudioSystem.getClip();
                    ai = AudioSystem.getAudioInputStream(myMusic);
                   // c.close();
                  //  c = AudioSystem.getClip();
                    c.open(ai);
                    c.start();
                    c.loop(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub
            
            
        }

        @Override
        public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }
 public static void main(String[] aRgs) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final GameFrame frame = new GameFrame();
                
                frame.setTitle("Collecting Eggs");
                
                
                final Toolkit kit = Toolkit.getDefaultToolkit();  // getting tool kit object
                final Dimension screenSize = kit.getScreenSize();
                final int screenWidth = screenSize.width; 
                final int screenHeight = screenSize.height;
                frame.setLocation(screenWidth / 4, screenHeight / 4);
                frame.setSize(705, 650);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

