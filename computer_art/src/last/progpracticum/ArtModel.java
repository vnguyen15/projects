/**
 * 
 */
package last.progpracticum;

import java.util.ArrayList; 
import java.util.List;


/**
 * @author Viet Nguyen
 * @version 1
 */
public class ArtModel {
    
    /**
     * This is the list of ShapeProperties.
     */
    private List<ShapeProperties> myList;
    
    /**
     * Number of rectangle need to create.
     */
    private int myRectangle;
    
    /**
     * Number of ellipse need to create.
     */
    private int myEllipse;
    
    /**
     * Number of triangle need to create.
     */
    private int myPolygon;
    
    /**
     * Number of line need to create.
     */
    private int myLine;
    
    /**
     * This constructing all the shapes passing in.
     * 
     * @param aRectangle is number of rectangle
     * @param anEllipse is number of ellipse
     * @param aLine is number of line
     * @param aPolygon  is number of triangle
     */
    public ArtModel(final int aRectangle, final int anEllipse,
                     final int aLine, final int aPolygon) {
        
        myList = new ArrayList<ShapeProperties>();
        myRectangle = aRectangle;
        myEllipse = anEllipse;
        myPolygon = aPolygon;
        myLine = aLine;
        createShapes();
        
    }
    
    /**
     * Create shape base on their properties.
     */
    private void createShapes() {
        for (int i = 0; i < myRectangle; i++) {
            final ShapeProperties rectangle = new ShapeProperties('r');
            myList.add(rectangle);
        }
        
        for (int i = 0; i < myEllipse; i++) {
            final ShapeProperties ellipse = new ShapeProperties('e');
            myList.add(ellipse);
        }
        
        for (int i = 0; i < myLine; i++) {
            final ShapeProperties line = new ShapeProperties('l');
            myList.add(line);
        }
        
        for (int i = 0; i < myPolygon; i++) {
            final ShapeProperties polygon = new ShapeProperties('p');
            myList.add(polygon);
        }
    }

    /**
     * Return the list of ShapeProperties.
     * @return the myList is ShapeProperties.
     */
    public List<ShapeProperties> getMyList() {
        return myList;
    }

    /**
     * Get number of rectangle.
     * @return the myRectangle is number of rectangle.
     */
    public int getMyRectangle() {
        return myRectangle;
    }

    /**
     * get number of ellipse.
     * @return the myEllipse is number of ellipse
     */
    public int getMyEllipse() {
        return myEllipse;
    }

    /**
     * Get numbers of Triangle.
     * @return the myPolygon is number of triangle.
     */
    public int getMyPolygon() {
        return myPolygon;
    }

    /**
     * Get number of line.
     * @return the myLine is my line number
     */
    public int getMyLine() {
        return myLine;
    }

   
    /**
     * This updating the current shape properties list.
     * @param aShape is shape properties.
     */
    public void updateMylist(final ShapeProperties aShape) {
        myList.add(aShape);
    }
  
    /**
     * This change the number of rectangle need to create.
     * @param aRectangle number of rectangle
     */
    public void setMyRectangle(final int aRectangle) {
        this.myRectangle = aRectangle;
    }

    /**
     * This change the number of ellipse need to create.
     * 
     * @param aEllipse the myEllipse to set
     */
    public void setMyEllipse(final int aEllipse) {
        this.myEllipse = aEllipse;
    }

    /**
     * This change the number of triangle need to create.
     * @param aPolygon the myPolygon to set
     */
    public void setMyPolygon(final int aPolygon) {
        this.myPolygon = aPolygon;
    }

    /**
     * This change the number of line need to create.
     * 
     * @param aLine the myLine to set
     */
    public void setMyLine(final int aLine) {
        this.myLine = aLine;
    }
}
