package last.progpracticum;

import java.awt.Color; 
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

/**
 * This is representing tool bars.
 * @author Viet NGuyen
 * @version 1
 *
 */
@SuppressWarnings("serial")
public class ArtToolBar extends JToolBar {
    
    /**
     * This is the number Rectangle spinner.
     */
    private JSpinner myRectangleSpinner;
    
    /**
     * This is the number ellipse spinner.
     */
    private JSpinner myEllipseSpinner;
    
    /**
     * This is the number line spinner.
     */
    private JSpinner myLineSpinner;
    
    /**
     * This is the number triangle spinner.
     */
    private JSpinner myTriangleSpinner;
    
    /**
     * This is add drawing shape button.
     */
    private JButton myAddButton;
    
    /**
     * This is changing background button.
     */
    private JButton myBackGroundChange;
    
    /**
     * this clear the shapes.
     */
    private JButton myClearButton;
    
    /**
     * this save the image on the panel.
     */
    private JButton mySaveButton;
    
    /**
     * this is about button.
     */
    private JButton myAbout;
    
    /**
     * This is the main panel.
     */
    private JPanel myMainPanel;
    
    /**
     * Model1 for spinner1.
     */
    private SpinnerNumberModel myModel1;
    
    /**
     * Model3 for spinner3.
     */
    private SpinnerNumberModel myModel3;
    
    /**
     * Model4 for spinner4.
     */
    private SpinnerNumberModel myModel4;
    
    /**
     * Model2 for spinner2.
     */
    private SpinnerNumberModel myModel2;
    
    /**
     * number of components in the panel.
     */
    private final int myNum = 12;
    
    /**
     * Max number of Shapes selected by spinners.
     */
    private final int myMax = 15;
    
    /**
     * This constructing the main panel with all the buttons , spinners.
     */
    public ArtToolBar() {
        super();
        myMainPanel = new JPanel();
        //myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
        myMainPanel.setLayout(new GridLayout(myNum, 1));
        createSpinners();
        createButtons();
    }
    
    /**
     * Creating all the spinners.
     */
    private void createSpinners() {
        myModel1 = new SpinnerNumberModel(0, 0, myMax, 1);
        myModel2 = new SpinnerNumberModel(0, 0, myMax, 1);
        myModel3 = new SpinnerNumberModel(0, 0, myMax, 1);
        myModel4 = new SpinnerNumberModel(0, 0, myMax, 1);
        
        myRectangleSpinner = new JSpinner(myModel1);
        myEllipseSpinner = new JSpinner(myModel2);
        myLineSpinner = new JSpinner(myModel3);
        myTriangleSpinner = new JSpinner(myModel4);
        
        final JLabel rectangle = new JLabel("Rectangle");
        final JLabel line = new JLabel("Line");
        final JLabel ellipse = new JLabel("Ellipse");
        final JLabel triangle = new JLabel("Triangle");
        
        final JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(2, myMax - myNum + 1));
       // pane.add(Box.createVerticalGlue());
      //  pane.add(Box.createVerticalGlue());
        pane.add(rectangle);
        pane.add(myRectangleSpinner);
        pane.add(ellipse);
        pane.add(myEllipseSpinner);
        pane.add(line);
        pane.add(myLineSpinner);
        pane.add(triangle);
        pane.add(myTriangleSpinner);
        
        myMainPanel.add(pane);
       // myMainPanel.add(Box.createVerticalGlue());
        add(myMainPanel);
        //add(Box.createVerticalGlue());
    }
    
    /**
     * Create all the Buttons and added to main panel.
     */
    private void createButtons() {
        myAddButton = new JButton("Add to Drawing");
        myAddButton.setBackground(Color.LIGHT_GRAY);
        myBackGroundChange = new JButton("Get New Background");
        myBackGroundChange.setBackground(Color.LIGHT_GRAY);
        myClearButton = new JButton("Wipe");
        myClearButton.setBackground(Color.LIGHT_GRAY);
        myClearButton.setBackground(Color.LIGHT_GRAY);
        mySaveButton = new JButton("Save");
        mySaveButton.setBackground(Color.LIGHT_GRAY);
        myAbout = new JButton("About");
        myAbout.setBackground(Color.LIGHT_GRAY);
        
        // set Button size
        myAddButton.setMinimumSize(myBackGroundChange.getMinimumSize());
        myAddButton.setMaximumSize(myBackGroundChange.getMaximumSize());
        myClearButton.setMinimumSize(myBackGroundChange.getMinimumSize());
        myClearButton.setMaximumSize(myBackGroundChange.getMaximumSize());
        mySaveButton.setMinimumSize(myBackGroundChange.getMinimumSize());
        mySaveButton.setMaximumSize(myBackGroundChange.getMaximumSize());
        myAbout.setMinimumSize(myBackGroundChange.getMinimumSize());
        myAbout.setMaximumSize(myBackGroundChange.getMaximumSize());
       // myAbout.setal
        myMainPanel.add(myAddButton);
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(myBackGroundChange);
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(myClearButton);
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(mySaveButton);
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(myAbout);
        //myMainPanel.add(Box.createVerticalGlue());
        
        add(myMainPanel);
    }

    /**
     * The rectangle spinner.
     * 
     * @return the myRectangleSpinner is Spinner
     */
    public JSpinner getMyRectangleSpinner() {
        return myRectangleSpinner;
    }

    /**
     * Get Ellipse spinner.
     * @return the myEllipseSpinner is spinner
     */
    public JSpinner getMyEllipseSpinner() {
        return myEllipseSpinner;
    }

    /**
     * Get Line Spinner.
     * @return the myLineSpinner is spinner
     */
    public JSpinner getMyLineSpinner() {
        return myLineSpinner;
    }

    /**
     * get Triangle spinner.
     * @return the myTriangleSpinner is spinner
     */
    public JSpinner getMyTriangleSpinner() {
        return myTriangleSpinner;
    }

    /**
     * GEt the add draw button.
     * @return the myAddButton is JButton
     */
    public JButton getMyAddButton() {
        return myAddButton;
    }

    /**
     * get background change button.
     * @return the myBackGroundChange is JButton
     */
    public JButton getMyBackGroundChange() {
        return myBackGroundChange;
    }

    /**
     * Get wipe button.
     * @return the myClearButton is JButton
     */
    public JButton getMyClearButton() {
        return myClearButton;
    }

    /**
     * Get save button.
     * @return the mySaveButton is JButton
     */
    public JButton getMySaveButton() {
        return mySaveButton;
    }

    /**
     * Get about button.
     * @return the myAbout is JButton
     */
    public JButton getMyAbout() {
        return myAbout;
    }

    /**
     * get Rectangle model.
     * @return the myModel1 is spinner model number
     */
    public SpinnerNumberModel getMyModel1() {
        return myModel1;
    }

    /**
     * get line model.
     * @return the myModel3 is spinner model number
     */
    public SpinnerNumberModel getMyModel3() {
        return myModel3;
    }

    /**
     * get triangle model.
     * @return the myModel4 is spinner model number
     */
    public SpinnerNumberModel getMyModel4() {
        return myModel4;
    }

    /**
     * get Ellipse model.
     * @return the myModel2 is spinner model number
     */
    public SpinnerNumberModel getMyModel2() {
        return myModel2;
    }
}
