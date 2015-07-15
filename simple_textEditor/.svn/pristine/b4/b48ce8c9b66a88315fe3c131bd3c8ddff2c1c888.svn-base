/*
 * TCSS 305 
 * Winter 2014
 * assignment 5
 */
package five.progpracticum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


/**
 * This representing a Text Editor Menu Bar.
 * 
 * @author Viet Nguyen
 * @version winter 2014
 */
@SuppressWarnings("serial")
public class TextMenuBar extends JMenuBar {
    
    /** The File menu. */
    private final JMenu myFileMenu;
  
    /** The Edit menu. */
    private final JMenu myFortmatMenu;
  
    /** The Help menu. */
    private final JMenu myHelpMenu;
    
    /**
     * This is a new menu item.
     */
    private JMenuItem myNew;
    
    /**
     * This is an Open menu item.
     */
    private JMenuItem myOpen;
    
    /**
     * this is a save menu item.
     */
    private JMenuItem mySave;
    
    /**
     * This is a Print menu Item.
     */
    private JMenuItem myPrint;
    
    /**
     * this is an exit menu Item.
     */
    private JMenuItem myExit;
  
    /** A button group for the menu radio buttons. */
    private  ButtonGroup myGroup;

    /**
     * This is a file chooser.
     */
    private JFileChooser myChooser;
    
    /**
     * This is word wrap check box menu item.
     */
    private JCheckBoxMenuItem myWordWrapBox;
    
    /**
     * This is a font menu.
     */
    private JMenu myFont;
    
    /**
     * This is a Type menu item.
     */
    private JMenuItem myType;
    
    /**
     * This is a Style Menu item.
     */
    private JMenuItem myStyle;
    
    /**
     * This is a Size menu item.
     */
    private JMenuItem mySize;
    
    /**
     * This is an About menu item.
     */
    private JMenuItem myAbout;
    
   // private PaintPanel myPanel;
    
    /**
     * Word Count Menu.
     */
    private JMenu myWordCount;
    
    /**
     * Count all the words.
     */
    private JMenuItem myCount;
    
    /**
     * Count specific words.
     */
    private JMenuItem mySpecificWord;
    /**
     * Construct text menu with its components.
     */
    public TextMenuBar() {
        super();
        myFileMenu = new JMenu("File");
        myFortmatMenu = new JMenu("Fortmat");
        myHelpMenu = new JMenu("Help");
        myGroup = new ButtonGroup();
        myWordCount = new JMenu("Word Count");
        
        createFileMenu();
        createFormatMenu();
        createHelpMenu();
        createWordCountMenu();
    }
    
    /**
     * create File menu items.
     */
    private void createFileMenu() {
        
        myFileMenu.setMnemonic(KeyEvent.VK_F);      
        myFortmatMenu.setMnemonic(KeyEvent.VK_O);
        myHelpMenu.setMnemonic(KeyEvent.VK_H);
        
        
        myNew = new JMenuItem("New");
        
        myOpen = new JMenuItem("Open");
        myChooser = new JFileChooser();
        mySave = new JMenuItem("Save");
        myPrint = new JMenuItem("Print");
        myExit = new JMenuItem("Exit");
        myExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        myExit.addActionListener(new ActionListener() {
            /**
             * exit the system.
             */
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                System.exit(0);
            }             
        });
        myGroup = new ButtonGroup();
        myGroup.add(myNew);
        myGroup.add(myOpen);
        myGroup.add(mySave);
        myGroup.add(myPrint);
        myGroup.add(myExit);

        myFileMenu.add(myNew);
        myFileMenu.add(myOpen);
        myFileMenu.addSeparator();
        myFileMenu.add(mySave);
        myFileMenu.addSeparator();
        myFileMenu.add(myPrint);
        myFileMenu.addSeparator();
        myFileMenu.add(myExit);
        
