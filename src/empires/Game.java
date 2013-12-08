package empires;

import java.util.ArrayList;

/**
 *
 * @author Filip Sergot
 */
public class Game {
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Trader> traders = new ArrayList<>();
    private ArrayList<Barbarians> barbarians = new ArrayList<>();
    private ArrayList<Road> roads = new ArrayList<>();
    
    private int timeBarbarians = 1000;
    
    public boolean painted = false;
    
    public Game() {
        for(int i = 0; i < 10; i++) {
            City c = i == 5 ? new Capital() : new City();
            c.setName("City #" + i);
            c.setPosition(new Point(Empires.r.nextInt(800), Empires.r.nextInt(500)));
            
            cities.add(c);
        }
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++)
                if(j != i) {
                    roads.add(new Road(cities.get(i), cities.get(j)));
                    roads.add(new Road(cities.get(j), cities.get(i)));
                }
        }
    }
    
    public void play() {
        int time = timeBarbarians;
        while(true) {
            Empires.mainWindow.repaint();
            
            // game logic
            // TODO
            
            // generate barbarians
            if(barbarians.size() < 5 && time-- == 0) {
                Barbarians b = new Barbarians();
                barbarians.add(b);
                new Thread(b).start();
                time = timeBarbarians;
            }
            
            try { Thread.sleep(2);
            } catch(Exception e) { }
        }
    }
    
    public Road specificRoad(City start, City end) {
        for(Road r : roads) {
            if(r.getStart().equals(start) && r.getEnd().equals(end)) {
                return r;
            }
        }
        return null;
    }

    /**
     * @return the cities
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    /**
     * @return the traders
     */
    public ArrayList<Trader> getTraders() {
        return traders;
    }

    /**
     * @return the barbarians
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
    
    
}
