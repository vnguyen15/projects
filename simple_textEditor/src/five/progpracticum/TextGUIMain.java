
/*
 * TCSS 305 
 * Winter 2014
 * assignment 5
 */

package five.progpracticum;

    import java.awt.EventQueue;

/**
 * Start Menu Bar and Text Panel.
 * 
 * @author Viet Nguyen
 * @version Winter 2014
 */
public final class TextGUIMain {
      
      /**
       * Private constructor to inhibit instantiation.
       */
    private TextGUIMain() {
        throw new IllegalStateException();
    }

      /**
       * Start point for the program.
       * 
       * @param aRgs command line arguments - ignored
       */
    public static void main(final String... aRgs) {

        EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new TextEditorGUI(); // create the graphical user interface
                }
            });
    }
}
