package empires.gui;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import empires.Being;
import empires.Point;
import java.awt.geom.PathIterator;

/**
 *
 * @author Filip Sergot
 */
public class BeingGUI extends Ellipse2D.Double {
    public static enum type {
        BARBARIANS, LEGION, TRADER, CITY, CAPITAL, CART
    }
    
    private Being owner;
    
    private Path2D path;
    private double radius, speed;
    private Color color;
    private int delay = 30;
    
    public BeingGUI(double radius, double speed, Color color, Being b) {
        super();
        
        owner = b;
        
        this.radius = radius;
        this.speed = speed;
        this.color = color;
    }
    
    public BeingGUI(type t, Being b) {
        super();
        
        owner = b;
        
        if(t == type.BARBARIANS) {
            radius = 10.;
            speed = 1. / 20.;
            color = Color.red;
        } else if(t == type.LEGION) {
            radius = 10.;
            speed = 1. / 15.;
            color = Color.blue;
        } else if(t == type.TRADER) {
            radius = 10.;
            speed = 1. / 10;
            color = Color.green;
        }
        
        // TODO
    }
    
    /**
     * moves objects
     */
    public void move() {
        double[] point = new double[2];
        
        PathIterator pi = path.getPathIterator(null);
        
        while(!pi.isDone()) {
            int type = pi.currentSegment(point);
            
            if(type == PathIterator.SEG_MOVETO) {
                double x = point[0] - radius / 2,
                       y = point[1] - radius / 2;
                setFrame(x, y, radius, radius);
                owner.setPosition(new Point(x, y));
            } else if(type == PathIterator.SEG_LINETO) {
                double ox = getCenterX();
                double oy = getCenterY();
                double nx = point[0];
                double ny = point[1];
                
                double distance = Math.sqrt((ox-nx)*(ox-nx) + (oy-ny)*(oy-ny));
                
                int count = (int)((distance / speed) / delay);
                
                double deltaX = (nx - ox) / count;
                double deltaY = (ny - oy) / count;
                
                for (int i = 0; i < count; i++) {
                    ox += deltaX;
                    oy += deltaY;
                    
                    double new_x = ox - radius / 2,
                           new_y = oy - radius / 2;
                    owner.setPosition(new Point(new_x, new_y));
                    
                    setFrame(new_x, new_y, radius, radius);
                    
                    try { Thread.sleep(delay); }
                    catch (InterruptedException e) { }
                }
            }
            pi.next();
        }
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

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }    
}