        add(myFileMenu);
    }
    
    /**
     * create fortmat menu items.
     */
    private void createFormatMenu() {
        myWordWrapBox = new JCheckBoxMenuItem("Word Wrap");
        myFont = new JMenu("Font");
        myType = new JMenuItem("type");
        myStyle = new JMenuItem("style");
        mySize = new JMenuItem("size");
        
        myFont.add(myType);
        myFont.add(myStyle);
        myFont.add(mySize);
        
        myFortmatMenu.add(myWordWrapBox);
        myFortmatMenu.addSeparator();
        myFortmatMenu.add(myFont);
        
        add(myFortmatMenu);
        
    }
    
    /**
     * create help menu item.
     */
    private void createHelpMenu() {
        myAbout = new JMenuItem("About");
        myHelpMenu.add(myAbout);
        
        add(myHelpMenu);
    }
    
    /**
     * create word count menu.
     */
    private void createWordCountMenu() {
        myWordCount.setMnemonic(KeyEvent.VK_W);
        myCount = new JMenuItem("Count All");        
        mySpecificWord = new JMenuItem("Count A Word");
        myWordCount.add(myCount);
        myWordCount.addSeparator();
        myWordCount.add(mySpecificWord);
        add(myWordCount);
    }

    /**
     * Get the file menu.
     * 
     * @return the myFileMenu that representing file menu
     */
    public JMenu getMyFileMenu() {
        return myFileMenu;
    }

    /**
     * Get the format menu.
     * 
     * @return the myFortmatMenu representing the JMenu format
     */
    public JMenu getMyFortmatMenu() {
        return myFortmatMenu;
    }

    /**
     * Get the Help Menu.
     * 
     * @return the myHelpMenu that representing the JMenu Help
     */
    public JMenu getMyHelpMenu() {
        return myHelpMenu;
    }

    /**
     * Get the New JMenuItem.
     * 
     * @return the myNew that representing the JMenu new.
     */
    public JMenuItem getMyNew() {
        return myNew;
    }

    /**
     * get the open JMenuItem.
     * @return the myOpen that representing the JMenuItem open
     */
    public JMenuItem getMyOpen() {
        return myOpen;
    }

    /**
     * get the save JMenuItem.
     * @return the mySave that representing the JMenuItem Save
     */
    public JMenuItem getMySave() {
        return mySave;
    }

    /**
     * get the Print JMenuitem.
     * @return the myPrint that representing the JMenuItem print
     */
    public JMenuItem getMyPrint() {
        return myPrint;
    }

    /**
     * get the exit JMenuItem.
     * @return the myExit that representing the JMenuItem Exit
     */
    public JMenuItem getMyExit() {
        return myExit;
    }

    /**
     * get the group buttons.
     * 
     * @return the myGroup is group of JMenuItems group.
     */
    public ButtonGroup getMyGroup() {
        return myGroup;
    }

    /**
     * get the file chooser.
     * 
     * @return the myChooser is representing file chooser
     */
    public JFileChooser getMyChooser() {
        return myChooser;
    }

    /**
     * get the word wrap box menu. 
     * 
     * @return the myCheckBox is JCheckBoxMenuItem that representing word wrapbox.
     */
    public JCheckBoxMenuItem getMyWordWrapBox() {
        return myWordWrapBox;
    }

    /**
     * get the font.
     * @return the myFont is font of text area.
     */
    public JMenu getMyFont() {
        return myFont;
    }

    /**
     * get Type of font.
     * @return the myType is type of font.
     */
    public JMenuItem getMyType() {
        return myType;
    }

    /**
     * get style of the font.
     * @return the myStyle is style of font
     */
    public JMenuItem getMyStyle() {
        return myStyle;
    }

    /**
     * get size of the font.
     * @return the mySize is size of the font
     */
    public JMenuItem getMySize() {
        return mySize;
    }

    /**
     * get about JMenu item.
     * 
     * @return the myAbout is that representing the JMenuItem about.
     */
    public JMenuItem getMyAbout() {
        return myAbout;
    }
    
    /**
     * get JMenu Item count.
     * @return word count menu item.
     */
    public JMenuItem getMyWordCount() {
        return myCount;
    }
    
    /**
     * get the JMenuItem word.
     * 
     * @return specific word menu item.
     */
    public JMenuItem getMySpecificWordCount() {
        return mySpecificWord;
        
    }
}
