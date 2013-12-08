package empires;

import empires.gui.BeingGUI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

/**
 *
 * @author Filip Sergot
 */
public class Barbarians extends Being implements Runnable, Movable {
    private BeingGUI being;
    private Road currentRoad;
    
    private String name;
    private int size;
    private int weapon;
    private City in = null, dest = null;
    
    private ArrayList<City> toVisit;
    
    private double angle;

    public Barbarians() {
        being = new BeingGUI(BeingGUI.type.BARBARIANS);
        
        this.toVisit = (ArrayList<City>) Empires.game.getCities().clone(); // copy list of cities (a city will be removed if visited)
        
        currentRoad = Empires.game.getRoads().get(Empires.r.nextInt(Empires.game.getRoads().size())); // random road
        
        this.setPosition(currentRoad.countCenter()); // center of random road
        dest = currentRoad.getEnd();
        
        // set current position to drawing
        being.setFrame(this.getPosition().getX() - being.getRadius() / 2, this.getPosition().getY() - being.getRadius() / 2, being.getRadius(), being.getRadius());
        being.setPath(currentRoad.getPath());
    }
    
    /**
     * 
     * @return the city which is nearest to the group of barbarians
     */
    private void nearestCity() {
        City destination = null;
        
        for(City c : toVisit) {
            if(c.getPopulation() <= 0)
                continue;
            
            if(destination == null) {
                destination = c;
                continue;
            }
            
            if(this.getPosition().distance(c.getPosition()) < this.getPosition().distance(destination.getPosition()) && !c.getPosition().equals(this.getPosition())) {
                destination = c;
            }
        }
        
        this.dest = destination;
        this.toVisit.remove(destination);
        
        if(in != null) {
            Road newDestRoad;
            if((newDestRoad = Empires.game.specificRoad(in, dest)) != null)
                being.setPath(newDestRoad.getPath());
            else
                being.setPath(null);
        }
    }
    
    /**
     * 
     * @param count how many people to kill
     */
    private synchronized void killPeople(int count) {
        in.setPopulation(in.getPopulation() - count);
    }

    @Override
    public void move() {
        double[] point = new double[2];
        
        PathIterator pi = being.getPath().getPathIterator(null);
        
        while(!pi.isDone()) {
            int type = pi.currentSegment(point);
            
            if(type == PathIterator.SEG_MOVETO) {
                being.setFrame(point[0] - being.getRadius() / 2, point[1] - being.getRadius() / 2, being.getRadius(), being.getRadius());
            } else if(type == PathIterator.SEG_LINETO) {
                double ox = being.getCenterX();
                double oy = being.getCenterY();
                double nx = point[0];
                double ny = point[1];
                
                double distance = Math.sqrt((ox-nx)*(ox-nx) + (oy-ny)*(oy-ny));
                
                int count = (int)((distance / being.getSpeed()) / being.getDelay());
                
                double deltaX = (nx - ox) / count;
                double deltaY = (ny - oy) / count;
                
                for (int i = 0; i < count; i++) {
                    ox += deltaX;
                    oy += deltaY;
                    being.setFrame(ox - being.getRadius() / 2, oy - being.getRadius() / 2,
                                       being.getRadius(), being.getRadius());
                    
                    try { Thread.sleep(being.getDelay()); }
                    catch (InterruptedException e) { }
                }
            }
            pi.next();
        }
    }

/*    @Override
    public void move() {
        Point now = this.getPosition();
        if(dest != null)
            if(now.equals(this.dest.getPosition())) {
                in = dest;
                this.killPeople(Empires.r.nextInt(100));
                if(in.getPopulation() <= 0)
                    this.nearestCity();
            } else {
                angle = this.getPosition().getAngle(this.dest.getPosition());

                now.setX((int) Math.round(now.getX() + Math.round(0.75 * Math.cos(angle))));
                now.setY((int) Math.round(now.getY() - Math.round(0.75 * Math.sin(-angle))));
            }
    }
*/    
    @Override
    public void run() {
        while(true) {
            if(being.getPath() != null && in == null) {
                this.move();
                in = dest;
            }
            
            if(in != null)
                this.killPeople(Empires.r.nextInt(100));
            
            if(toVisit.isEmpty()) break;
            
            if(in != null && in.getPopulation() <= 0) {
                this.nearestCity(); // get new destination
                in = null;
            }
            
            try { Thread.sleep(10);
            } catch (InterruptedException e) { }
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(being.getColor());
        g.draw(being);
        g.setColor(Color.yellow);
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the weapon
     */
    public int getWeapon() {
        return weapon;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the in
     */
    public City getIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public void setIn(City in) {
        this.in = in;
    }
}
