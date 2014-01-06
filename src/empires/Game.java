package empires;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Filip Sergot
 */

public class Game implements Serializable {
    // config
    private Date start;
    
    private ArrayList<City> cities = new ArrayList<>();
    private Capital capital;
    private City center;
    
    private ArrayList<Trader> traders = new ArrayList<>();
    private ArrayList<Barbarians> barbarians = new ArrayList<>();
    private ArrayList<Road> roads = new ArrayList<>();
    private ArrayList<Road> roadsC = new ArrayList<>();
    private ArrayList<Legion> legions = new ArrayList<>();
    
    private ArrayList<Being> beings = new ArrayList<>();
    
    private final int timeBarbarians = 10000;
    
    private boolean painted = false;
    
    private Being selected = null;
    
    public String time, name;
    
    private Object crossLock = new Object();
    private boolean onCross = false;
    
    public Game() {
        start = new Date();
        
        Point[] points = new Point[10];
        points[0] = new Point(200, 250);
        points[1] = new Point(260, 130);
        points[2] = new Point(360, 50);
        points[3] = new Point(440, 50);
        points[4] = new Point(540, 130);
        points[5] = new Point(600, 250);
        points[6] = new Point(540, 370);
        points[7] = new Point(440, 450);
        points[8] = new Point(360, 450);
        points[9] = new Point(260, 370);
        
        // generate cities
        for(int i = 0; i < 10; i++) {
            City c;
            
            // 5th city is a capital
            if(i == 5) {
                c = new Capital();
                capital = (Capital) c;
                c.setRadius(50.);
            } else {
                c = new City();
                c.setRadius(25.);
            }
            
            //c.setName("City #" + i);
            c.setPosition(points[i]);
            
            cities.add(c);
        }
        
        // generate roads
        center = new City();
        center.setName("CENTER");
        center.setPosition(new Point(400, 250));
        for(int i = 0; i < 10; i++) {
            roadsC.add(new Road(cities.get(i), center, 0));
            roadsC.add(new Road(center, cities.get(i), 4));
            
            if(i > 0)
                roads.add(new Road(cities.get(i-1), cities.get(i), 0));
        }
        roads.add(new Road(cities.get(9), cities.get(0), 0));
        
    }
    
    /**
     * Get city where some barbarian are
     * @return city where barbarians are in
     */
    public Barbarians getBarbariansInCity() {
        for(Barbarians b : barbarians)
            if(b.getIn() != null)
                return b;
        return null;
    }
    
    /**
     * checks if there is some barbarians in cities
     * @return true if they are; false otherwise
     */
    public boolean barbariansInCity() {
        for(Barbarians b : barbarians)
            if(b.getIn() != null)
                return true;
        return false;
    }
    
    /**
     * update playing time
     */
    private void updateTime() {
        DateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date now = new Date();
        String newDate = dateFormat.format(now.getTime() - getStart().getTime());
        Empires.getStatusBar().setStatusLabel(newDate);
        Empires.getMainWindow().repaint();
        
        time = newDate;
    }
    
    /**
     * stops the game
     */
    public void stop() {
        for(Being b : getBeings()) {
            b.die();
        }
    }
    
    /**
     * plays the game
     */
    public void play() {
        int time = getTimeBarbarians();
        int productionTime = 0;
        
        boolean allAlive;
        while(true) {
            updateTime();
            
            // generate barbarians
            if(barbarians.size() < 5 && time-- == 0) {
                Barbarians b = new Barbarians();
                barbarians.add(b);
                getBeings().add(b);
                new Thread(b).start();
                time = getTimeBarbarians();
            }
            
            // kill trader if meet barbarians
            for(Barbarians b : barbarians) {
                for(Trader t : traders) {
                    if(t.closeTo((int)b.getPosition().getX(), (int)b.getPosition().getY())) {
                        t.die();
                    }
                }
            }
            
            // kill barbarians if meet legion
            for(Legion l : legions)
                for(Barbarians b : barbarians)
                    if(l.closeTo((int)b.getPosition().getX(), (int)b.getPosition().getY())) {
                        b.die();
                        l.die();
                    }
            
            allAlive = false;
            for(City c : cities)
                if(c.getPopulation() > 0)
                    allAlive = true;
            
            if(!allAlive)
                break;
            
            // check if materials are still fine
            for(City c : cities)
                c.checkMaterials();
            
            // just sleep
            try { Thread.sleep(2);
            } catch(Exception e) { }
        }
    }
    
    /**
     * checks if there is such a road
     * @param start start of needed road
     * @param end end of needed road
     * @return true if there is such a road
     */
    public boolean roadExists(City start, City end) {
        for(Road r : roads) {
            if(r.getStart().equals(start) && r.getEnd().equals(end)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * gets needed road
     * @param start start of needed road
     * @param end end of needed road
     * @return true if there is such a road
     */
    public Road specificRoad(City start, City end) {
        if(start.equals(end)) return null;
        
        Road road = null;
        
        for(Road r : roads) {
            if(r.getStart().equals(start) && r.getEnd().equals(end)) {
                road = r;
                break;
            }
        }
        
        if(road == null) {
            for(Road r : roadsC) {
                if(r.getStart().equals(start)) {
                    road = r;
                    break;
                }
            }
        }
        
        return road;
    }
    
    /**
     * gets road from the center to *end* city
     * @param end end of needed road
     * @return needed road or null
     */
    public Road specificRoad(City end) {
        for(Road r : roadsC) {
            if(r.getEnd().equals(end))
                return r;
        }
        
        return null;
    }
    
    /**
     * gets city which buys materials of given type
     * @param t type of material to sell
     * @return city which need such a material
     */
    public City getCityWhichBuys(Material.types t) {
        for(City c : cities) {
            for(Material.types type : c.getPurchase()) {
                if(type == t)
                    return c;
            }
        }
        
        return null;
    }

    /**
     * @return capital
     */
    public Capital getCapital() {
        return capital;
    }
    
    /**
     * @return cities
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    /**
     * @return traders
     */
    public ArrayList<Trader> getTraders() {
        return traders;
    }

    /**
     * @return barbarians
     */
    public ArrayList<Barbarians> getBarbarians() {
        return barbarians;
    }

    /**
     * @return the roads
     */
    public ArrayList<Road> getRoads() {
        return roads;
    }

    /**
     * @return the legions
     */
    public ArrayList<Legion> getLegions() {
        return legions;
    }
    
    /**
     *
     * @return center cross
     */
    public City getCenter() {
        return center;
    }

    /**
     * @return the roadsC
     */
    public ArrayList<Road> getRoadsC() {
        return roadsC;
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
     * @return the beings
     */
    public ArrayList<Being> getBeings() {
        return beings;
    }

    /**
     * @return the crossLock
     */
    public Object getCrossLock() {
        return crossLock;
    }

    /**
     * @param onCross the onCross to set
     */
    public void setOnCross(boolean onCross) {
        this.onCross = onCross;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @return the timeBarbarians
     */
    public int getTimeBarbarians() {
        return timeBarbarians;
    }

    /**
     * @return the painted
     */
    public boolean isPainted() {
        return painted;
    }

    /**
     * @return the selected
     */
    public Being getSelected() {
        return selected;
    }

    /**
     * @return the onCross
     */
    public boolean isOnCross() {
        return onCross;
    }

    /**
     * @param painted the painted to set
     */
    public void setPainted(boolean painted) {
        this.painted = painted;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Being selected) {
        this.selected = selected;
    }

    /**
     * @param crossLock the crossLock to set
     */
    public void setCrossLock(Object crossLock) {
        this.crossLock = crossLock;
    }
}
