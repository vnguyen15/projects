/**
 * 
 */
package three.progpracticum;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * 
 * @author V
 * @version 1
 */
@SuppressWarnings("serial")
public class ConePanel extends JPanel {
    
    /**
     * Cone label.
     */
    private JLabel myCone;
    /**
     * A button displays Sugar Cone button.
     */
    private JToggleButton mySugarCone;
    
    /**
     * Button that represents waffle cone.
     */
    private JToggleButton myWaffleCone;
    
    /**
     * Button that represents a paper cup.
     */
    private JToggleButton myPaperCup;
    /**
     * 
     */
    private ButtonGroup myToggleGroup;
    /**
     * 
     */
    public ConePanel() {
        super();
        createToggleButtons();
    }
    
    /**
     * 
     */
    private void createToggleButtons() {
        
        myCone = new JLabel("Cone:");
        
        myToggleGroup = new ButtonGroup();
        mySugarCone = new JToggleButton("Sugar Cone", true);
        mySugarCone.setForeground(Color.RED);
        myWaffleCone = new JToggleButton("Waffle Cone");
        myWaffleCone.setForeground(Color.MAGENTA);
        myPaperCup = new JToggleButton("Paper Cup");
        myPaperCup.setForeground(Color.GRAY);
        // adding button to a group;
        myToggleGroup.add(mySugarCone);
        myToggleGroup.add(myWaffleCone);
        myToggleGroup.add(myPaperCup);
        
        // adjust size of all buttons to waffle cone size.
        mySugarCone.setMinimumSize(myWaffleCone.getMinimumSize());
        mySugarCone.setMaximumSize(myWaffleCone.getMaximumSize());
        myPaperCup.setMinimumSize(myWaffleCone.getMinimumSize());
        myPaperCup.setMaximumSize(myWaffleCone.getMaximumSize());
        
        // add all the cone buttons to a cone panel
        add(myCone);
        add(mySugarCone);
        add(myWaffleCone);
        add(myPaperCup);
    }



    /**
     * @return the mySugarCone
     */
    public JToggleButton getMySugarCone() {
        return mySugarCone;
    }

    /**
     * @return the myWaffleCone
     */
    public JToggleButton getMyWaffleCone() {
        return myWaffleCone;
    }

    /**
     * @return the myPaperCup
     */
    public JToggleButton getMyPaperCup() {
        return myPaperCup;
    }
}
