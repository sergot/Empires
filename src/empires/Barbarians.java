package empires;

import empires.gui.BeingGUI;
import java.awt.Color;
import java.awt.Graphics2D;
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
        String[] names = new String[10];
        names[0] = "Heat";
        names[1] = "Pelicans";
        names[2] = "Thunder";
        names[3] = "Knicks";
        names[4] = "Spurs";
        names[5] = "Rockets";
        names[6] = "Lakers";
        names[7] = "Suns";
        names[8] = "Cavaliers";
        names[9] = "Warriors";
        
        name = names[Empires.getR().nextInt(names.length)];
        
        being = new BeingGUI(BeingGUI.type.BARBARIANS, this);
        this.setRadius(being.getRadius());
        
        this.toVisit = (ArrayList<City>) Empires.getGame().getCities().clone(); // copy list of cities (a city will be removed if visited)
        
        this.setPosition(new Point(450, 200));
        dest = Empires.getGame().getCities().get(Empires.getR().nextInt(Empires.getGame().getCities().size()));
        currentRoad = Empires.getGame().specificRoad(dest);
        
        // set current position to drawing
        being.setFrame(this.getPosition().getX() - being.getRadius() / 2, this.getPosition().getY() - being.getRadius() / 2, being.getRadius(), being.getRadius());
        being.setPath(currentRoad.getPath());
        
        size = Empires.getR().nextInt(100);
        weapon = Empires.getR().nextInt(100);
    }
    
    /**
     * finds the nearest city and sets the path to that city
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
            if((newDestRoad = Empires.getGame().specificRoad(in, getDest())) != null) {
                currentRoad = newDestRoad;
                getBeing().setPath(newDestRoad.getPath());
            } else
                getBeing().setPath(null);
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
        being.move();
    }
    
    @Override
    public void run() {
        while(!dead()) {
            
            if(in == null)
                move();
            
            if(currentRoad.getEnd().equals(Empires.getGame().getCenter())) {
                in = null;
                currentRoad = Empires.getGame().specificRoad(dest);
                being.setPath(currentRoad.getPath());
            } else {
                in = dest;
                
                if(in.getPopulation() <= 0) {
                    nearestCity();
                    if(currentRoad == null) break;
                    in = null;
                } else {
                    in.getGranary().clear();
                    in.setMoney(0);
                    killPeople(Empires.getR().nextInt(100));
                }
            }
            
            try { Thread.sleep(10);
            } catch (InterruptedException e) { }
        }
        Empires.getGame().getBarbarians().remove(this);
    }
    
    @Override
    public String toString() {
        return "destination: " + dest.toString() + " in: " + in.toString();
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(getBeing().getColor());
        g.draw(getBeing());
        g.setColor(Color.yellow);
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the being
     */
    public BeingGUI getBeing() {
        return being;
    }

    /**
     * @return the dest
     */
    public City getDest() {
        return dest;
    }
}
