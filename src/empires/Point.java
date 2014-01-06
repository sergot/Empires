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
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * counts the distance from this point to given point
     * @param p point
     * @return distance from point to point
     */
    public int distance(Point p) {
        return (int) Math.sqrt(Math.pow((p.getX() - this.x), 2) + Math.pow((p.getY() - this.y), 2));
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
