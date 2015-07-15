/**
 * 
 */
package three.progpracticum;

import java.awt.BorderLayout;     
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This represents an ice cream order system.
 * 
 * @author Viet Nguyen
 * @version 1
 *
 */
public class IceCream extends JFrame {
    
    /**
     * Ice cream store font size.
     */
    private static final int STORE_FONTSIZE = 20;
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Background Color for the ice cream store.
     */
    private static final int BACKGROUND_COLOR = 0xadd8e6;
    
    /**
     * Preselected Price.
     */
    private static final double START_PRICE = 8.98;
    
    /**
     * Ice Cream image file.
     */
    private static final Icon ICE_CREAM = new ImageIcon("source/creamCup.gif");
    
    /**
     * String decimal format for final price.
     */
    private static final String STRING_FORMAT = "$%.2f";
    
    
    /**
     * This panel contains combo ice cream components.
     */
    private JPanel myPanelWest;

    /**
     * Panel that contains all the cones buttons.
     */
    private ConePanel myConePanel;
    
    /**
     * Panel that contains radio buttons.
     */
    private JPanel myRadioPanel;
    
    /**
     * Box in the center of the frame store.
     */
    private JPanel myCenterBox;
    
    /**
     * Panel that contains combo of ice cream.
     */
    private JPanel myComboPanel;
    
    /**
     * Panel at the south of the frame contains display and order.
     */
    private JPanel mySouthPanel;
    
    /**
     * East panel that contains Check boxes components.
     */
    private JPanel myPanelEast;
     
    /**
     * This represent Ice cream Size.
     */
    private ComboBox myComboBox;
    
    /**
     * A box that contains all the Flavor.
     */
    private FlavorBox myFlavorBox;
    
    /**
     * A box that contain all the Mix-In.
     */
    private MixInBox myMixInBox;
    
    /**
     * This tracks the price from users.
     */
    private double myComboPriceTrack;
    
    /**
     * Cone Price track from user.
     */
    private double myConePriceTrack;
    
    /**
     * This tracks the price from users.
     */
    private double myFlavorPriceTrack;
    
    /**
     * Total Price in double.
     */
    private double myTotalPrice;
    
    /**
     * Display final Price.
     */
    private JLabel myPrice;
    
    /**
     * DemoFrame constructor that creates a GUI.
     * @custom.post GUI with all components created
     */
    public IceCream() {
        myTotalPrice = START_PRICE;
        constructAllPanels(); // containers
        createStoreLabel();  // setting up store label
        
       // createComboPane(); // components
        createComboPane();
        createConePane();
        createFlavorPane();
        createMixInsPane();
        createPriceOrderPane();
       
        pack();
    }
    
    /**
     * Create all the boxes and Panels for the Ice Cream store.
     */
    private void constructAllPanels() {
        // initializing all the panel / boxes
        myPanelWest = new JPanel();
        myPanelWest.setLayout(new BoxLayout(myPanelWest, BoxLayout.Y_AXIS));
        myConePanel = new ConePanel();
        myConePanel.setLayout(new BoxLayout(myConePanel, BoxLayout.Y_AXIS));
        myRadioPanel = new JPanel();
        myRadioPanel.setLayout(new BoxLayout(myConePanel, BoxLayout.Y_AXIS));
        myComboPanel = new JPanel();
        myComboPanel.setLayout(new BoxLayout(myConePanel, BoxLayout.Y_AXIS));
        myCenterBox = new JPanel();
        myCenterBox.setLayout(new BoxLayout(myCenterBox, BoxLayout.X_AXIS));
        mySouthPanel = new JPanel();
        mySouthPanel.setLayout(new BoxLayout(mySouthPanel, BoxLayout.X_AXIS));
        myPanelEast = new JPanel();
        myPanelEast.setLayout(new BoxLayout(myPanelEast, BoxLayout.Y_AXIS));
    }
    
    /**
     * Setting up store name, outline, etc...
     */
    private void createStoreLabel() {
        final JLabel storeLabel = new JLabel("Welcome to Viet�s Ice-Cream Parlor!");
        storeLabel.setFont(new Font("Serif", Font.BOLD, STORE_FONTSIZE));
        storeLabel.setForeground(Color.BLUE);
        storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
     // add a store label to the top
        add(storeLabel, BorderLayout.NORTH);
    }
    
