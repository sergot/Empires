package empires;

/**
 *
 * @author Filip Sergot
 */
public class Barbarians extends Groups {    
    private String name;
    private int size;
    private int weapon;

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

    @Override
    public void move(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
