/**
 * 
 */
package last.progpracticum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This is a panel where all the shapes are drawn and animation take place.
 * 
 * @author Viet Nguyen
 * @version 1
 *
 */
@SuppressWarnings("serial")
public class ArtPanel extends JPanel {
    /**
     * This is max color range.
     */
    public static final int RANGE = 255;
    /**
     * this is the model.
     */
    private ArtModel myModel;
    
    /**
     * This is the tracking point.
     */
    private Point myPoint;

    /**
     * Color 1 for Gradient Paint color.
     */
    private Color myColor2;
    
    /**
     * base Color 2 for gradient paint color.
     */
    private Color myColor1;
    
    /**
     * Gradient Color for background.
     */
    private GradientPaint myGP;
    
    /**
     * Width of gradient paint color.
     */
    private int myW;
    
    /**
     * Height of gradient paint color.
     */
    private int myH;
    
    /**
     * This is the button tool bar pop up menu.
     */
    private PopupMenu myPopupMenu;
    
   // private GeneralPath myPath;
    
    /**
     * Create Art Models, Shapes, etc.
     */
    public ArtPanel() {
        super();
        myModel = new ArtModel(0, 0, 0, 0);
        final MouseClick mouse = new MouseClick();
        myPopupMenu = new PopupMenu();
       // final MouseClick myMouse = new MouseClick();
        setComponentPopupMenu(myPopupMenu);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        //addMouseMotionListener((MouseMotionListener) myMouse);
        //setBackground(Color.white);
        final Random rdn = new Random();
        myColor1 = new Color(rdn.nextInt(RANGE), rdn.nextInt(RANGE), rdn.nextInt(RANGE));
        myColor2 = new Color(rdn.nextInt(RANGE), rdn.nextInt(RANGE), rdn.nextInt(RANGE));
        repaint();
    }
    
    /**
     * This return the art model.
     * 
     * @return the myModel is the model of this art.
     */
    public ArtModel getMyModel() {
        return myModel;
    }

    /**
     * Return the Point of mouse click.
     * 
     * @return the myPoint mouse click point.
     */
    public Point getMyPoint() {
        return myPoint;
    }

    /**
     * Return the tool menu bar pop up menu.
     * 
     * @return the myPopupMenu is the pop up menu
     */
    public PopupMenu getMyPopupMenu() {
        return myPopupMenu;
    }
    
    /**
     * Paints some ellipses.
     * 
     * @param aGraphics The graphics context to use for painting.
     */
    public void paintComponent(final Graphics aGraphics) {
        super.paintComponent(aGraphics);
        final Graphics2D g2d = (Graphics2D) aGraphics;
      
          // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
          
        myW = getWidth();
        myH = getHeight();
        
        myGP = new GradientPaint(0, 0, myColor1, myW, myH, myColor2);
        g2d.setPaint(myGP);
        g2d.fillRect(0, 0, myW, myH);
          
          // draw shapes
        for (int i = 0; i < myModel.getMyList().size(); i++) {
            final Shape shape = (Shape) myModel.getMyList().get(i).getMyShape();
            g2d.setPaint(myModel.getMyList().get(i).getMyColor());
            if (myModel.getMyList().get(i).getMyFlag() == 0) {
                g2d.setStroke(new BasicStroke(myModel.getMyList().get(i).getMyStroke()));
                g2d.draw(shape);
            } else {
                g2d.fill(shape);
            }
        }
          
    }
    
    /**
     * This is mouse click listener.
     * 
     * @author Viet Nguyen
     *
     */
    private class MouseClick extends MouseAdapter {
        
        /**
         * Get the point from left mouse click.
         * @param anEvent event from the left click mouse.
         */
        public void mousePressed(MouseEvent anEvent) {
            if (SwingUtilities.isLeftMouseButton(anEvent)) {
                myPoint = anEvent.getPoint();
            }           
              
            final int size = myModel.getMyList().size();
            int i = size - 1;
            boolean found = true;
            while (i >= 0 && found) {
                final Shape shape = (Shape) myModel.getMyList().get(i).getMyShape();
                if (shape.intersects(myPoint.getX(), myPoint.getY(), 2 + 2 + 1, 2 + 2 + 1)) {
                    myModel.getMyList().remove(i);
                    found = false;
                }
                i--;
            }
            repaint();

        }
    }

    /**
     * @return the myColor2
     */
    public Color getMyColor2() {
        return myColor2;
    }

    /**
     * @param myColor2 the myColor2 to set
     */
    public void setMyColor2(Color myColor2) {
        this.myColor2 = myColor2;
    }

    /**
     * @return the myColor1
     */
    public Color getMyColor1() {
        return myColor1;
    }

    /**
     * @param myColor1 the myColor1 to set
     */
    public void setMyColor1(Color myColor1) {
        this.myColor1 = myColor1;
    }
          
         /* public void mouseDragged(MouseEvent anEvent) {
              if(SwingUtilities.isLeftMouseButton(anEvent)) {
                  final Point fromPoint = anEvent.getPoint();
              
                  final Point toPoint = anEvent.getPoint();
                    
              
                  final int size = myModel.getMyList().size();
                  int i = size - 1;
                  boolean found = true;
                  while (i-- >= 0 && found) {
                      final Shape shape = (Shape) myModel.getMyList().get(i).getMyShape();
                      if (shape.intersects(fromPoint.getX(), fromPoint.getY(), 5, 5)) {
                          myModel.getMyList().get(i).move(toPoint.getX(), toPoint.getY());
                          found = false;
                     
                      
                      myPath.append((PathIterator) myModel.getMyList().get(i), true);
                      fromPoint .setLocation(toPoint);
                      repaint(myPath.getBounds());
                      }
                  }
                  repaint();
              } 
          }
          
          public void mouseReleased (MouseEvent anEvent) {
              if(SwingUtilities.isLeftMouseButton(anEvent)) {
                  repaint();
              }
          }
    } */

} // end ArtPanel class
