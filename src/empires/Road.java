package empires;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 *
 * @author Filip Sergot
 */
public class Road extends Thing {
    private City start;
    private City end;
    private Path2D path;
    private boolean oneWay = false;
    private int offset = 0;
    
    Road(City start, City end, int offset) {
        this.start = start;
        this.end = end;
        this.offset = offset;
        
        path = this.newPath(offset);
    }
    
    @Override
    public boolean equals(Object o) {
        Road r = (Road) o;
        return this.equals(r);
    }
    
    /**
     * creates new Path2D for given road
     * @param offset offset
     * @return new Path2D
     */
    private Path2D newPath(int offset) {
        Path2D newPath;
        
        double startX = start.getPosition().getX() + offset,
               startY = start.getPosition().getY() + offset,
               endX   =   end.getPosition().getX() + offset,
               endY   =   end.getPosition().getY() + offset;
        
        newPath = new Path2D.Double();
        
        newPath.moveTo(endX, endY);
        newPath.moveTo(startX, startY);
        
        newPath.lineTo(endX, endY);
        
        newPath.closePath();
        return newPath;
    }
    
    /**
     * counts center of this road
     * @return 
     */
    public Point countCenter() {
        return new Point((start.getPosition().getX() + end.getPosition().getX()) / 2, (start.getPosition().getY() + end.getPosition().getY()) / 2);
    }
    
    @Override
    public String toString() {
        return "from: " + start + " to: " + end;
    }
    
    /**
     * @return the start
     */
    public City getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public City getEnd() {
        return end;
    }

    /**
     * @return the oneWay
     */
    public boolean isOneWay() {
        return oneWay;
    }

    /**
     * @param start the start to set
     */
    public void setStart(City start) {
        this.start = start;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(City end) {
        this.end = end;
    }

    /**
     * @param oneWay the oneWay to set
     */
    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        
        g.draw(getPath());
        g.setColor(Color.yellow);
    }

    /**
     * @return the path
     */
    public Path2D getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(Path2D path) {
        this.path = path;
    }
    
    
}
