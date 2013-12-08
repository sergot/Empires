package empires;

/**
 *
 * @author Filip Sergot
 */
public class Point {
    private double x;
    private double y;
    
    Point() {
        
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        
        return p.getX() == this.x && p.getY() == this.y;
    }
    
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public int distance(Point p) {
        return (int) Math.sqrt(Math.pow((p.getX() - this.x), 2) + Math.pow((p.getY() - this.y), 2));
    }
    
    public double getAngle(Point p) {
        return Math.atan2(p.y - y, p.x - x);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
    
    public double[] toArray() {
        double[] point = new double[2];
        point[0] = this.x;
        point[1] = this.y;
        
        return point;
    }
    
    
}
