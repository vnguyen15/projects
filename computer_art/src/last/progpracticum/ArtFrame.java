package last.progpracticum;

import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * 
 * @author Viet Nguyen
 * @version 1
 *
 */
@SuppressWarnings("serial")
public class ArtFrame extends JFrame {
    
    /**
     * One 1000 millisecond.
     */
    public static final int KSECONDS = 1000;
    /**
     * Denominator.
     */
    private static final int DENO = 80;
    
    /**
     * Denominator2.
     */
    private static final int DENO2 = 75;
    
    /**
     * Numerator.
     */
    private static final int NUM = 100;
    /**
     * Background picture for about button.
     */
    private static final Icon BGROUND = new ImageIcon("icons/background2.gif");
    
    /**
     * background name.
     */
    private final String myBackground = "Background";
    
    /**
     * Wipe name.
     */
    private final String myWipe = "Wipe";
    
   
    /**
     * Panel that contain drawings.
     */
    private ArtPanel myPanel;
    
    /**
     * Which contains all the buttons and spinners.
     */
    private ArtToolBar myArtToolBar;
    
    /**
     * It contains all the animation buttons.
     */
    private AnimationToolbar myAnimationTool;
    
    /**
     * List of copy ShapeProperties.
     */
    private List<ShapeProperties> myOriginalList;
    
    /**
     * tracking whether play button whether pressed or not.
     */
    private boolean myTrack = true;
    
    /**
     * File chooser for save button.
     */
    private JFileChooser myChooser;
    
    /**
     * timer to fire the event.
     */
    private Timer myTimer;
    
    /**
     * Number of frame / second.
     */
    private int myFrame;
    
    /**
     * Construct the frame with all the components in this frame.
     */
    public ArtFrame() {
        super();
        createToolBars();
        createAnimationTools();
        guiSetup();
    }
    
    /**
     * adding components to frame.
     */
    private void guiSetup() {
        add(myPanel, BorderLayout.CENTER);
        add(myArtToolBar, BorderLayout.WEST);
        add(myAnimationTool, BorderLayout.SOUTH);
        
        final Toolkit kit = Toolkit.getDefaultToolkit();  // getting tool kit object
        final Dimension screenSize = kit.getScreenSize();
        final int screenWidth = screenSize.width; 
        final int screenHeight = screenSize.height;
        setSize(screenWidth * DENO / NUM, screenHeight * DENO / NUM); 
    }
    
