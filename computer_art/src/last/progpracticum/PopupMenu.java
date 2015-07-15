package last.progpracticum;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * This is a pop up menu that contain Wipe, Save, and About.
 * @author Viet Nguyen
 * @version 1
 */
@SuppressWarnings("serial")
public class PopupMenu extends JPopupMenu {
    /**
     * This is wipe icon.
     */
    private final Icon myWipeIcon = new ImageIcon("icons/wipe1.png");
    
    /**
     * this is save icon.
     */
    private final Icon mySaveIcon = new ImageIcon("icons/save.png");
    
    /**
     * This is about icon.
     */
    private final Icon myAboutIcon = new ImageIcon("icons/about.png");
    /**
     * menu item wipe.
     */
    private JMenuItem myWipe;
    
    /**
     * menu item Save.
     */
    private JMenuItem mySave;
    
    /**
     * menu item About.
     */
    private JMenuItem myAbout;
    
    /**
     * This constructing the pop up menu with Wipe, Save, and About.
     */
    public PopupMenu() {
        myWipe = new JMenuItem("Wipe");
        mySave = new JMenuItem("Save");
        myAbout = new JMenuItem("About");
        myWipe.setIcon(myWipeIcon);
        mySave.setIcon(mySaveIcon);
        myAbout.setIcon(myAboutIcon);
        
        add(myWipe);
        add(mySave);
        add(myAbout);
    }

    /**
     * This returns Wipe JMenu item.
     * @return the myWipe
     */
    public JMenuItem getMyWipe() {
        return myWipe;
    }

    /**
     * This return JMenu save item.
     * @return the mySave
     */
    public JMenuItem getMySave() {
        return mySave;
    }

    /**
     * This return the about JMenu item.
     * @return the myAbout
     */
    public JMenuItem getMyAbout() {
        return myAbout;
    }

}
