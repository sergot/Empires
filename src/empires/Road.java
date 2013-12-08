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
    
    Road(City start, City end) {
        this.start = start;
        this.end = end;
        
        path = this.newPath();
    }
    
    @Override
    public boolean equals(Object o) {
        Road r = (Road) o;
        return this.equals(r);
    }
    
    private Path2D newPath() {
        Path2D newPath;
        
        double startX = start.getPosition().getX(),
               startY = start.getPosition().getY(),
               endX   =   end.getPosition().getX(),
               endY   =   end.getPosition().getY();
        
        newPath = new Path2D.Double();
        
        newPath.moveTo(endX, endY);
        newPath.moveTo(startX, startY);
        
        newPath.lineTo(endX, endY);
        
        newPath.closePath();
        return newPath;
    }
    
    public Point countCenter() {
        return new Point((start.getPosition().getX() + end.getPosition().getX()) / 2, (start.getPosition().getY() + end.getPosition().getY()) / 2);
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
        
        /*Point p = start.getPosition();
        double angle;
        while(!p.equals(end.getPosition())) {
            angle = p.getAngle(end.getPosition());
            g.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
            
            p.setX((int) (p.getX() + Math.round(Math.cos(angle))));
            p.setY((int) (p.getY() - Math.round(Math.sin(-angle))));
            
            System.out.println(p.getX() + "," + p.getY());
        }
        System.out.println("END");
        */
        //g.drawLine(start.getPosition().getX(), start.getPosition().getY(), end.getPosition().getX(), end.getPosition().getY());
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
