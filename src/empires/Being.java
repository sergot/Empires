package empires;

/**
 *
 * @author Filip Sergot
 */
public class Being extends Thing {
    private Point position;

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

    @Override
    public void draw() {
        
    }
    
}