    /**
     * Create ComboBox.
     */
    private void createComboPane() {
        final double priceTrack = 7.99;
        final double small = 7.99;
        myComboPriceTrack = priceTrack;
        myComboBox = new ComboBox();
        final JLabel comboLabel = new JLabel("The name of your choice: ");
        comboLabel.setVerticalAlignment((int) LEFT_ALIGNMENT);
        //adding listener
        final PriceListener comboPrice = new PriceListener(small);
        
        myComboBox.addItemListener(comboPrice);
        
        myPanelWest.add(comboLabel);
        myPanelWest.add(myComboBox);
        
        // set color to match other panels
        myPanelWest.setBackground(new Color(BACKGROUND_COLOR));
        add(myPanelWest, BorderLayout.WEST); // add this panel to the west of the frame
    }
    
    
     /**
      * Create Cone toggle buttons in a Pane.
      */
    private void createConePane() {
        final double priceTrack = 0.99;
        myConePriceTrack = priceTrack;

        // construct Cone toggle buttons
        final PriceListener conePrice1 = new PriceListener(0.99);
        final PriceListener conePrice2 = new PriceListener(1.99);
        final PriceListener conePrice3 = new PriceListener(0.00);
        myConePanel.getMySugarCone().addItemListener(conePrice1);
        myConePanel.getMyWaffleCone().addItemListener(conePrice2);
        myConePanel.getMyPaperCup().addItemListener(conePrice3);
        myConePanel.setBackground(new Color(BACKGROUND_COLOR));
       
    }
    /**
     * Create an ice cream flavor pane.
     */
    private void createFlavorPane() {
        
        myFlavorPriceTrack = 0.00;
        
        // Construct the Flavor Radio Buttons with their values in a box of 5.
        myFlavorBox = new FlavorBox();
        final PriceListener vanillaPrice = new PriceListener(0.00);
        final PriceListener chocolatePrice = new PriceListener(0.00);
        final PriceListener strawBerryPrice = new PriceListener(0.00);
        
        final PriceListener mangoPrice = new PriceListener(1.49);
        final PriceListener lemonPrice = new PriceListener(1.49);
        myFlavorBox.getMyFrenchVanilla().addItemListener(vanillaPrice);
        myFlavorBox.getMyChocolate().addItemListener(chocolatePrice);
        myFlavorBox.getMyStrawberry().addItemListener(strawBerryPrice);
        myFlavorBox.getMyMango().addItemListener(mangoPrice);
        myFlavorBox.getMyLemon().addItemListener(lemonPrice);
        
        // adjust the positions of panel and box to the top
        myConePanel.setAlignmentY(TOP_ALIGNMENT);
        myFlavorBox.setAlignmentY(TOP_ALIGNMENT);
  
        myCenterBox.setBackground(new Color(BACKGROUND_COLOR));
        myCenterBox.add(Box.createHorizontalGlue());
        myCenterBox.add(myConePanel);
        myCenterBox.add(Box.createHorizontalGlue());
        myCenterBox.add(myFlavorBox);
        myCenterBox.add(Box.createHorizontalGlue());

        add(myCenterBox, BorderLayout.CENTER);   
    }
     /**
      * Create Mix In Boxes.
      */
    private void createMixInsPane() {
      
        final JLabel mixIns = new JLabel("Mix-Ins:");
        mixIns.setBackground(new Color(BACKGROUND_COLOR));
        myMixInBox = new MixInBox();
        final PriceListener mixInPrice = new PriceListener(0.39);
        // adding listener to check boxes
        myMixInBox.getMyAppleCoconut().addItemListener(mixInPrice);
        myMixInBox.getMyBananaCherry().addItemListener(mixInPrice);
        myMixInBox.getMyBerryLemon().addItemListener(mixInPrice);
        myMixInBox.getMyCookieOrio().addItemListener(mixInPrice);
        myMixInBox.getMyGrapeOrange().addItemListener(mixInPrice);
        myMixInBox.getMyPineAppleGrape().addItemListener(mixInPrice);
        
        myPanelEast.add(mixIns);
        myPanelEast.add(myMixInBox);
        myPanelEast.setBackground(new Color(BACKGROUND_COLOR));
        add(myPanelEast, BorderLayout.EAST);
    }
    /**
     * adds components to a panel.
     * 
     */
    private void createPriceOrderPane() {         
        
        final JButton order = new JButton("Order");
        final OrderListener orderAction = new OrderListener();
        order.addActionListener(orderAction);
        order.setToolTipText("Click When Ready"); // tip on the button

        final JPanel pane = new JPanel(); // create an instance pane for Price and Order
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        myPrice = new JLabel("$8.98");
        pane.add(Box.createVerticalGlue());
        pane.add(new JLabel("Price:"));
        pane.add(myPrice);

        pane.add(new JLabel("-----------------"));
        pane.setBackground(new Color(BACKGROUND_COLOR));
        pane.add(order);
        mySouthPanel.add(Box.createHorizontalGlue());
        mySouthPanel.add(pane);
        mySouthPanel.add(Box.createHorizontalGlue());
        mySouthPanel.setBackground(new Color(BACKGROUND_COLOR));
        add(mySouthPanel, BorderLayout.SOUTH); // add last panel to the south of this frame
    }
    
    
    /**
     * This represent a price listener. 
     * @author Viet Nguyen
     *
     */
    private class PriceListener implements ItemListener {
        
        /**
         * Price base on each button.
         */
        private double myActionPrice;
        
