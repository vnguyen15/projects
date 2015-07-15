/**
 * 
 */
package last.progpracticum;

import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

/**
 * This is representing a shape with all its properties.
 * 
 * @author Viet Nguyen
 * @version 1
 */
public class ShapeProperties implements Cloneable {
    /**
     * This is the actual shape.
     */
    private Shape myShape;
    
    /**
     * This is color of the shape.
     */
    private Color myColor;
    /**
     * This is decided whether shape is filled or not.
     */
    private int myFlag;
    
    /**
     * This is stroke, thickness of shape.
     */
    private int myStroke;
    
    /**
     * This is tracking what shape need to create.
     */
    private char myType;
    
    /**
     * A random number for choosing properties of shapes.
     */
    private Random myRandom;

    /**
     * Array of x coordinates.
     */
    private int[] myX;
    
    /**
     * array of Y coordinates.
     */
    private int[] myY;
    
    /**
     * horizontal, x move.
     */
    private int myHorizontalmove;
    
    /**
     * vertical, y move.
     */
    private int myVerticalmove;
    
    /**
     * Initial value.
     */
    private final int myVal = 5;
    
    /**
     * Initial Color max range.
     */
    private final int myRange = 255;
    
    /**
     * initial screen ratio denominator.
     */
    private final int myDeno = 70;
    
    
    /**
     * initial screen ratio denominator for the screen width.
     */
    private final int myDenoWidth = 58;
    
    /**
     * initial screen ratio numerator.
     */
    private final int myNum = 100;
    
    /**
     * This constructing the shape with all its properties randomly.
     * @param aType is representing what type of shape need to create.
     */
    public ShapeProperties(final char aType) {
        initialProperties(aType);
        createShape(aType);
    }
    /**
     * This initializing the shape properties randomly.
     * @param aType decide what shape need to create
     */
    private void initialProperties(final char aType) {
        myRandom = new Random();
        myColor = new Color(myRandom.nextInt(myRange),
                            myRandom.nextInt(myRange), myRandom.nextInt(myRange));
        myFlag = myRandom.nextInt(2);
        myType = aType;
        myStroke = myRandom.nextInt(myVal * 2 + myVal);
        
        myHorizontalmove =  myRandom.nextInt(myVal + 2) - 1 - myVal;
        myVerticalmove =  myRandom.nextInt(myVal + 2) - 1 - myVal;
        while (myHorizontalmove == 0 && myVerticalmove == 0) {
            myHorizontalmove =  myRandom.nextInt(myVal + 2) - 1 - myVal;
            myVerticalmove =  myRandom.nextInt(myVal + 2) - 1 - myVal;
        }
    }
    /**
     * This create a shape base on the type pass in.
     * @param aType decide what shape need to create
     */
    public void createShape(final char aType) {
        final Random rdn = new Random();
        final Toolkit kit = Toolkit.getDefaultToolkit();  // getting tool kit object
        final Dimension screenSize = kit.getScreenSize();
        final int frameWidth = screenSize.width * myDenoWidth / myNum; 
        final int frameHeight = screenSize.height * myDeno / myNum;
        //setSize(screenWidth * 80 / 100, screenHeight * 80 / 100); 
        
        final int xCor = rdn.nextInt(frameWidth - myVal * (2 + 1)) + myVal * (2 + 1);
        final int yCor = rdn.nextInt(frameHeight - myVal * (2 + 1)) + myVal * (2 + 1);
        
        if (aType == 'r') {
            final int w = Math.abs(frameWidth - xCor);
            final int h = Math.abs(frameHeight - yCor);
            myShape = new Rectangle2D.Double(xCor, yCor,
                                             rdn.nextInt(w) , rdn.nextInt(h));

        } else if (aType == 'e') {
            final int w = Math.abs(frameWidth - xCor);
            final int h = Math.abs(frameHeight - yCor);
            myShape = new Ellipse2D.Double(xCor, yCor,
                                           rdn.nextInt(w) , rdn.nextInt(h));
        } else if (aType == 'l') {

            int x1 = rdn.nextInt(frameWidth - myVal) + myVal * (2 + 1);
            int y1 = rdn.nextInt(frameHeight - myVal) + myVal * (2 + 1);
            while (Math.abs(xCor - x1) < myVal  && Math.abs(yCor - y1) < myVal) {
                x1 = rdn.nextInt(frameWidth - myVal) + myVal * (2 + 1);
                y1 = rdn.nextInt(frameHeight - myVal) + myVal * (2 + 1);
            }
         
            myShape = new Line2D.Double(xCor, yCor, x1, y1);
            
        } else if (aType == 'p') {
            myX = new int[2 + 1];
            myX[0] = rdn.nextInt(frameWidth - myVal * 2) + myVal * 2;
            myX[1] = rdn.nextInt(frameWidth - myVal * 2) + myVal * 2;
            myX[2] = rdn.nextInt(frameWidth - myVal * 2) + myVal * 2;
            
            myY = new int[2 + 1];
            myY[0] = rdn.nextInt(frameHeight - myVal * 2) + myVal * 2;
            myY[1] = rdn.nextInt(frameHeight - myVal * 2) + myVal * 2;
            myY[2] = rdn.nextInt(frameHeight - myVal * 2) + myVal * 2;
            
            myShape = new Polygon(myX, myY, 2 + 1);
        }
        
    }
    
