package empires;

import empires.gui.BeingGUI;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Filip Sergot
 */
public class Legion extends Being implements Runnable, Movable {
    private BeingGUI being;
    private Road currentRoad;
    
    private Barbarians chasing;
    private City from, to, in;
    
    public Legion() {
        being = new BeingGUI(BeingGUI.type.LEGION, this);
        this.setRadius(being.getRadius());
        
        if(Empires.getGame().barbariansInCity()) {
            chasing = Empires.getGame().getBarbariansInCity();

            from = Empires.getGame().getCapital();
            to = chasing.getIn();

            in = from;

            currentRoad = Empires.getGame().specificRoad(from, to);

            if(currentRoad != null) {
                being.setPath(currentRoad.getPath());
                double x = Empires.getGame().getCapital().getPosition().getX(),
                       y = Empires.getGame().getCapital().getPosition().getY();
                being.setFrame(x - being.getRadius() / 2, y - being.getRadius() / 2, being.getRadius(), being.getRadius());
                being.setPath(currentRoad.getPath());
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(being.getColor());
        g.draw(being);
        
        g.setColor(Color.yellow);
    }
    
    @Override
    public void move() {
        if(to != null && from != null) {
            being.move();
        }
    }

    @Override
    public void run() {
        while(!dead()) {
            if(currentRoad != null) {
                move();
                if(currentRoad.getEnd().equals(Empires.getGame().getCenter())) {
                    currentRoad = Empires.getGame().specificRoad(to);
                    being.setPath(currentRoad.getPath());
                    continue;
                }
                in = to;
            }
            
            currentRoad = null;
            
            if(Empires.getGame().getBarbarians().contains(chasing) && chasing != null && chasing.getIn() != null && in != null && in.equals(chasing.getIn())) {
                kill(chasing);
                die();
                Empires.getGame().getLegions().remove(this);
            } else {
                chasing = Empires.getGame().getBarbariansInCity();
                if(chasing != null && chasing.getIn() != null) {
                    
                    from = in;
                    to = chasing.getIn();
                    
                    currentRoad = Empires.getGame().specificRoad(from, to);
                                        
                    if(currentRoad != null) {
                        being.setPath(currentRoad.getPath());
                        in = null;
                    }
                }
            }
        }
        Empires.getGame().getLegions().remove(this);
    }
    
    /**
     * kills met barbarians
     * @param b met barbarians
     */
    private void kill(Barbarians b) {
        if(Empires.getGame().getBarbarians().contains(b)) {
            Empires.getGame().getBarbarians().remove(b);
            b.die();
        }
    }
}