        /**
         * Create a listener action for each button when it got clicked or not selected.
         * 
         * @param anPrice is passed in constructor base on each button.
         */
        public PriceListener(final double anPrice) {
            myActionPrice = anPrice;
        }
        @Override
        public void itemStateChanged(ItemEvent anEvent) {
            final Object source = anEvent.getItemSelectable();
            AbstractButton origin;
            
            if ("javax.swing.JToggleButton".equals(source.getClass().getName())) {
               // origin = (JToggleButton) source;
                myTotalPrice += myActionPrice - myConePriceTrack;   
                myConePriceTrack = myActionPrice;
                
            } else if ("javax.swing.JRadioButton".equals(source.getClass().getName())) {
                // Flavor JRadio Buttons
                myTotalPrice += myActionPrice - myFlavorPriceTrack;   
                myFlavorPriceTrack = myActionPrice;   
                
            } else if ("javax.swing.JCheckBox".equals(source.getClass().getName())) {
                origin = (JCheckBox) source;
                if (origin.isSelected()) {
                    myTotalPrice += myActionPrice;
                } else {
                    myTotalPrice -= myActionPrice;
                }     
            } else {
                               
                if (myComboBox.getItemAt(0).equals(myComboBox.getSelectedItem())) {
                    myTotalPrice += myActionPrice - myComboPriceTrack;
                    myComboPriceTrack = myActionPrice;
                } else if (myComboBox.getItemAt(1).equals(myComboBox.getSelectedItem())) {  
                    myTotalPrice += myActionPrice + 2 - myComboPriceTrack;
                    myComboPriceTrack = myActionPrice + 2.00;
                } else {      
                    myTotalPrice += myActionPrice + 2 + 1 - myComboPriceTrack;
                    myComboPriceTrack = myActionPrice + 2 + 1;
                }
                
            }
            final String string = String.format(STRING_FORMAT, myTotalPrice);
            myPrice.setText(string);
        }
    }
    
    /**
     * Display Order Detail Action Listener.
     * 
     * @author Viet Nguyen
     *
     */
    public class OrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent anEvent) {
            
            String displayOrder = "Here your Order detail."
                    + "\n----------------------------------\n-Sizes:\n";
            String comboString = (String) myComboBox.getSelectedItem();
            if ("Mine (16 oz)".equals(comboString)) {
                comboString =  "Mine 16oz: $7.99";
            } else if ("Ours (32 oz)".equals(comboString)) {
                comboString = "Ours (32 oz): $9.99";
            } else {
                comboString = "Everybody�s (48 oz): $10.99";
            }
            displayOrder += "    " + comboString + "\n";
            displayOrder += "- Cones:\n";
            if (myConePanel.getMyPaperCup().isSelected()) {
                displayOrder += "    Paper Cup: Free \n";
            } else if (myConePanel.getMySugarCone().isSelected()) {
                displayOrder += "    Surgar Cone: $0.99 \n";
            } else {
                displayOrder += "    Waffle Cone: $1.99 \n";
            }
            
            displayOrder += "- Flavors:\n";
            if (myFlavorBox.getMyChocolate().isSelected()) {
                displayOrder += "    Chocolate: Free\n"; 
            } else if (myFlavorBox.getMyFrenchVanilla().isSelected()) {
                displayOrder += "    French Vanilla: Free\n"; 
            } else if (myFlavorBox.getMyLemon().isSelected()) {
                displayOrder += "    Lemon: $1.49\n"; 
            } else if (myFlavorBox.getMyMango().isSelected()) {
                displayOrder += "    Mango: $1.49\n"; 
            } else if (myFlavorBox.getMyStrawberry().isSelected()) {
                displayOrder += "    Strawberry: Free\n"; 
            }
            
            displayOrder += "- Mix-Ins:\n";
            if (myMixInBox.getMyAppleCoconut().isSelected()) {
                displayOrder += "    Apple Coconut: $0.39\n";
            } 
            if (myMixInBox.getMyBananaCherry().isSelected()) {
                displayOrder += "    Banana Cherry: $0.39\n";
            }
            if (myMixInBox.getMyBerryLemon().isSelected()) {
                displayOrder += "    Berry Lemon: $0.39\n";
            }
            if (myMixInBox.getMyCookieOrio().isSelected()) {
                displayOrder += "    Cookie Orio: $0.39\n";
            }
            if (myMixInBox.getMyGrapeOrange().isSelected()) {
                displayOrder += "    Grape Orange: $0.39\n";
            }
            if (myMixInBox.getMyPineAppleGrape().isSelected()) {
                displayOrder += "    Pineaple Grape: $0.39\n";
            }
            
            displayOrder += "----------------------------------\n";
            final String string = String.format(STRING_FORMAT, myTotalPrice);
            displayOrder += "Total Price: " + string;
            JOptionPane.showMessageDialog(null, displayOrder,
                "Your Ice Cream Order Detail List", JOptionPane.PLAIN_MESSAGE, ICE_CREAM);
        }
    }
    
} // end 