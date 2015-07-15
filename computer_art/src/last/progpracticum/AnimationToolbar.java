package last.progpracticum;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;


/**
 * This representing animation play, pause, stop, and step buttons.
 * @author Viet Nguyen
 * @version 1
 */
@SuppressWarnings("serial")
public class AnimationToolbar extends JToolBar {
    /**
     * This is play icon.
     */
    private static final Icon PLAY = new ImageIcon("icons/play.png");
    
    /**
     * This is pause icon.
     */
    private static final Icon PAUSE = new ImageIcon("icons/pause.png");
    
    /**
     * This is stop icon.
     */
    private static final Icon STOP = new ImageIcon("icons/stop.png");
    
    /**
     * This is step icon.
     */
    private static final Icon STEP = new ImageIcon("icons/step-forward.png");
    
    /**
     * Note icon.
     */
    private static final Icon NOTE = new ImageIcon("icons/note.png");
    
    /**
     * Slider initial value.
     */
    private static final int VAL1 = 5;
    
    /**
     * Slider max value.
     */
    private static final int VAL2 = 35;
    
    /**
     * Slider current value.
     */
    private static final int VAL3 = 24;
    
    
    /**
     * This is play button.
     */
    private JButton myPlay;
    
    /**
     * This is pause button.
     */
    private JButton myPause;
    
    /**
     * This is stop button.
     */
    private JButton myStop;
    
    /**
     * This is step button.
     */
    private JButton myStep;
    
    /**
     * This is a frame slider.
     */
    private JSlider mySlider;
    
    /**
     * This is music button.
     */
    private JButton myMusic;
    
    /**
     * This is frame label.
     */
    private JLabel myLabel;
    
    /**
     * This constructing the animation buttons.
     */
    public AnimationToolbar() {
        super();
        setupButtons();
    }
    
    /**
     * This is create and add button to the animation tool bar.
     */
    private void setupButtons() {
        myPlay = new JButton(PLAY);
        myPause = new JButton(PAUSE);
        myStop = new JButton(STOP);
        myStep = new JButton(STEP);
        myMusic = new JButton(NOTE);
        mySlider = new JSlider(VAL1, VAL2, VAL3);
        
        mySlider.setMajorTickSpacing(VAL1);
        mySlider.setMinorTickSpacing(1);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        myLabel = new JLabel(" Frame/s: " + mySlider.getValue());
        
        /*myMusic.addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent anEvent) {
                playMusic();
            }
        
        });*/
        add(Box.createHorizontalGlue());
        add(Box.createHorizontalGlue());
        add(myPlay);
        add(myPause);
        add(myStop);
        add(myStep);
        add(myLabel);
        add(mySlider);
        add(myMusic);
        add(Box.createHorizontalGlue());
       // add(Box.createHorizontalGlue());
    }
    /**
     * This return play button.
     * @return the myPlay
     */
    public JButton getMyPlay() {
        return myPlay;
    }

    /**
     * This return pause button.
     * @return the myPause
     */
    public JButton getMyPause() {
        return myPause;
    }

    /**
     * This return stop button.
     * @return the myStop
     */
    public JButton getMyStop() {
        return myStop;
    }

    /**
     * This return step button.
     * @return the myStep
     */
    public JButton getMyStep() {
        return myStep;
    }

    /**
     * This return slider.
     * @return the mySlider
     */
    public JSlider getMySlider() {
        return mySlider;
    }

    /**
     * This return frame number.
     * @return the myLabel
     */
    public JLabel getMyLabel() {
        return myLabel;
    }

    /**
     * @return the myMusic
     */
    public JButton getMyMusic() {
        return myMusic;
    }
    
}
