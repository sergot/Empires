package empires;

/**
 *
 * @author Filip Sergot
 */
public class Barbarians extends Groups {    
    private String name;
    private int size;
    private int weapon;
    private City in;

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
    
    @Override
    public void move(Point p) {
        // TODO
    }
    
    /**
     * 
     * @return the city which is nearest to the group of barbarians
     */
    private Point nearestCity() {
        // TODO
        return new Point();
    }
    
    /**
     * 
     * @param count how many people to kill
     */
    private void killPeople(int count) {
        in.setPopulation(in.getPopulation() - count);
    }
}
