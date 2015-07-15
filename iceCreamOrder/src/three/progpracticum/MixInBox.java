/**
 * 
 */
package three.progpracticum;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.JCheckBox;


/**
 * This is a Mix-In Box.
 * 
 * @author Viet Nguyen
 * @version 1
 *
 */
@SuppressWarnings("serial")
public class MixInBox extends Box {
    
    /**
     * Background Color for the ice cream store.
     */
    private static final int BACKGROUND_COLOR = 0xadd8e6;
    
    /**
     * A check box that represents berry lemon mix-in.
     */
    private JCheckBox myBerryLemon;
    
    /**
     * A check box that represents banana cherry mix-in.
     */
    private JCheckBox myBananaCherry;
    
    /**
     * A check box that represents grape orange mix-in.
     */
    private JCheckBox myGrapeOrange;
    
    
    /**
     * A check box that represents apple coconut mix-in.
     */
    private JCheckBox myAppleCoconut;
    
    
    /**
     * A check box that represents cookie orio mix-in.
     */
    private JCheckBox myCookieOrio;
    
    
    /**
     * A check box that represents pineapple grape mix-in.
     */
    private JCheckBox myPineAppleGrape;
       
    /**
     * Construct a box contains all the check boxes.
     */
    public MixInBox() {
        super(1); //x-axis = 0, y-axix = 1
        createCheckBoxes();
    }
    
    /**
     * Create check boxes.
     */
    private void createCheckBoxes() {
        // construct all the mix-in check box with their values
        myBerryLemon = new JCheckBox("Berry Lemon");
        myBerryLemon.setBackground(new Color(BACKGROUND_COLOR));
        myBerryLemon.setForeground(Color.YELLOW);
        myBananaCherry = new JCheckBox("Banana Cherry");
        myBananaCherry.setBackground(new Color(BACKGROUND_COLOR));
        myBananaCherry.setForeground(Color.PINK);
        myGrapeOrange = new JCheckBox("Grape Orange");
        myGrapeOrange.setBackground(new Color(BACKGROUND_COLOR));
        myGrapeOrange.setForeground(Color.ORANGE);
        myAppleCoconut = new JCheckBox("Apple Coconut");
        myAppleCoconut.setBackground(new Color(BACKGROUND_COLOR));
        myAppleCoconut.setForeground(Color.WHITE);
        myPineAppleGrape = new JCheckBox("Pineaple Grape");
        myPineAppleGrape.setBackground(new Color(BACKGROUND_COLOR));
        myPineAppleGrape.setForeground(Color.GREEN);
        myCookieOrio = new JCheckBox("Cookie Orio");
        myCookieOrio.setForeground(Color.GRAY);
        myCookieOrio.setBackground(new Color(BACKGROUND_COLOR));
        
        // adding all the check boxes to a Panel
        add(myBerryLemon);
        add(myBananaCherry);
        add(myGrapeOrange);
        add(myAppleCoconut);
        add(myPineAppleGrape);
        add(myCookieOrio);
    }

    /**
     * @return the myBerryLemon
     */
    public JCheckBox getMyBerryLemon() {
        return myBerryLemon;
    }

    /**
     * @return the myBananaCherry
     */
    public JCheckBox getMyBananaCherry() {
        return myBananaCherry;
    }

    /**
     * @return the myGrapeOrange
     */
    public JCheckBox getMyGrapeOrange() {
        return myGrapeOrange;
    }

    /**
     * @return the myAppleCoconut
     */
    public JCheckBox getMyAppleCoconut() {
        return myAppleCoconut;
    }

    /**
     * @return the myCookieOrio
     */
    public JCheckBox getMyCookieOrio() {
        return myCookieOrio;
    }

    /**
     * @return the myPineAppleGrape
     */
    public JCheckBox getMyPineAppleGrape() {
        return myPineAppleGrape;
    }
}
