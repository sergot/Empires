package empires;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Filip Sergot
 */
public class Capital extends City {
    Capital() {
        super();
    }
    
    Capital(Point p) {
        super(p);
    }
    
    public Legion sendLegion() {
        return new Legion();
    }
    
    @Override
    public void draw(Graphics2D g) {
        if(this.getPopulation() <= 0)
            g.setColor(Color.black);
        g.fillOval((int)this.getPosition().getX() - 25, (int)this.getPosition().getY() - 25, 50, 50);
        g.setColor(Color.yellow);
    }
}
