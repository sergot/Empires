package empires;

/**
 *
 * @author Filip Sergot
 */
public class Capital extends City {
    public Legion sendLegion() {
        return new Legion();
    }
    
    @Override
    public void draw() {
        super.draw();
        // TODO
    }
}
