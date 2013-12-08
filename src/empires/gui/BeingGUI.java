package empires.gui;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/**
 *
 * @author Filip Sergot
 */
public class BeingGUI extends Ellipse2D.Double {
    public static enum type {
        BARBARIANS, LEGION, TRADER, CITY, CAPITAL, CART
    }
    
    private Path2D path;
    private double radius, speed;
    private Color color;
    private int delay = 30;
    
    public BeingGUI(double radius, double speed, Color color) {
        super();
        
        this.radius = radius;
        this.speed = speed;
        this.color = color;
    }
    
    public BeingGUI(type t) {
        super();
        
        if(t == type.BARBARIANS) {
            radius = 10.;
            speed = 1.0 / 15.0;
            color = Color.red;
        }
        
        // TODO
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
