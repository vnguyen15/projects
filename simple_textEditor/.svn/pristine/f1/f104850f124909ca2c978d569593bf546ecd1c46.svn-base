/*
 * TCSS 305 
 * Winter 2014
 * assignment 5
 */
package five.progpracticum;

import java.awt.BorderLayout;   
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This represents a Text Editor program.
 * 
 * @author Viet Nguyen
 * @version 1
 *
 */
public class TextEditorGUI {
    /**
     * Frame size Denominator.
     */
    private static final int DENO = 85;
    
    /**
     * Frame size nominator.
     */
    private static final int NOMI = 100;
    /**
     * List of Sizes.
     */
    private static final String[] FONT_SIZE = {"8", "10", "12", "14", "16", "18", "20", "24",
        "28", "32", "36", "40", "44", "48", "52", "58", "64", "70", "76"};
    /**
     * List of Font Style.
     */
    private static final String[] FONT_STYLE = {"Bold", "Italic", "Plain"};
    
    /**
     * Start Type of font.
     */
    private static final String START_TYPE = "Serif";
    
    /**
     * Start font size.
     */
    private static final int START_SIZE = 12;
    /**
     * Name of the New menu.
     */
    private static final String NEW = "New";
    
    /**
     * Name of the Type menu.
     */
    private static final String TYPE = "Type";
    
    /**
     * Name of the Style menu.
     */
    private static final String STYLE = "Style";
    
    /**
     * Name of the Size menu.
     */
    private static final String SIZE = "Size"; 
    
    /**
     * This is list of Font type.
     */
    private String[] myFontTypes;
    
    /**
     * This a main frame.
     */
    private JFrame myMainFrame;
    
    /**
     * This representing MenuBar.
     */
    private TextMenuBar myMenuBar; 
    
    /**
     * This representing Text area.
     */
    private JTextArea myTextArea;
    
    /**
     * A scroll pane that contains text area.
     */
    private JScrollPane myScrollPane;
    
    /**
     * Tracking the current type of font.
     */
    private String myCurrentType;
    
    /**
     * Tracking the current Style of font.
     */
    private int myCurrentStyle;
    
    /**
     * this is a current size of font.
     */
    private int myCurrentSize;
    
    /**
     * this is current Style position.
     */
    private int myStylePosition;
    
    /**
     * this is Type position.
     */
    private int myTypePosition;
    
    /**
     * This is size position.
     */
    private int mySizePosition;
    
    /**
     * This is file Chooser.
     */
    private JFileChooser myChooser;
    
    /**
     * Constructing main frame with its menus and text area.
     * 
     * @custompost main frame construct with menu bars and JText area.
     */
    public TextEditorGUI() {
        
        myMainFrame = new JFrame("Viet's Simple Text Editor");
       // myMainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        myMenuBar = new TextMenuBar();
        myMainFrame.setJMenuBar(myMenuBar);
        
        createMenus();
    }
    
    /**
     * Creating menu with it components then add to main frame.
     */
    private void createMenus() {
        
        final NewListener newMenu = new NewListener(NEW);
        myMenuBar.getMyNew().addActionListener(newMenu);
        
        final FileListener open = new FileListener();
        final FileListener save = new FileListener();
        myMenuBar.getMyOpen().addActionListener(open);
        myMenuBar.getMySave().addActionListener(save);
        final AboutListener about = new AboutListener();
        myMenuBar.getMyAbout().addActionListener(about);
        
        final PrintListener print = new PrintListener();
        myMenuBar.getMyPrint().addActionListener(print);
        myTextArea = new JTextArea();
        
        final WordWrapListener wordWrapListener = new WordWrapListener();
        myMenuBar.getMyWordWrapBox().addActionListener(wordWrapListener);
        
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        myFontTypes = ge.getAvailableFontFamilyNames();
    
        myCurrentType = START_TYPE;
        myCurrentStyle = Font.PLAIN;
        myCurrentSize = START_SIZE;
        mySizePosition = 2;
        
        final FontListener fontType = new FontListener(myFontTypes, TYPE);
        final FontListener fontStyle = new FontListener(FONT_STYLE, STYLE);
        final FontListener fontSize = new FontListener(FONT_SIZE, SIZE);
        myMenuBar.getMyType().addActionListener(fontType);
        myMenuBar.getMyStyle().addActionListener(fontStyle);
        myMenuBar.getMySize().addActionListener(fontSize);
        
        // Extra Credit: words/lines count, specific word count.
        final WordCountListener wordCountListner = new WordCountListener();
        myMenuBar.getMyWordCount().addActionListener(wordCountListner);
        myMenuBar.getMySpecificWordCount().addActionListener(wordCountListner);
        
        myTextArea.setEditable(true);
        
        myScrollPane = new JScrollPane(myTextArea);

        
        myMainFrame.add(myScrollPane, BorderLayout.CENTER); 
        

        // main frame set up
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Toolkit kit = Toolkit.getDefaultToolkit();  // getting tool kit object
        final Dimension screenSize = kit.getScreenSize();
        final int screenWidth = screenSize.width; 
        final int screenHeight = screenSize.height;
        myMainFrame.setSize(screenWidth * DENO / NOMI, screenHeight * DENO / NOMI); 
        myMainFrame.setLocationRelativeTo(null);
        myMainFrame.setVisible(true);
    }
    
        
    /**
     * This is Font listener that changing font type, style, or size.
     * 
     * @author Viet Nguyen
     *
     */
    public class FontListener implements ActionListener {
       