    /**
     * Deep copy shape and its random properties.
     * @throws CloneNotSupportedException unsupported clone object.
     * @return ShapeProperties with its random stats.
     */
    public ShapeProperties clone() throws CloneNotSupportedException {
        final ShapeProperties shape = (ShapeProperties) super.clone();
        if (myType == 'r') {
            shape.myShape = (Shape) ((RectangularShape) myShape).clone();
        } else if (myType == 'e') {
            shape.myShape = (Shape) ((Ellipse2D) myShape).clone();   
        } else if (myType == 'p') {
            
            shape.myShape = new Polygon(myX.clone(), myY.clone(), 2 + 1);
            
        } else if (myType == 'l') {
            shape.myShape = (Shape) ((Line2D) myShape).clone();
        }
        return shape;
    }
    
    /**
     * Update the position of this BouncingShape.
     */
    public void move() {
        
        if (myType == 'r' || myType == 'e') {
            final RectangularShape  shape = (RectangularShape) myShape;
            shape.setFrame(shape.getX() + myHorizontalmove,
                        shape.getY() + myVerticalmove,
                        shape.getWidth(), shape.getHeight());
        } else if (myType == 'l') {
            final Line2D line = (Line2D) myShape;
            line.setLine(line.getX1() + myHorizontalmove, line.getY1() + myVerticalmove,
                         line.getX2() + myHorizontalmove, line.getY2() + myVerticalmove);
        } else if (myType == 'p') {
            myX[0] = myX[0] + myHorizontalmove;
            myX[1] = myX[1] + myHorizontalmove;
            myX[2] = myX[2] + myHorizontalmove;
            
            myY[0] = myY[0] + myVerticalmove;
            myY[1] = myY[1] + myVerticalmove;
            myY[2] = myY[2] + myVerticalmove;
            
            myShape = new Polygon(myX, myY, 2 + 1);
        }
    }

    /**
     * Set a new direction for horizontal coordinate.
     * @param aDirection > 0 means move right
     */
    public void setHorizontalDirection(final int aDirection) {
        myHorizontalmove = Math.abs(myHorizontalmove) * aDirection;
    }

    /**
     * This set new direction for vertical coordinate.
     * @param aDirection > 0 means move down
     */
    public void setVerticalDirection(final int aDirection) {
        myVerticalmove = Math.abs(myVerticalmove) * aDirection;
    }
   

    /**
     * Get an actual shape.
     * 
     * @return the myShape actual shape
     */
    public Shape getMyShape() {
        return myShape;
    }

    /**
     * Get the random color of this shape.
     * @return the myColor random color.
     */
    public Color getMyColor() {
        return myColor;
    }

    /**
     * Get Either fill or no fill.
     * @return the myFlag either 1 or 0 
     */
    public int getMyFlag() {
        return myFlag;
    }

    /**
     * get outline , thickness of the shape.
     * @return the myStroke is outline of shape
     */
    public int getMyStroke() {
        return myStroke;
    }

    /**
     * Get the type of shape.
     * @return the myType either line, rectangle, triangle, or ellipse
     */
    public char getMyType() {
        return myType;
    }

    /**
     * it return the x coordinates.
     * @return the myX is array of x coordinates.
     */
    public int[] getMyX() {
        return myX;
    }
    
    /**
     * Return y coordinates.
     * @return array of Y coordinate
     */
    public int[] getMyY() {
        return myY;
    }

} // end ShapeProperties class
