package empires;

/**
 *
 * @author Filip Sergot
 */
public abstract class Groups {
    private Point position;
    
    public abstract void move(Point p);

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
    
    
}
