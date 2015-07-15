/**
 * Viet Nguyen. Final project Winter 2014
 */
package last.progpracticum;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * AbstractArt Driver class.
 * @author Monika
 * @version Winter 2014
 */
public final class ArtMain {
    
    /**
     * Private constructor - guards against instantiation.
     */
    private ArtMain() {
        
    }
    
    /**
     * Main method of a simulation driver.
     * @param aRgs command line arguments, assumes none
     * @custom.post art gui with all its functionality created and running
     */
    public static void main(String[] aRgs) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final ArtFrame frame = new ArtFrame();
                frame.setTitle("CSS305 Computer Art");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}