    /**
     * creating and adding tool bars to main frame.
     */
    private void createToolBars() {
        myPanel = new ArtPanel();
        myOriginalList = new ArrayList<ShapeProperties>();
        for (int i = 0; i < myPanel.getMyModel().getMyList().size(); i++) {
            try {
                myOriginalList.add(myPanel.getMyModel().getMyList().get(i).clone());
            } catch (final CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
        }
        
        myArtToolBar = new ArtToolBar();
        final AddDrawListener drawListener = new AddDrawListener();    
        myArtToolBar.getMyAddButton().addActionListener(drawListener);
        
        final ButtonListener bListener1 = new ButtonListener(myBackground);
        myArtToolBar.getMyBackGroundChange().addActionListener(bListener1);
        final ButtonListener bListener2 = new ButtonListener(myWipe);
        myArtToolBar.getMyClearButton().addActionListener(bListener2);
        
        final FileListener save = new FileListener();
        myArtToolBar.getMySaveButton().addActionListener(save);
        final ButtonListener bListener3 = new ButtonListener("About");
        myArtToolBar.getMyAbout().addActionListener(bListener3);
        
        myPanel.getMyPopupMenu().getMyWipe().addActionListener(bListener2);
        myPanel.getMyPopupMenu().getMySave().addActionListener(save);
        myPanel.getMyPopupMenu().getMyAbout().addActionListener(bListener3);
    }
    
    /**
     * create animation buttons and add to main frame.
     */
    private void createAnimationTools() {
        myAnimationTool = new AnimationToolbar();
        myAnimationTool.getMySlider().addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent anEvent) {
                myFrame = KSECONDS / myAnimationTool.getMySlider().getValue();
                myAnimationTool.getMyLabel().setText("Frame/s: " 
                        + myAnimationTool.getMySlider().getValue());
            }
        
        });
        
        final MoveListener mListener = new MoveListener(); 
        myAnimationTool.getMyStep().addActionListener(mListener);
        final ShapeMoveListener sListener = new ShapeMoveListener();
        myFrame = KSECONDS / myAnimationTool.getMySlider().getValue();
        
        myTimer = new Timer(myFrame, sListener);
        
        final PlayListener play = new PlayListener();
        myAnimationTool.getMyPlay().addActionListener(play);
        myAnimationTool.getMyPause().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent anEvent) { 
                //myTimer = new Timer(2, sListener);
                myTimer.stop();
            } 
        });
        
        // stop button and its listener
        myAnimationTool.getMyStop().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent anEvent) { 

                myTimer.stop();
            } 
        });
        myAnimationTool.getMyMusic().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent anEvent) { 

                playMusic();
            } 
        });
    }
    

    /**
     * This method start and playing background music.
     *
     */
    private void playMusic() {
        
        final File soundFile = new File("icons/letitsnow.mid");
        AudioInputStream sound;
        try {
            sound = AudioSystem.getAudioInputStream(soundFile);
            final DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip;
            try {
                clip = (Clip) AudioSystem.getLine(info);
                try {
                    clip.open(sound);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                // exit frame when music is done
                
                /*clip.addLineListener(new LineListener() {
                    public void update(LineEvent anEvent) {
                        if (anEvent.getType() == LineEvent.Type.STOP) {
                            anEvent.getLine().close();
                            System.exit(0);
                        }
                    }
                });*/
                // play the sound clip
                clip.start();   
            } catch (final LineUnavailableException e1) {
                e1.printStackTrace();
            }
        } catch (final UnsupportedAudioFileException e) {

            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
  
    }
    /**
     * Play button listener that moves the shapes.
     * @author Viet Nguyen
     * 
     */
    public class PlayListener implements ActionListener {
        
        /**
         * Start Playing music and moving shapes.
         * @param anEvent is the event firing by Button.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            if (myTrack) {
                
                myTimer.setDelay(myFrame);
                myTimer.start();
                
               // playMusic();
            } else {
               //  List<ShapeProperties> s = myPanel.getMyModel().getMyList();
               // s = myOriginalList;
               // myPanel.getMyModel().getMyList().clear();
                for (int i = 0; i < myOriginalList.size(); i++) {
                    ShapeProperties shape;
                    try {
                        shape = myOriginalList.get(i).clone();
                        myPanel.getMyModel().getMyList().add(shape);
                    } catch (final CloneNotSupportedException e1) {
                        e1.printStackTrace();
                    }
                }
                
              //  playMusic();
            }
        } 
    } // end player listener
    
    /**
     * This class create listener for Add draw button.
     * 
     * @author Viet Nguyen
     *
     */
    
    public class AddDrawListener implements ActionListener {
        
        /**
         * This will add all the shapes to drawing panel.
         * 
         * @param anEvent is event fired from Add draw button.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            final int numbRect = myArtToolBar.getMyModel1().getNumber().intValue();
            final int numbEll = myArtToolBar.getMyModel2().getNumber().intValue();
            final int numbLin = myArtToolBar.getMyModel3().getNumber().intValue();
            final int numbTri = myArtToolBar.getMyModel4().getNumber().intValue();
            
            final ArtModel model = new ArtModel(numbRect, numbEll, numbLin, numbTri);
            final List<ShapeProperties> shapes = model.getMyList();
            
            for (int i = 0; i < shapes.size(); i++) {
                myPanel.getMyModel().updateMylist(shapes.get(i));
            }
            repaint();
        }  
    } // end add draw listener.
    
    /**
     * This is creating button listener for other buttons.
     * 
     * @author Viet Nguyen
     *
     */
    public class ButtonListener implements ActionListener {
        
        /**
         * name pass in for different button.
         */
        private String myName;
        
        /**
         * Assign name to specific button.
         * 
         * @param aName is string that representing name of button.
         */
        public ButtonListener(final String aName) {
            myName = aName;
        }
        
        /**
         * This create action listener base on different button.
         * 
         * @param anEvent firing from each button.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
          //  final Object source = anEvent.getSource();
            
            if (myBackground.equals(myName)) {
                final Random rdn = new Random();
                myPanel.setMyColor1(new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255)));
                myPanel.setMyColor2(new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255)));
                repaint();  
            } else  if (myWipe.equals(myName)) {
                myPanel.getMyModel().getMyList().clear();
                repaint();
            } else {
                JOptionPane.showMessageDialog(null,
                                              "Final Project \nComputer Art \nVersion 1.0",
                            "Viet Nguyen", JOptionPane.PLAIN_MESSAGE, BGROUND);
            }
        }  
    }
    
    /**
     * This is File Save listener.
     * 
     * @author Viet NGuyen
     *
     */
    private class FileListener implements ActionListener {
        /**
         * extension.
         */
        private final String myDot = ".";
        /**
         * It saves the image file from the panel.
         * @param anEvent event firing from the save button.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            if (myChooser == null) {
                myChooser = new JFileChooser();
                myChooser.setCurrentDirectory(new File(myDot));
            }
            
            int choice;
                
            myChooser.setFileFilter(new FileNameExtensionFilter("JPG (*.jpg)", "jpg"));
            myChooser.setFileFilter(new FileNameExtensionFilter("GIF (*.gif)", "gif"));
            myChooser.setFileFilter(new FileNameExtensionFilter("BMP (*.bmp)", "bmp"));
            myChooser.setFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
            choice = myChooser.showSaveDialog(myPanel);
            
            File selected;
            if (choice == JFileChooser.APPROVE_OPTION) {
                selected = myChooser.getSelectedFile();

                final String extension = myChooser.getFileFilter().
                        getDescription().substring(0, 3);
                saveImage(selected, extension);
            }
        } // ending action performed
        
        /**
         * This save the image from the frame to an image format file.
         * 
         * @param aFile is aFile to pass in
         * @param anExtension is String representing extension of image file
         */
        private void saveImage(File aFile, String anExtension) {
            final File newF = new File(aFile.getPath() + myDot + anExtension);
            final BufferedImage bi = new BufferedImage(myPanel.getWidth()
                                      , myPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            final Graphics2D pen = bi.createGraphics(); 
            myPanel.paintAll(pen); // will grab everything to the panel
            
            try {
                ImageIO.write(bi, anExtension, newF);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This create move button listener.
     * 
     * @author Viet Nguyen
     *
     */
    public class MoveListener implements ActionListener {
        
        /**
         * This moving the shapes in the frame random direction.
         * 
         * @param anEvent is fired from the play button
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            final int size = myPanel.getMyModel().getMyList().size();
            for (int i = 0; i < size; i++) {
                myPanel.getMyModel().getMyList().get(i).move();
            }
            repaint();
        }
    }

    /**
     * A class that listens for timer events and moves the ball, checking for the
     * window boundaries and changing direction as appropriate.
     */
    private class ShapeMoveListener implements ActionListener {
      /**
       * Moves the ball appropriately.
       * 
       * @param anEvent The event triggering the action.
       */
        public void actionPerformed(final ActionEvent anEvent) {
            
            final int size = myPanel.getMyModel().getMyList().size();
            for (int i = 0; i < size; i++) {
                final ShapeProperties s =  myPanel.getMyModel().
                        getMyList().get(i);

                if (s.getMyType() == 'r' || s.getMyType() == 'e') {    
                    updateHorizontalMove(s);
                    updateVerticalMove(s);
                    s.move();
                } else if (s.getMyType() == 'l') {
                    updateHorizontalLine(s);
                    updateVerticalLine(s);
                    s.move();
                } else { //if (s.getMyType() == 'p') {
                    updateHorizontalTriangle(s);
                    updateVerticalTriangle(s);
                    s.move();
                }
            }
            repaint();
        }
      
        /**
         * Updates the horizontal move to keep the shape on screen.
         * 
         * @param aShape the BouncingShape to update
         */
        private void updateHorizontalMove(final ShapeProperties aShape) {
            final RectangularShape s = (RectangularShape) aShape.getMyShape();
            if (s.getFrame().getMinX() <= 0) {
                aShape.setHorizontalDirection(1);
            }
            if (s.getMaxX() >= getWidth() * DENO2 / NUM) {
                aShape.setHorizontalDirection(-1);
            }
        }
      
      /**
       * Updates the vertical move to keep the shape on screen.
       * 
       * @param aShape the BouncingShape to update
       */
        private void updateVerticalMove(final ShapeProperties aShape) {
            final RectangularShape s = (RectangularShape) aShape.getMyShape();
            if (s.getFrame().getMinY() <= 0) {
                aShape.setVerticalDirection(1);
            }
            if (s.getFrame().getMaxY() >= getHeight() * (NUM - 2 - 1) / NUM) {
                aShape.setVerticalDirection(-1);
            }
        }
      
      // line 
      
      /**
       * Updates the horizontal move to keep the shape on screen.
       * 
       * @param aShape the BouncingShape to update
       */
        private void updateHorizontalLine(final ShapeProperties aShape) {
            final Line2D s = (Line2D) aShape.getMyShape();
            if (s.getX1() <= 0 || s.getX2() <= 0) {
                aShape.setHorizontalDirection(1);
            }
            if (s.getX1() >= getWidth() * DENO2 / NUM
                    || s.getX2() >= getWidth() * DENO2 / NUM) {
                aShape.setHorizontalDirection(-1);
            }
        }
      
      /**
       * Updates the vertical move to keep the shape on screen.
       * 
       * @param aShape the BouncingShape to update
       */
        private void updateVerticalLine(final ShapeProperties aShape) {
            final Line2D s = (Line2D) aShape.getMyShape();
            if (s.getY1() <= 0 || s.getY2() <= 0) {
                aShape.setVerticalDirection(1);
            }
            if (s.getY1() >= getWidth() * DENO2 / NUM
                    || s.getY2() >= getWidth() * DENO2 / NUM) {
                aShape.setVerticalDirection(-1);
            }
        }
      
      // Triangle coordinates.
      /**
       * Updates the horizontal move to keep the shape on screen.
       * 
       * @param aShape the BouncingShape to update
       */
        private void updateHorizontalTriangle(final ShapeProperties aShape) {
            final int[] x = aShape.getMyX();
          
            if (x[0] <= 0 || x[1] <= 0 || x[2] <= 0) {
                aShape.setHorizontalDirection(1);
            }
            if (x[0] >= getWidth() * DENO2 / NUM || x[1] >= getWidth() * DENO2 / NUM
                  || x[2] >= getWidth() * DENO2 / NUM) {
                aShape.setHorizontalDirection(-1);
            }
        }
      
      /**
       * Updates the vertical move to keep the shape on screen.
       * 
       * @param aShape the BouncingShape to update
       */
        private void updateVerticalTriangle(final ShapeProperties aShape) {
            final int[] y = aShape.getMyY();
          
            if (y[0] <= 0 || y[1] <= 0 || y[2] <= 0) {
                aShape.setVerticalDirection(1);
            }
            if (y[0] >= getWidth() * DENO2 / NUM || y[1] >= getWidth() * DENO2 / NUM
                  || y[2] >= getWidth() * DENO2 / NUM) {
                aShape.setVerticalDirection(-1);
            }
        }
    } // end of BBMoveListener

}
