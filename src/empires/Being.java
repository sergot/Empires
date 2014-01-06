package empires;

import java.awt.Graphics2D;

/**
 *
 * @author Filip Sergot
 */
public class Being extends Thing {
    private Point position;
    private double radius;
    private boolean dead = false;
    
    public boolean closeTo(int x, int y) {
        if(position != null) {
            double dist = (Math.pow(position.getX() - x, 2) + Math.pow(y - position.getY(), 2));
            if(dist <= Math.pow(radius, 2))
                return true;
        }
        return false;
    }
    
    /**
     * kills this being
     */
    public void die() {
        dead = true;
    }
    
    /**
     * is this being dead?
     * @return dead or not
     */
    public boolean dead() {
        return dead;
    }
    
    @Override
    public void draw(Graphics2D g) {
        
    }
    
    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
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
    
}
