/**
 * 
 */
package three.progpracticum;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Runs the ice cream store.
 * 
 * @version 1
 * @author Viet Nguyen
 */
public final class IceCreamMain {
    
    /**
     * Denominator for screen height.
     */
    public static final int NUMB = 3;
    /** 
     * private default constructor.
     */
    private IceCreamMain() {
    }
    
    /**
     * main method driving the ice_cream order system.
     * @param aRgs none
     */
    public static void main(String[] aRgs) {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                final JFrame frame = new IceCream();
                frame.setTitle("Create your Ice-Cream");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                final Toolkit kit = Toolkit.getDefaultToolkit();  // getting tool kit object
                final Dimension screenSize = kit.getScreenSize();
                final int screenWidth = screenSize.width; 
                final int screenHeight = screenSize.height; 
                
                frame.setSize(screenWidth / 2, screenHeight / NUMB); 
                frame.setLocationByPlatform(true);  
                final Image img = kit.getImage("source/1.gif");
                frame.setIconImage(img);           
                frame.setVisible(true);
            }
        });
    }
}

