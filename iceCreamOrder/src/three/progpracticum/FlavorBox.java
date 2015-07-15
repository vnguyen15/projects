/**
 * 
 */
package three.progpracticum;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * This is representing an ice cream flavor Box with Radio buttons. 
 *
 * 
 * @author Viet Nguyen
 * @version 1
 */
@SuppressWarnings("serial")
public class FlavorBox extends Box {
    
    /**
     * A radio button that represents French vanilla flavor.
     */
    private JRadioButton myFrenchVanilla;
    
    /**
     * A radio button that represents chocolate flavor.
     */
    private JRadioButton myChocolate;
    
    /**
     * A radio button that represents straw berry flavor.
     */
    private JRadioButton myStrawberry;
    
    /**
     * A radio button that represents Mango flavor.
     */
    private JRadioButton myMango;
    
    /**
     * A radio button that represents Lemon flavor.
     */
    private JRadioButton myLemon;
    
    /**
     * group of buttons.
     */
    private ButtonGroup myRadioGroup;
    
    /**
     * Construct a Radio button Box.
     */
    public FlavorBox() {
        super(1); //x-axis = 0, y-axix = 1
        createRadioButtons();
    }
    
    /**
     * create Radio buttons.
     */
    private void createRadioButtons() {
        
        // Construct the Radio Buttons with their values.
        myRadioGroup = new ButtonGroup();
        final JLabel flavors = new JLabel("The flavors of your choice:");
        myFrenchVanilla = new JRadioButton("French Vanilla", true);
        myFrenchVanilla.setForeground(Color.LIGHT_GRAY);
        myFrenchVanilla.setBackground(Color.WHITE);
        myChocolate = new JRadioButton("Chocolate", false);
        myChocolate.setMinimumSize(myFrenchVanilla.getMinimumSize());
        myChocolate.setMaximumSize(myFrenchVanilla.getMaximumSize());
        myChocolate.setBackground(Color.GRAY);
        myStrawberry = new JRadioButton("Strawerry", false);
        myStrawberry.setBackground(Color.RED);
        myStrawberry.setMinimumSize(myFrenchVanilla.getMinimumSize());
        myStrawberry.setMaximumSize(myFrenchVanilla.getMaximumSize());
        myMango = new JRadioButton("Mango", false);
        myMango.setBackground(Color.ORANGE);
        myMango.setMinimumSize(myFrenchVanilla.getMinimumSize());
        myMango.setMaximumSize(myFrenchVanilla.getMaximumSize());
        myLemon = new JRadioButton("Lemon", false);
        myLemon.setBackground(Color.YELLOW);
        myLemon.setMinimumSize(myFrenchVanilla.getMinimumSize());
        myLemon.setMaximumSize(myFrenchVanilla.getMaximumSize());
        
        // Grouping the radio buttons
        myRadioGroup.add(myFrenchVanilla);
        myRadioGroup.add(myChocolate);
        myRadioGroup.add(myStrawberry);
        myRadioGroup.add(myMango);
        myRadioGroup.add(myLemon);
        
        // add label and radio buttons to JPanel flavor.
        add(flavors);
        add(myFrenchVanilla);
        add(myChocolate);
        add(myStrawberry);
        add(myMango);
        add(myLemon);
    }

    /**
     * @return the myFrenchVanilla
     */
    public JRadioButton getMyFrenchVanilla() {
        return myFrenchVanilla;
    }

    /**
     * @return the myChocolate
     */
    public JRadioButton getMyChocolate() {
        return myChocolate;
    }

    /**
     * @return the myStrawberry
     */
    public JRadioButton getMyStrawberry() {
        return myStrawberry;
    }

    /**
     * @return the myMango
     */
    public JRadioButton getMyMango() {
        return myMango;
    }

    /**
     * @return the myLemon
     */
    public JRadioButton getMyLemon() {
        return myLemon;
    }

}