        /**
         * List of Font styles, Types, or Sizes.
         */
        private String[] myFont;
        
        /**
         * The description of Font argument pass in.
         */
        private String myDescription;
        
        /**
         * Constructing the font listener base on the description of font.
         * @param aFont either type, sizes, or styles
         * @param aDescription name of type, style, or size
         */
        public FontListener(final String[] aFont, final String aDescription) {
            myFont = aFont;
            myDescription = aDescription;
        }
        
        /**
         * This changing the font style, size , and type.
         * 
         * @param anEvent is event firing from the source.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            
            if (STYLE.equals(myDescription)) {
                final String input = (String) JOptionPane.
                        showInputDialog(null, "Select Font Style", "Font Style", 
                         JOptionPane.PLAIN_MESSAGE, new ImageIcon(),
                         myFont, myFont[myStylePosition]);
                if (input != null) {
                    if (myFont[0].equals(input)) {
                        final int bold = Font.BOLD;
                        myTextArea.setFont(new Font(myCurrentType, bold, myCurrentSize));
                        myCurrentStyle = bold;
                        myStylePosition = 1;
                    
                    } else if (myFont[1].equals(input)) {
                        final int italic = Font.ITALIC;
                        myTextArea.setFont(new Font(myCurrentType, italic, myCurrentSize));
                        myCurrentStyle = italic;
                        myStylePosition = 2;
                    } else if (myFont[2].equals(input)) {
                        final int plain = Font.PLAIN;
                        myTextArea.setFont(new Font(myCurrentType, plain, myCurrentSize));
                        myCurrentStyle = plain;
                        myStylePosition = 2 + 1;
                    }
                }
         
            } else if (SIZE.equals(myDescription)) {
                final String input = (String) JOptionPane.
                        showInputDialog(null, "Select Font Size",
                       "Font Size", JOptionPane.PLAIN_MESSAGE, new ImageIcon(), 
                       myFont, myFont[mySizePosition]);
                
                if (input != null) {
                    int i = 0;
                    while (!input.equals(myFont[i])) {
                        i++;
                    }
                    myCurrentSize = Integer.parseInt(myFont[i]);
                    myTextArea.setFont(new Font(myCurrentType, myCurrentStyle, myCurrentSize));
                    mySizePosition = i;
                }
                
            } else if (TYPE.equals(myDescription)) {
                final String input = (String) JOptionPane.
                        showInputDialog(null, "Select Font Type",
                        "Font Type", JOptionPane.PLAIN_MESSAGE, new ImageIcon(), 
                        myFont, myFont[myTypePosition]);
                if (input != null) {
                    int i = 0;
                    while (!input.equals(myFont[i])) {
                        i++;
                    }
                    myCurrentType = myFont[i];
                    myTextArea.setFont(new Font(myCurrentType, myCurrentStyle, myCurrentSize));
                    myTypePosition = i;
                }
            }
        }
        
    } // end FontListener
    
    /**
     * This is Word Wrap Listener.
     * @author V
     *
     */
    public class WordWrapListener implements ActionListener {
        
        /**
         * This wrap all the lines in the text area.
         * @param anEvent is event from source
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            
            if (myMenuBar.getMyWordWrapBox().isSelected()) {
                myTextArea.setLineWrap(true);
                myTextArea.setWrapStyleWord(true);
            } else {
                myTextArea.setLineWrap(false);
            }
        }
    }
    
    /**
     * This is New Menu listener.
     * @author V
     *
     */
    public class NewListener implements ActionListener {
        /**
         * name of new menu.
         */
        private String myName;
        
        /**
         * construct an initial new listener.
         * @param aName String representing name of new menu.
         */
        public NewListener(final String aName) {
            myName = aName;
        }
        /**
         * This clear the text area and reset to default setting of the font.
         * @param anEvent is an event from source.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            
            if (NEW.equals(myName)) {
                myTextArea.setText(""); // clear TextArea
                // reset font
                myCurrentType = START_TYPE;
                myCurrentStyle = Font.PLAIN;
                myCurrentSize = START_SIZE;
                myTypePosition = 0;
                myStylePosition = 0;
                mySizePosition = 2;
                myTextArea.setFont(new Font(myCurrentType, myCurrentStyle, myCurrentSize));
            }
        }
    } // end FileListener
    
    /**
     * This representing File Menu item listener.
     * @author V
     *
     */
    private class FileListener implements ActionListener {
        /**
         * This handling file open, save.
         * 
         * @param anEvent is event from source.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            if (myChooser == null) {
                myChooser = new JFileChooser();
                myChooser.setCurrentDirectory(new File("."));
            }
            final Object source = anEvent.getSource();
            final String extension = "txt";
            int choice;
            if (source == myMenuBar.getMyOpen()) {
                myChooser.setFileFilter(new FileNameExtensionFilter("Text Files", extension));
                choice = myChooser.showOpenDialog(null);
            } else {
                myChooser.setFileFilter(new FileNameExtensionFilter("TXT (*.txt)", extension));
                choice = myChooser.showSaveDialog(myTextArea);
            }
            File selected;
            if (choice == JFileChooser.APPROVE_OPTION) {
                selected = myChooser.getSelectedFile();
                if (selected == null) {
                    return;
                }
                if (source == myMenuBar.getMyOpen()) {
                    loadText(selected);
                } else {
                    saveText(selected);
                }
            }
        }
        
        /**
         * This method loading the file text to Text area.
         * @param aFile text File.
         */
        private void loadText(File aFile) {
           // FileReader file = (FileReader) aFile;
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(aFile.getPath()));
                try {
                    myTextArea.read(br, null);
                    br.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            } catch (final FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        
        /**
         * This saving the text area into a text file.
         * @param aFile text saved file.
         */
        private void saveText(File aFile) {
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(aFile.getPath()));
                myTextArea.write(bw);
                bw.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } // end of savText method      
    }
    
    /**
     * This representing print listener.
     * @author V
     *
     */
    public class PrintListener implements ActionListener {
        
        /**
         * This print the text area by connect it to the printer setting.
         * 
         * @param anArg is event from the source.
         */
        @Override
        public void actionPerformed(ActionEvent anArg) {
            try {
                myTextArea.print();
            } catch (final PrinterException e) {
                e.printStackTrace();
            } 
        }
    }
    
    /**
     * This representing about listener menu that shows information.
     * @author V
     *
     */
    public class AboutListener implements ActionListener {
        
        /**
         * This is showing the information about this program text editor.
         * 
         * @param anEvent is event from the source.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            JOptionPane.showMessageDialog(null, "Simple Text Editor \nVersion 1.0"
                                           , "Text Editor", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    /**
     * This representing Word Count listener.
     * @author V
     *
     */
    public class WordCountListener implements ActionListener {
        
        /**
         * This count either the words in the area or a certain word from user input.
         * 
         * @param anEvent is event from source.
         */
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            final Object source = anEvent.getSource();
            final List<String> words = new ArrayList<String>();
            Scanner wordScan;
            wordScan = new Scanner(myTextArea.getText());
            
            while (wordScan.hasNextLine()) {
                final Scanner line = new Scanner(wordScan.nextLine());
                while (line.hasNext()) {
                    words.add(line.next());
                }
                line.close();
            }
            wordScan.close();
            
            
            final int wordCount = words.size();
            final String title = "Count All Words";
            final String output = "Total Words in TextArea: " + wordCount 
                    + "\n";

            final JMenuItem countAllItem = (JMenuItem) source;
            if ("Count All".equals(countAllItem.getText())) {
                JOptionPane.showMessageDialog(null, output, title , JOptionPane.PLAIN_MESSAGE);
            } else {
                final String title1 = "Count A Word";
                final String prompt = "Enter a valid word to count(no space)";
                final String input = (String) JOptionPane.
                        showInputDialog(null, null,
                       title1 , JOptionPane.PLAIN_MESSAGE, new ImageIcon(), 
                       null, prompt);
                if (input != null && !input.equals(prompt) && !" ".equals(input)) {
                    int count = 0;
                    for (int i = 0; i < words.size(); i++) {
                        if (input.toLowerCase().equals(words.get(i).toLowerCase())) {
                            count++;
                        } 
                    }
                    final String specWords = "Number of \"" + input + "\" words:"
                                + "  " + count;
                    JOptionPane.showMessageDialog(null,
                                                  specWords,
                                                  "Specific Word Count",
                                                  JOptionPane.PLAIN_MESSAGE); 
                }
            }
        } // end Action method
        
    } // end of WordCountListener
}

